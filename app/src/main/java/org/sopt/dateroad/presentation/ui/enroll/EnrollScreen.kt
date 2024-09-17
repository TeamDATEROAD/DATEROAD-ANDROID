package org.sopt.dateroad.presentation.ui.enroll

import android.net.Uri
import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import org.sopt.dateroad.R
import org.sopt.dateroad.domain.model.Place
import org.sopt.dateroad.domain.type.RegionType
import org.sopt.dateroad.presentation.type.DateRoadRegionBottomSheetType
import org.sopt.dateroad.presentation.type.DateTagType
import org.sopt.dateroad.presentation.type.EnrollScreenType
import org.sopt.dateroad.presentation.type.EnrollType
import org.sopt.dateroad.presentation.type.MyCourseType
import org.sopt.dateroad.presentation.type.OneButtonDialogType
import org.sopt.dateroad.presentation.type.OneButtonDialogWithDescriptionType
import org.sopt.dateroad.presentation.ui.component.bottomsheet.DateRoadPickerBottomSheet
import org.sopt.dateroad.presentation.ui.component.bottomsheet.DateRoadRegionBottomSheet
import org.sopt.dateroad.presentation.ui.component.button.DateRoadBasicButton
import org.sopt.dateroad.presentation.ui.component.button.DateRoadFilledButton
import org.sopt.dateroad.presentation.ui.component.dialog.DateRoadOneButtonDialog
import org.sopt.dateroad.presentation.ui.component.dialog.DateRoadOneButtonDialogWithDescription
import org.sopt.dateroad.presentation.ui.component.textfield.model.TextFieldValidateResult
import org.sopt.dateroad.presentation.ui.component.topbar.DateRoadBasicTopBar
import org.sopt.dateroad.presentation.ui.component.view.DateRoadErrorView
import org.sopt.dateroad.presentation.ui.component.view.DateRoadLoadingView
import org.sopt.dateroad.presentation.ui.enroll.component.EnrollPhotos
import org.sopt.dateroad.presentation.util.DatePicker
import org.sopt.dateroad.presentation.util.EnrollScreen.MAX_ITEMS
import org.sopt.dateroad.presentation.util.EnrollScreen.TITLE_MIN_LENGTH
import org.sopt.dateroad.presentation.util.TimePicker
import org.sopt.dateroad.presentation.util.TimelineAmplitude.CLICK_ADD_SCHEDULE
import org.sopt.dateroad.presentation.util.amplitude.AmplitudeUtils
import org.sopt.dateroad.presentation.util.view.LoadState
import org.sopt.dateroad.ui.theme.DATEROADTheme
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun EnrollRoute(
    padding: PaddingValues,
    viewModel: EnrollViewModel = hiltViewModel(),
    popBackStack: () -> Unit,
    navigateToMyCourse: (MyCourseType) -> Unit,
    enrollType: EnrollType,
    id: Int?
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    val getGalleryLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        if (uri != null) viewModel.setEvent(EnrollContract.EnrollEvent.SetImage(images = listOf(uri.toString())))
    }

    val getPhotoPickerLauncher = rememberLauncherForActivityResult(ActivityResultContracts.PickMultipleVisualMedia(maxItems = MAX_ITEMS)) { uris: List<Uri> ->
        viewModel.setEvent(EnrollContract.EnrollEvent.SetImage(images = uris.map { it.toString() }))
    }

    BackHandler {
        viewModel.setEvent(EnrollContract.EnrollEvent.OnTopBarBackButtonClick)
    }

    LaunchedEffect(Unit) {
        viewModel.setEvent(EnrollContract.EnrollEvent.FetchEnrollCourseType(enrollType = enrollType))
    }

    LaunchedEffect(uiState.enrollType) {
        if (id != null) {
            when (enrollType) {
                EnrollType.COURSE -> {
                    viewModel.fetchTimelineDetail(timelineId = id)
                }

                EnrollType.TIMELINE -> {
                    viewModel.fetchCourseDetail(courseId = id)
                }
            }
        }
    }

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { enrollSideEffect ->
                when (enrollSideEffect) {
                    is EnrollContract.EnrollSideEffect.PopBackStack -> popBackStack()
                    is EnrollContract.EnrollSideEffect.NavigateToMyCourseRead -> navigateToMyCourse(MyCourseType.READ)
                }
            }
    }

    LaunchedEffect(uiState.enroll.title) {
        viewModel.setEvent(
            EnrollContract.EnrollEvent.SetTitleValidationState(
                titleValidationState = when {
                    uiState.enroll.title.isEmpty() -> TextFieldValidateResult.Basic
                    uiState.enroll.title.length >= TITLE_MIN_LENGTH -> TextFieldValidateResult.Success
                    else -> TextFieldValidateResult.ValidationError
                }
            )
        )
    }

    LaunchedEffect(uiState.enroll.date) {
        viewModel.setEvent(
            EnrollContract.EnrollEvent.SetDateValidationState(
                dateValidationState = when {
                    uiState.enroll.date.isEmpty() -> TextFieldValidateResult.Basic
                    uiState.enrollType == EnrollType.COURSE && LocalDate.parse(uiState.enroll.date, DateTimeFormatter.ofPattern(DatePicker.DATE_PATTERN)).isAfter(LocalDate.now()) -> TextFieldValidateResult.ValidationError
                    else -> TextFieldValidateResult.Success
                }
            )
        )
    }

    LaunchedEffect(uiState.loadState) {
        if (uiState.loadState == LoadState.Success) {
            when (uiState.enrollType) {
                EnrollType.TIMELINE -> AmplitudeUtils.trackEvent(CLICK_ADD_SCHEDULE)
                else -> Unit
            }
        }
    }

    EnrollScreen(
        padding = padding,
        enrollUiState = uiState,
        onTopBarBackButtonClick = { viewModel.setEvent(EnrollContract.EnrollEvent.OnTopBarBackButtonClick) },
        onTopBarLoadButtonClick = { viewModel.setSideEffect(EnrollContract.EnrollSideEffect.NavigateToMyCourseRead) },
        onEnrollButtonClick = { viewModel.setEvent(EnrollContract.EnrollEvent.OnEnrollButtonClick) },
        onDateTextFieldClick = { viewModel.setEvent(EnrollContract.EnrollEvent.OnDateTextFieldClick) },
        onTimeTextFieldClick = { viewModel.setEvent(EnrollContract.EnrollEvent.OnTimeTextFieldClick) },
        onRegionTextFieldClick = { viewModel.setEvent(EnrollContract.EnrollEvent.OnRegionTextFieldClick) },
        onSelectedPlaceCourseTimeClick = { viewModel.setEvent(EnrollContract.EnrollEvent.OnSelectedPlaceCourseTimeClick) },
        onDatePickerBottomSheetDismissRequest = { viewModel.setEvent(EnrollContract.EnrollEvent.OnDatePickerBottomSheetDismissRequest) },
        onTimePickerBottomSheetDismissRequest = { viewModel.setEvent(EnrollContract.EnrollEvent.OnTimePickerBottomSheetDismissRequest) },
        onRegionBottomSheetDismissRequest = { viewModel.setEvent(EnrollContract.EnrollEvent.OnRegionBottomSheetDismissRequest) },
        onDurationBottomSheetDismissRequest = { viewModel.setEvent(EnrollContract.EnrollEvent.OnDurationBottomSheetDismissRequest) },
        onPhotoButtonClick = {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
                getGalleryLauncher.launch("image/*")
            } else {
                getPhotoPickerLauncher.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )
            }
        },
        onImageDeleteButtonClick = { index -> viewModel.setEvent(EnrollContract.EnrollEvent.OnImageDeleteButtonClick(index = index)) },
        onTitleValueChange = { title -> viewModel.setEvent(EnrollContract.EnrollEvent.OnTitleValueChange(title = title)) },
        onDatePickerBottomSheetButtonClick = { date -> viewModel.setEvent(EnrollContract.EnrollEvent.OnDatePickerBottomSheetButtonClick(date = date)) },
        onTimePickerBottomSheetButtonClick = { startAt -> viewModel.setEvent(EnrollContract.EnrollEvent.OnTimePickerBottomSheetButtonClick(startAt = startAt)) },
        onDateChipClicked = { tag -> viewModel.setEvent(EnrollContract.EnrollEvent.OnDateChipClicked(tag = tag.name)) },
        onRegionBottomSheetRegionChipClick = { country -> viewModel.setEvent(EnrollContract.EnrollEvent.OnRegionBottomSheetRegionChipClick(country = country)) },
        onRegionBottomSheetAreaChipClick = { city -> viewModel.setEvent(EnrollContract.EnrollEvent.OnRegionBottomSheetAreaChipClick(city = city)) },
        onRegionBottomSheetButtonClick = { region: RegionType?, area: Any? -> viewModel.setEvent(EnrollContract.EnrollEvent.OnRegionBottomSheetButtonClick(region = region, area = area)) },
        onAddPlaceButtonClick = { place -> viewModel.setEvent(EnrollContract.EnrollEvent.OnAddPlaceButtonClick(place = place)) },
        onPlaceCardDragAndDrop = { places -> viewModel.setEvent(EnrollContract.EnrollEvent.OnPlaceCardDragAndDrop(places = places)) },
        onPlaceTitleValueChange = { placeTitle -> viewModel.setEvent(EnrollContract.EnrollEvent.OnPlaceTitleValueChange(placeTitle = placeTitle)) },
        onDurationBottomSheetButtonClick = { placeDuration -> viewModel.setEvent(EnrollContract.EnrollEvent.OnDurationBottomSheetButtonClick(placeDuration = placeDuration)) },
        onPlaceEditButtonClick = { editable -> viewModel.setEvent(EnrollContract.EnrollEvent.OnEditableValueChange(editable = editable)) },
        onPlaceCardDeleteButtonClick = { index -> viewModel.setEvent(EnrollContract.EnrollEvent.OnPlaceCardDeleteButtonClick(index = index)) },
        onDescriptionValueChange = { description -> viewModel.setEvent(EnrollContract.EnrollEvent.OnDescriptionValueChange(description = description)) },
        onCostValueChange = { cost -> viewModel.setEvent(EnrollContract.EnrollEvent.OnCostValueChange(cost = cost)) },
        onEnrollSuccessDialogButtonClick = {
            viewModel.setSideEffect(EnrollContract.EnrollSideEffect.PopBackStack)
            viewModel.setEvent(EnrollContract.EnrollEvent.SetIsEnrollSuccessDialogOpen(isEnrollSuccessDialogOpen = false))
        }
    )

    when (uiState.loadState) {
        LoadState.Success -> {
            viewModel.setEvent(EnrollContract.EnrollEvent.SetIsEnrollSuccessDialogOpen(isEnrollSuccessDialogOpen = true))
        }

        LoadState.Loading -> DateRoadLoadingView()

        LoadState.Error -> DateRoadErrorView()

        else -> Unit
    }

    with(uiState) {
        viewModel.setEvent(
            EnrollContract.EnrollEvent.SetEnrollButtonEnabled(
                when (page) {
                    EnrollScreenType.FIRST -> {
                        when (enrollType) {
                            EnrollType.COURSE -> enroll.images.isNotEmpty() && titleValidateState == TextFieldValidateResult.Success && dateValidateState == TextFieldValidateResult.Success && enroll.startAt.isNotEmpty() && enroll.tags.isNotEmpty() && enroll.country != null && enroll.city != null
                            EnrollType.TIMELINE -> titleValidateState == TextFieldValidateResult.Success && enroll.date.isNotEmpty() && enroll.startAt.isNotEmpty() && enroll.tags.isNotEmpty() && enroll.country != null && enroll.city != null
                        }
                    }

                    EnrollScreenType.SECOND -> enroll.places.size >= 2
                    EnrollScreenType.THIRD -> enroll.description.length >= 200 && enroll.cost.isNotEmpty()
                }
            )
        )

        if (enroll.places.isEmpty()) viewModel.setEvent(EnrollContract.EnrollEvent.OnEditableValueChange(editable = true))
    }
}

@Composable
fun EnrollScreen(
    padding: PaddingValues,
    enrollUiState: EnrollContract.EnrollUiState = EnrollContract.EnrollUiState(),
    onTopBarBackButtonClick: () -> Unit,
    onTopBarLoadButtonClick: () -> Unit,
    onEnrollButtonClick: () -> Unit,
    onDateTextFieldClick: () -> Unit,
    onTimeTextFieldClick: () -> Unit,
    onRegionTextFieldClick: () -> Unit,
    onSelectedPlaceCourseTimeClick: () -> Unit,
    onDatePickerBottomSheetDismissRequest: () -> Unit,
    onTimePickerBottomSheetDismissRequest: () -> Unit,
    onRegionBottomSheetDismissRequest: () -> Unit,
    onDurationBottomSheetDismissRequest: () -> Unit,
    onPhotoButtonClick: () -> Unit,
    onImageDeleteButtonClick: (Int) -> Unit,
    onTitleValueChange: (String) -> Unit,
    onDatePickerBottomSheetButtonClick: (String) -> Unit,
    onTimePickerBottomSheetButtonClick: (String) -> Unit,
    onDateChipClicked: (DateTagType) -> Unit,
    onRegionBottomSheetRegionChipClick: (RegionType) -> Unit,
    onRegionBottomSheetAreaChipClick: (Any?) -> Unit,
    onRegionBottomSheetButtonClick: (RegionType?, Any?) -> Unit,
    onAddPlaceButtonClick: (Place) -> Unit,
    onPlaceCardDragAndDrop: (List<Place>) -> Unit,
    onPlaceTitleValueChange: (String) -> Unit,
    onDurationBottomSheetButtonClick: (String) -> Unit,
    onPlaceEditButtonClick: (Boolean) -> Unit,
    onPlaceCardDeleteButtonClick: (Int) -> Unit,
    onDescriptionValueChange: (String) -> Unit,
    onCostValueChange: (String) -> Unit,
    onEnrollSuccessDialogButtonClick: () -> Unit
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize()
            .background(color = DateRoadTheme.colors.white)
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            }
    ) {
        when (enrollUiState.enrollType) {
            EnrollType.COURSE -> {
                DateRoadBasicTopBar(
                    title = stringResource(id = R.string.top_bar_title_enroll_course),
                    leftIconResource = R.drawable.ic_top_bar_back_white,
                    backGroundColor = DateRoadTheme.colors.white,
                    onLeftIconClick = onTopBarBackButtonClick
                )
                Spacer(modifier = Modifier.height(8.dp))
                EnrollPhotos(
                    isDeletable = enrollUiState.page == EnrollScreenType.FIRST,
                    images = enrollUiState.enroll.images,
                    onPhotoButtonClick = onPhotoButtonClick,
                    onDeleteButtonClick = onImageDeleteButtonClick
                )
            }

            EnrollType.TIMELINE -> {
                DateRoadBasicTopBar(
                    title = stringResource(id = R.string.top_bar_title_enroll_timeline),
                    leftIconResource = R.drawable.ic_top_bar_back_white,
                    onLeftIconClick = onTopBarBackButtonClick,
                    buttonContent = {
                        Row {
                            DateRoadFilledButton(
                                isEnabled = true,
                                textContent = stringResource(id = R.string.top_bar_button_text_load),
                                onClick = onTopBarLoadButtonClick,
                                textStyle = DateRoadTheme.typography.bodyMed13,
                                enabledBackgroundColor = DateRoadTheme.colors.purple600,
                                enabledTextColor = DateRoadTheme.colors.white,
                                disabledBackgroundColor = DateRoadTheme.colors.gray200,
                                disabledTextColor = DateRoadTheme.colors.gray400,
                                cornerRadius = 20.dp,
                                paddingHorizontal = 10.dp,
                                paddingVertical = 5.dp
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                        }
                    }
                )
                Spacer(modifier = Modifier.height(2.dp))
            }
        }
        Column(
            modifier = Modifier.weight(1f)
        ) {
            when (enrollUiState.page) {
                EnrollScreenType.FIRST -> EnrollFirstScreen(
                    enrollUiState = enrollUiState,
                    onDateTextFieldClick = onDateTextFieldClick,
                    onTimeTextFieldClick = onTimeTextFieldClick,
                    onRegionTextFieldClick = onRegionTextFieldClick,
                    onTitleValueChange = onTitleValueChange,
                    onDateChipClicked = onDateChipClicked
                )

                EnrollScreenType.SECOND -> EnrollSecondScreen(
                    enrollUiState = enrollUiState,
                    onSelectedPlaceCourseTimeClick = onSelectedPlaceCourseTimeClick,
                    onAddPlaceButtonClick = onAddPlaceButtonClick,
                    onPlaceTitleValueChange = onPlaceTitleValueChange,
                    onPlaceEditButtonClick = onPlaceEditButtonClick,
                    onPlaceCardDeleteButtonClick = onPlaceCardDeleteButtonClick,
                    onPlaceCardDragAndDrop = onPlaceCardDragAndDrop
                )

                EnrollScreenType.THIRD -> EnrollThirdScreen(
                    enrollUiState = enrollUiState,
                    onDescriptionValueChange = onDescriptionValueChange,
                    onCostValueChange = onCostValueChange
                )
            }
        }
        Spacer(
            modifier = Modifier
                .height(16.dp)
                .padding(horizontal = 16.dp)
        )
        DateRoadBasicButton(
            modifier = Modifier.padding(horizontal = 16.dp),
            isEnabled = enrollUiState.isEnrollButtonEnabled,
            textContent = when (enrollUiState.enrollType) {
                EnrollType.COURSE -> if (enrollUiState.page != EnrollScreenType.THIRD) stringResource(id = R.string.enroll_button_text_next_with_page, enrollUiState.page.position, 3) else stringResource(id = R.string.complete)
                EnrollType.TIMELINE -> if (enrollUiState.page == EnrollScreenType.FIRST) stringResource(id = R.string.enroll_button_text_next) else stringResource(id = R.string.complete)
            },
            onClick = onEnrollButtonClick
        )
        Spacer(modifier = Modifier.height(16.dp))
    }

    DateRoadPickerBottomSheet(
        isBottomSheetOpen = enrollUiState.isDatePickerBottomSheetOpen,
        isButtonEnabled = true,
        buttonText = stringResource(id = R.string.apply),
        onButtonClick = {
            onDatePickerBottomSheetButtonClick(
                enrollUiState.datePickers.joinToString(separator = ".") { it.pickerState.selectedItem.padStart(2, '0') }
            )
        },
        onDismissRequest = onDatePickerBottomSheetDismissRequest,
        pickers = enrollUiState.datePickers
    )

    DateRoadPickerBottomSheet(
        isBottomSheetOpen = enrollUiState.isTimePickerBottomSheetOpen,
        isButtonEnabled = true,
        buttonText = stringResource(id = R.string.apply),
        onButtonClick = {
            onTimePickerBottomSheetButtonClick(
                formatTime(enrollUiState.timePickers.map { it.pickerState.selectedItem })
            )
        },
        onDismissRequest = onTimePickerBottomSheetDismissRequest,
        pickers = enrollUiState.timePickers
    )

    DateRoadRegionBottomSheet(
        isBottomSheetOpen = enrollUiState.isRegionBottomSheetOpen,
        isButtonEnabled = enrollUiState.onRegionBottomSheetRegionSelected != null && enrollUiState.onRegionBottomSheetAreaSelected != null,
        dateRoadRegionBottomSheetType = DateRoadRegionBottomSheetType.ENROLL,
        selectedRegion = enrollUiState.onRegionBottomSheetRegionSelected,
        onSelectedRegionChanged = { regionType ->
            onRegionBottomSheetRegionChipClick(regionType)
        },
        selectedArea = enrollUiState.onRegionBottomSheetAreaSelected,
        onSelectedAreaChanged = { area ->
            onRegionBottomSheetAreaChipClick(area)
        },
        titleText = stringResource(id = R.string.region_bottom_sheet_title),
        buttonText = stringResource(id = R.string.apply),
        onButtonClick = { regoion, area -> onRegionBottomSheetButtonClick(regoion, area) },
        onDismissRequest = onRegionBottomSheetDismissRequest
    )

    DateRoadPickerBottomSheet(
        isBottomSheetOpen = enrollUiState.isDurationBottomSheetOpen,
        isButtonEnabled = true,
        buttonText = stringResource(id = R.string.apply),
        onButtonClick = {
            onDurationBottomSheetButtonClick(enrollUiState.durationPicker.first().pickerState.selectedItem)
        },
        onDismissRequest = onDurationBottomSheetDismissRequest,
        pickers = enrollUiState.durationPicker
    )

    if (enrollUiState.isEnrollSuccessDialogOpen) {
        when (enrollUiState.enrollType) {
            EnrollType.TIMELINE -> {
                DateRoadOneButtonDialog(
                    oneButtonDialogType = OneButtonDialogType.ENROLL_TIMELINE,
                    onDismissRequest = onEnrollSuccessDialogButtonClick,
                    onClickConfirm = onEnrollSuccessDialogButtonClick
                )
            }

            EnrollType.COURSE -> {
                DateRoadOneButtonDialogWithDescription(
                    oneButtonDialogWithDescriptionType = OneButtonDialogWithDescriptionType.ENROLL_COURSE,
                    onDismissRequest = onEnrollSuccessDialogButtonClick,
                    onClickConfirm = onEnrollSuccessDialogButtonClick
                )
            }
        }
    }
}

fun formatTime(time: List<String>): String {
    val period = if (time[0] == TimePicker.AM) "AM" else "PM"
    val hour = time[1].padStart(2, '0')
    val minute = time[2].padStart(2, '0')
    return "$hour:$minute $period"
}

@Preview
@Composable
fun EnrollScreenPreview() {
    DATEROADTheme {
        EnrollScreen(
            padding = PaddingValues(0.dp),
            enrollUiState = EnrollContract.EnrollUiState(
                loadState = LoadState.Success
            ),
            onTopBarBackButtonClick = {},
            onTopBarLoadButtonClick = {},
            onEnrollButtonClick = {},
            onDateTextFieldClick = {},
            onTimeTextFieldClick = {},
            onRegionTextFieldClick = {},
            onSelectedPlaceCourseTimeClick = {},
            onDatePickerBottomSheetDismissRequest = {},
            onTimePickerBottomSheetDismissRequest = {},
            onRegionBottomSheetDismissRequest = {},
            onDurationBottomSheetDismissRequest = {},
            onPhotoButtonClick = {},
            onImageDeleteButtonClick = {},
            onTitleValueChange = {},
            onDatePickerBottomSheetButtonClick = {},
            onTimePickerBottomSheetButtonClick = {},
            onDateChipClicked = {},
            onRegionBottomSheetRegionChipClick = {},
            onRegionBottomSheetAreaChipClick = {},
            onRegionBottomSheetButtonClick = { _, _ -> },
            onAddPlaceButtonClick = {},
            onPlaceTitleValueChange = {},
            onDurationBottomSheetButtonClick = {},
            onPlaceEditButtonClick = {},
            onPlaceCardDeleteButtonClick = {},
            onPlaceCardDragAndDrop = {},
            onDescriptionValueChange = {},
            onCostValueChange = {},
            onEnrollSuccessDialogButtonClick = {}
        )
    }
}
