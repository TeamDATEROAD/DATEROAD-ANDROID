package org.sopt.dateroad.presentation.ui.look

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.sopt.dateroad.R
import org.sopt.dateroad.presentation.type.ChipType
import org.sopt.dateroad.presentation.type.EmptyViewType
import org.sopt.dateroad.presentation.type.GyeonggiAreaType
import org.sopt.dateroad.presentation.type.IncheonAreaType
import org.sopt.dateroad.presentation.type.MoneyTagType
import org.sopt.dateroad.presentation.type.RegionType
import org.sopt.dateroad.presentation.type.SeoulAreaType
import org.sopt.dateroad.presentation.ui.component.bottomsheet.DateRoadRegionBottomSheet
import org.sopt.dateroad.presentation.ui.component.button.DateRoadAreaButton
import org.sopt.dateroad.presentation.ui.component.button.DateRoadImageButton
import org.sopt.dateroad.presentation.ui.component.chip.DateRoadTextChip
import org.sopt.dateroad.presentation.ui.component.emptyview.DateRoadEmptyView
import org.sopt.dateroad.presentation.ui.component.topbar.DateRoadLeftTitleTopBar
import org.sopt.dateroad.presentation.ui.look.component.LookCourseCard
import org.sopt.dateroad.presentation.util.modifier.noRippleClickable
import org.sopt.dateroad.presentation.util.view.LoadState
import org.sopt.dateroad.ui.theme.DATEROADTheme
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun LookRoute(
    padding: PaddingValues,
    viewModel: LookViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(Unit) {
        viewModel.setEvent(LookContract.LookEvent.FetchCourses)
    }

    when (uiState.loadState) {
        LoadState.Success -> {
            LookScreen(
                padding = padding,
                lookUiState = uiState,
                onAreaButtonClicked = { viewModel.setEvent(LookContract.LookEvent.OnAreaButtonClicked) },
                onResetButtonClicked = { viewModel.setEvent(LookContract.LookEvent.OnResetButtonClicked) },
                onRegionBottomSheetDismissRequest = { viewModel.setEvent(LookContract.LookEvent.OnRegionBottomSheetDismissRequest) },
                onMoneyChipClicked = { moneyTagType -> viewModel.setEvent(LookContract.LookEvent.OnMoneyChipClicked(money = moneyTagType)) },
                onRegionBottomSheetButtonClicked = { region: RegionType?, area: Any? -> viewModel.setEvent(LookContract.LookEvent.OnRegionBottomSheetButtonClicked(region = region, area = area)) },
                onRegionBottomSheetRegionClicked = { region: RegionType? -> viewModel.setEvent(LookContract.LookEvent.OnRegionBottomSheetRegionClicked(region = region)) },
                onRegionBottomSheetAreaClicked = { area: Any? -> viewModel.setEvent(LookContract.LookEvent.OnRegionBottomSheetAreaClicked(area = area)) }
            )
        }

        else -> Unit
    }
}

@Composable
fun LookScreen(
    padding: PaddingValues,
    lookUiState: LookContract.LookUiState = LookContract.LookUiState(),
    onAreaButtonClicked: () -> Unit = {},
    onResetButtonClicked: () -> Unit = {},
    onRegionBottomSheetDismissRequest: () -> Unit = {},
    onMoneyChipClicked: (MoneyTagType?) -> Unit = {},
    onRegionBottomSheetButtonClicked: (RegionType?, Any?) -> Unit = { _, _ -> },
    onRegionBottomSheetRegionClicked: (RegionType?) -> Unit = {},
    onRegionBottomSheetAreaClicked: (Any?) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .background(color = DateRoadTheme.colors.white)
            .padding(padding)
            .fillMaxSize()
    ) {
        DateRoadLeftTitleTopBar(
            title = stringResource(id = R.string.top_bar_title_look),
            buttonContent = {
                DateRoadImageButton(
                    isEnabled = true,
                    onClick = {},
                    cornerRadius = 14.dp,
                    paddingHorizontal = 16.dp,
                    paddingVertical = 9.dp
                )
            }
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(16.dp))
            DateRoadAreaButton(
                modifier = Modifier.weight(1f),
                textContent = stringResource(
                    id = when (lookUiState.area) {
                        is SeoulAreaType -> lookUiState.area.nameRes
                        is GyeonggiAreaType -> lookUiState.area.nameRes
                        is IncheonAreaType -> lookUiState.area.nameRes
                        else -> R.string.region
                    }
                ),
                onClick = onAreaButtonClicked
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                modifier = Modifier
                    .padding(8.dp)
                    .noRippleClickable(onClick = onResetButtonClicked),
                painter = painterResource(id = R.drawable.ic_all_reset),
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        LazyRow(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(MoneyTagType.entries.size) { index ->
                DateRoadTextChip(
                    textId = MoneyTagType.entries[index].titleRes,
                    chipType = ChipType.MONEY,
                    isSelected = lookUiState.money == MoneyTagType.entries[index],
                    onSelectedChange = {
                        onMoneyChipClicked(MoneyTagType.entries[index])
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        if (lookUiState.courses.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                DateRoadEmptyView(emptyViewType = EmptyViewType.LOOK)
            }
        }
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(lookUiState.courses.size) { index ->
                LookCourseCard(course = lookUiState.courses[index])
            }
        }
    }

    DateRoadRegionBottomSheet(
        isBottomSheetOpen = lookUiState.isRegionBottomSheetOpen,
        isButtonEnabled = lookUiState.regionBottomSheetSelectedRegion != null && lookUiState.regionBottomSheetSelectedArea != null,
        selectedRegion = lookUiState.regionBottomSheetSelectedRegion,
        onSelectedRegionChanged = { region -> onRegionBottomSheetRegionClicked(region) },
        selectedArea = lookUiState.regionBottomSheetSelectedArea,
        onSelectedAreaChanged = { area -> onRegionBottomSheetAreaClicked(area) },
        onButtonClick = { region, area ->
            onRegionBottomSheetButtonClicked(region, area)
        },
        onDismissRequest = onRegionBottomSheetDismissRequest
    )
}

@Preview()
@Composable
fun LookScreenPreview() {
    DATEROADTheme {
        LookScreen(padding = PaddingValues(0.dp))
    }
}
