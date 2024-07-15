package org.sopt.dateroad.presentation.ui.enroll

import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import org.sopt.dateroad.R
import org.sopt.dateroad.domain.model.Place
import org.sopt.dateroad.presentation.type.DateTagType
import org.sopt.dateroad.presentation.type.EnrollScreenType
import org.sopt.dateroad.presentation.type.EnrollType
import org.sopt.dateroad.presentation.type.MyCourseType
import org.sopt.dateroad.presentation.type.RegionType
import org.sopt.dateroad.presentation.ui.component.bottomsheet.DateRoadPickerBottomSheet
import org.sopt.dateroad.presentation.ui.component.bottomsheet.DateRoadRegionBottomSheet
import org.sopt.dateroad.presentation.ui.component.button.DateRoadBasicButton
import org.sopt.dateroad.presentation.ui.component.button.DateRoadFilledButton
import org.sopt.dateroad.presentation.ui.component.textfield.model.TextFieldValidateResult
import org.sopt.dateroad.presentation.ui.component.topbar.DateRoadBasicTopBar
import org.sopt.dateroad.presentation.ui.enroll.component.EnrollPhotos
import org.sopt.dateroad.presentation.util.EnrollScreen.MAX_ITEMS
import org.sopt.dateroad.presentation.util.TimePicker
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
    courseId: Int?
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    val getGalleryLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        viewModel.setEvent(EnrollContract.EnrollEvent.SetImage(images = listOf(uri.toString())))
    }

    val getPhotoPickerLauncher = rememberLauncherForActivityResult(ActivityResultContracts.PickMultipleVisualMedia(maxItems = MAX_ITEMS)) { uris: List<Uri> ->
        viewModel.setEvent(EnrollContract.EnrollEvent.SetImage(images = uris.map { it.toString() }))
    }

    LaunchedEffect(Unit) {
        viewModel.setEvent(EnrollContract.EnrollEvent.FetchEnrollCourseType(enrollType = enrollType))
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

    when (uiState.loadState) {
        LoadState.Idle -> {
            EnrollScreen(
                padding = padding,
                enrollUiState = uiState,
                onTopBarBackButtonClick = { viewModel.setSideEffect(EnrollContract.EnrollSideEffect.PopBackStack) },
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
                onDateChipClicked = { tag -> viewModel.setEvent(EnrollContract.EnrollEvent.OnDateChipClicked(tag = tag)) },
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
                onCostValueChange = { cost -> viewModel.setEvent(EnrollContract.EnrollEvent.OnCostValueChange(cost = cost)) }
            )
        }

        else -> Unit
    }

    with(uiState) {
        viewModel.setEvent(
            EnrollContract.EnrollEvent.SetEnrollButtonEnabled(
                when (page) {
                    EnrollScreenType.FIRST -> {
                        when (enrollType) {
                            EnrollType.COURSE -> images.isNotEmpty() && titleValidateState == TextFieldValidateResult.Success && dateValidateState == TextFieldValidateResult.Success && startAt.isNotEmpty() && tags.isNotEmpty() && country != null && city != null
                            EnrollType.TIMELINE -> titleValidateState == TextFieldValidateResult.Success && dateValidateState == TextFieldValidateResult.Success && startAt.isNotEmpty() && tags.isNotEmpty() && country != null && city != null
                        }
                    }

                    EnrollScreenType.SECOND -> place.size >= 2
                    EnrollScreenType.THIRD -> description.length >= 200 && cost.isNotEmpty()
                }
            )
        )

        if (place.isEmpty()) viewModel.setEvent(EnrollContract.EnrollEvent.OnEditableValueChange(editable = true))
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
    onCostValueChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize()
            .background(color = DateRoadTheme.colors.white)
    ) {
        when (enrollUiState.enrollType) {
            EnrollType.COURSE -> {
                DateRoadBasicTopBar(
                    title = stringResource(id = R.string.top_bar_title_enroll_course),
                    iconLeftResource = R.drawable.ic_top_bar_back_white,
                    backGroundColor = DateRoadTheme.colors.white,
                    onIconClick = onTopBarBackButtonClick
                )
                Spacer(modifier = Modifier.height(8.dp))
                EnrollPhotos(
                    isDeletable = enrollUiState.page == EnrollScreenType.FIRST,
                    images = enrollUiState.images,
                    onPhotoButtonClick = onPhotoButtonClick,
                    onDeleteButtonClick = onImageDeleteButtonClick
                )
            }

            EnrollType.TIMELINE -> {
                DateRoadBasicTopBar(
                    title = stringResource(id = R.string.top_bar_title_enroll_timeline),
                    iconLeftResource = R.drawable.ic_top_bar_back_white,
                    onIconClick = onTopBarBackButtonClick,
                    buttonContent = {
                        DateRoadFilledButton(
                            isEnabled = true,
                            textContent = stringResource(id = R.string.top_bar_button_text_load),
                            onClick = onTopBarLoadButtonClick,
                            textStyle = DateRoadTheme.typography.bodyMed13,
                            enabledBackgroundColor = DateRoadTheme.colors.deepPurple,
                            enabledTextColor = DateRoadTheme.colors.white,
                            disabledBackgroundColor = DateRoadTheme.colors.gray200,
                            disabledTextColor = DateRoadTheme.colors.gray400,
                            cornerRadius = 20.dp,
                            paddingHorizontal = 10.dp,
                            paddingVertical = 5.dp
                        )
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
                EnrollType.COURSE -> if (enrollUiState.page != EnrollScreenType.THIRD) stringResource(id = R.string.enroll_button_text_next_with_page, enrollUiState.page.position, 3) else stringResource(id = R.string.apply)
                EnrollType.TIMELINE -> if (enrollUiState.page == EnrollScreenType.FIRST) stringResource(id = R.string.enroll_button_text_next) else stringResource(id = R.string.apply)
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
            onCostValueChange = {}
        )
    }
}
