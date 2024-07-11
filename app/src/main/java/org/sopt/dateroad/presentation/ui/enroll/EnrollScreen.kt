package org.sopt.dateroad.presentation.ui.enroll

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
import org.sopt.dateroad.presentation.ui.component.button.DateRoadBasicButton
import org.sopt.dateroad.presentation.ui.component.topbar.DateRoadBasicTopBar
import org.sopt.dateroad.presentation.ui.enroll.component.EnrollPhotos
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

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { enrollSideEffect ->
                when (enrollSideEffect) {
                    is EnrollContract.EnrollSideEffect.PopBackStack -> popBackStack()
                }
            }
    }

    when (uiState.loadState) {
        LoadState.Success -> {
            EnrollScreen(
                padding = padding,
                enrollUiState = uiState,
                onEnrollButtonClick = {},
                onPageChange = {},
                onPhotoButtonClick = {},
                onDeleteButtonClick = {},
                onTitleValueChange = {},
                onDatePickerBottomSheetButtonClicked = {},
                onTimePickerBottomSheetButtonClicked = {},
                onDateChipClicked = {},
                onRegionBottomSheetRegionChipClicked = {},
                onRegionBottomSheetAreaChipClicked = {},
                onAddPlaceButtonClick = {},
                onPlaceTitleValueChange = {},
                onPlaceDurationClick = {},
                onPlaceEditButtonClick = {},
                onDescriptionValueChange = {},
                onCostValueChange = {}
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
    onPageChange: (EnrollScreenType) -> Unit,
    onPhotoButtonClick: (List<String>) -> Unit,
    onDeleteButtonClick: (Int) -> Unit,
    onTitleValueChange: (String) -> Unit,
    onDatePickerBottomSheetButtonClicked: (String) -> Unit,
    onTimePickerBottomSheetButtonClicked: (String) -> Unit,
    onDateChipClicked: (DateTagType) -> Unit,
    onRegionBottomSheetRegionChipClicked: (RegionType) -> Unit,
    onRegionBottomSheetAreaChipClicked: (Any?) -> Unit,
    onAddPlaceButtonClick: (Place) -> Unit,
    onPlaceTitleValueChange: (String) -> Unit,
    onPlaceDurationClick: (String) -> Unit,
    onPlaceEditButtonClick: (Boolean) -> Unit,
    onDescriptionValueChange: (String) -> Unit,
    onCostValueChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
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
            images = enrollUiState.images
        )
        Column(
            modifier = Modifier.weight(1f)
        ) {
            enrollUiState.page.screen()
        }
        Spacer(
            modifier = Modifier
                .height(16.dp)
                .padding(horizontal = 16.dp)
        )
        DateRoadBasicButton(
            modifier = Modifier.padding(horizontal = 16.dp),
            textContent = stringResource(id = R.string.enroll_button_text_next, enrollUiState.page, 3)
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
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
            onPageChange = {},
            onPhotoButtonClick = {},
            onDeleteButtonClick = {},
            onTitleValueChange = {},
            onDatePickerBottomSheetButtonClicked = {},
            onTimePickerBottomSheetButtonClicked = {},
            onDateChipClicked = {},
            onRegionBottomSheetRegionChipClicked = {},
            onRegionBottomSheetAreaChipClicked = {},
            onAddPlaceButtonClick = {},
            onPlaceTitleValueChange = {},
            onPlaceDurationClick = {},
            onPlaceEditButtonClick = {},
            onDescriptionValueChange = {},
            onCostValueChange = {}
        )
    }
}
