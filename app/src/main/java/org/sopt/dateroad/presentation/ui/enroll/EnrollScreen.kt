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
import org.sopt.dateroad.presentation.type.RegionType
import org.sopt.dateroad.presentation.ui.component.bottomsheet.DateRoadPickerBottomSheet
import org.sopt.dateroad.presentation.ui.component.bottomsheet.DateRoadRegionBottomSheet
import org.sopt.dateroad.presentation.ui.component.button.DateRoadBasicButton
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
    popBackStack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    val getGalleryLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        viewModel.setEvent(EnrollContract.EnrollEvent.SetImage(images = listOf(uri.toString())))
    }

    val getPhotoPickerLauncher = rememberLauncherForActivityResult(ActivityResultContracts.PickMultipleVisualMedia(maxItems = MAX_ITEMS)) { uris: List<Uri> ->
        viewModel.setEvent(EnrollContract.EnrollEvent.SetImage(images = uris.map { it.toString() }))
    }

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { enrollSideEffect ->
                when (enrollSideEffect) {
                    is EnrollContract.EnrollSideEffect.PopBackStack -> popBackStack()
                }
            }
    }

    when (uiState.loadState) {
        LoadState.Idle -> {
            EnrollScreen(
                padding = padding,
                enrollUiState = uiState,
                onEnrollButtonClick = { viewModel.setEvent(EnrollContract.EnrollEvent.OnEnrollButtonClick) },
                onDateTextFieldClick = { viewModel.setEvent(EnrollContract.EnrollEvent.OnDateTextFieldClick) },
                onTimeTextFieldClick = { viewModel.setEvent(EnrollContract.EnrollEvent.OnTimeTextFieldClick) },
                onRegionTextFieldClick = { viewModel.setEvent(EnrollContract.EnrollEvent.OnRegionTextFieldClick) },
                onDatePickerBottomSheetDismissRequest = { viewModel.setEvent(EnrollContract.EnrollEvent.OnDatePickerBottomSheetDismissRequest) },
                onTimePickerBottomSheetDismissRequest = { viewModel.setEvent(EnrollContract.EnrollEvent.OnTimePickerBottomSheetDismissRequest) },
                onRegionBottomSheetDismissRequest = { viewModel.setEvent(EnrollContract.EnrollEvent.OnRegionBottomSheetDismissRequest) },
                onPlaceDurationClick = { viewModel.setEvent(EnrollContract.EnrollEvent.OnPlaceDurationClick) },
                onPageChange = { enrollScreenType -> viewModel.setEvent(EnrollContract.EnrollEvent.OnPageChange(page = enrollScreenType)) },
                onPhotoButtonClick = {
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
                        getGalleryLauncher.launch("image/*")
                    } else {
                        getPhotoPickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    }
                },
                onDeleteButtonClick = { index -> viewModel.setEvent(EnrollContract.EnrollEvent.OnDeleteButtonClick(index = index)) },
                onTitleValueChange = { title -> viewModel.setEvent(EnrollContract.EnrollEvent.OnTitleValueChange(title = title)) },
                onDatePickerBottomSheetButtonClicked = { date -> viewModel.setEvent(EnrollContract.EnrollEvent.OnDatePickerBottomSheetButtonClick(date = date)) },
                onTimePickerBottomSheetButtonClicked = { startAt -> viewModel.setEvent(EnrollContract.EnrollEvent.OnTimePickerBottomSheetButtonClick(startAt = startAt)) },
                onDateChipClicked = { tag -> viewModel.setEvent(EnrollContract.EnrollEvent.OnDateChipClicked(tag = tag)) },
                onRegionBottomSheetRegionChipClicked = { country -> viewModel.setEvent(EnrollContract.EnrollEvent.OnRegionBottomSheetRegionChipClick(country = country)) },
                onRegionBottomSheetAreaChipClicked = { city -> viewModel.setEvent(EnrollContract.EnrollEvent.OnRegionBottomSheetAreaChipClick(city = city)) },
                onRegionBottomSheetButtonClick = { region: RegionType?, area: Any? -> viewModel.setEvent(EnrollContract.EnrollEvent.OnRegionBottomSheetButtonClick(region = region, area = area)) },
                onAddPlaceButtonClick = { place -> viewModel.setEvent(EnrollContract.EnrollEvent.OnAddPlaceButtonClick(place = place)) },
                onPlaceTitleValueChange = { title -> viewModel.setEvent(EnrollContract.EnrollEvent.OnPlaceTitleValueChange(title = title)) },
                onPlaceEditButtonClick = { editable -> viewModel.setEvent(EnrollContract.EnrollEvent.OnPlaceEditButtonClick(editable = editable)) },
                onDescriptionValueChange = { description -> viewModel.setEvent(EnrollContract.EnrollEvent.OnDescriptionValueChange(description = description)) },
                onCostValueChange = { cost -> viewModel.setEvent(EnrollContract.EnrollEvent.OnCostValueChange(cost = cost)) }
            )
        }

        else -> Unit
    }
}

@Composable
fun EnrollScreen(
    padding: PaddingValues,
    enrollUiState: EnrollContract.EnrollUiState = EnrollContract.EnrollUiState(),
    onEnrollButtonClick: () -> Unit,
    onDateTextFieldClick: () -> Unit,
    onTimeTextFieldClick: () -> Unit,
    onRegionTextFieldClick: () -> Unit,
    onDatePickerBottomSheetDismissRequest: () -> Unit,
    onTimePickerBottomSheetDismissRequest: () -> Unit,
    onRegionBottomSheetDismissRequest: () -> Unit,
    onPageChange: (EnrollScreenType) -> Unit,
    onPhotoButtonClick: () -> Unit,
    onDeleteButtonClick: (Int) -> Unit,
    onTitleValueChange: (String) -> Unit,
    onDatePickerBottomSheetButtonClicked: (String) -> Unit,
    onTimePickerBottomSheetButtonClicked: (String) -> Unit,
    onDateChipClicked: (DateTagType) -> Unit,
    onRegionBottomSheetRegionChipClicked: (RegionType) -> Unit,
    onRegionBottomSheetAreaChipClicked: (Any?) -> Unit,
    onRegionBottomSheetButtonClick: (RegionType?, Any?) -> Unit,
    onAddPlaceButtonClick: (Place) -> Unit,
    onPlaceTitleValueChange: (String) -> Unit,
    onPlaceDurationClick: () -> Unit,
    onPlaceEditButtonClick: (Boolean) -> Unit,
    onDescriptionValueChange: (String) -> Unit,
    onCostValueChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize()
            .background(color = DateRoadTheme.colors.white)
    ) {
        DateRoadBasicTopBar(
            title = stringResource(id = R.string.top_bar_title_enroll),
            iconLeftResource = R.drawable.ic_top_bar_back_white,
            backGroundColor = DateRoadTheme.colors.white,
            onIconClick = { }
        )
        Spacer(modifier = Modifier.height(8.dp))
        EnrollPhotos(
            isDeletable = enrollUiState.page == EnrollScreenType.FIRST,
            images = enrollUiState.images,
            onPhotoButtonClick = onPhotoButtonClick,
            onDeleteButtonClick = onDeleteButtonClick
        )
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
                    onAddPlaceButtonClick = onAddPlaceButtonClick,
                    onPlaceTitleValueChange = onPlaceTitleValueChange,
                    onPlaceDurationClick = onPlaceDurationClick,
                    onPlaceEditButtonClick = onPlaceEditButtonClick
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
            textContent = stringResource(id = R.string.enroll_button_text_next, enrollUiState.page.position, 3)
        )
        Spacer(modifier = Modifier.height(16.dp))
    }

    DateRoadPickerBottomSheet(
        isBottomSheetOpen = enrollUiState.isDatePickerBottomSheetOpen,
        isButtonEnabled = true,
        buttonText = stringResource(id = R.string.apply),
        onButtonClick = {
            onDatePickerBottomSheetButtonClicked(
                enrollUiState.datePickers.joinToString(separator = ".") { it.pickerState.selectedItem }
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
            onTimePickerBottomSheetButtonClicked(
                formatTime(enrollUiState.timePickers.map { it.pickerState.selectedItem })
            )
            onTimePickerBottomSheetDismissRequest()
        },
        pickers = enrollUiState.timePickers
    )

    DateRoadRegionBottomSheet(
        isBottomSheetOpen = enrollUiState.isRegionBottomSheetOpen,
        isButtonEnabled = enrollUiState.onRegionBottomSheetRegionSelected != null && enrollUiState.onRegionBottomSheetAreaSelected != null,
        selectedRegion = enrollUiState.onRegionBottomSheetRegionSelected,
        onSelectedRegionChanged = { regionType ->
            onRegionBottomSheetRegionChipClicked(regionType)
        },
        selectedArea = enrollUiState.onRegionBottomSheetAreaSelected,
        onSelectedAreaChanged = { area ->
            onRegionBottomSheetAreaChipClicked(area)
        },
        titleText = stringResource(id = R.string.region_bottom_sheet_title),
        buttonText = stringResource(id = R.string.apply),
        onButtonClick = { onRegionBottomSheetButtonClick(enrollUiState.onRegionBottomSheetRegionSelected, enrollUiState.onRegionBottomSheetAreaSelected) },
        onDismissRequest = onRegionBottomSheetDismissRequest
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
            onEnrollButtonClick = {},
            onDateTextFieldClick = {},
            onTimeTextFieldClick = {},
            onRegionTextFieldClick = {},
            onDatePickerBottomSheetDismissRequest = {},
            onTimePickerBottomSheetDismissRequest = {},
            onRegionBottomSheetDismissRequest = {},
            onPageChange = {},
            onPhotoButtonClick = {},
            onDeleteButtonClick = {},
            onTitleValueChange = {},
            onDatePickerBottomSheetButtonClicked = {},
            onTimePickerBottomSheetButtonClicked = {},
            onDateChipClicked = {},
            onRegionBottomSheetRegionChipClicked = {},
            onRegionBottomSheetAreaChipClicked = {},
            onRegionBottomSheetButtonClick = { _, _ -> },
            onAddPlaceButtonClick = {},
            onPlaceTitleValueChange = {},
            onPlaceDurationClick = {},
            onPlaceEditButtonClick = {},
            onDescriptionValueChange = {},
            onCostValueChange = {}
        )
    }
}
