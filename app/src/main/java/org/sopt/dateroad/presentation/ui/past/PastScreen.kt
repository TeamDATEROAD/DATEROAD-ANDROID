package org.sopt.dateroad.presentation.ui.past

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import org.sopt.dateroad.domain.type.DateTimeType
import org.sopt.dateroad.presentation.type.DateType
import org.sopt.dateroad.presentation.type.EmptyViewType
import org.sopt.dateroad.presentation.ui.component.topbar.DateRoadBasicTopBar
import org.sopt.dateroad.presentation.ui.component.view.DateRoadEmptyView
import org.sopt.dateroad.presentation.ui.component.view.DateRoadErrorView
import org.sopt.dateroad.presentation.ui.component.view.DateRoadIdleView
import org.sopt.dateroad.presentation.ui.component.view.DateRoadLoadingView
import org.sopt.dateroad.presentation.ui.past.component.PastCard
import org.sopt.dateroad.presentation.util.view.LoadState
import org.sopt.dateroad.ui.theme.DATEROADTheme
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun PastRoute(
    padding: PaddingValues,
    viewModel: PastViewModel = hiltViewModel(),
    popBackStack: () -> Unit,
    navigateToTimelineDetail: (DateType, Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(Unit) {
        viewModel.fetchPastDate(DateTimeType.PAST)
    }

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle).collect { pastSideEffect ->
            when (pastSideEffect) {
                is PastContract.PastSideEffect.PopBackStack -> popBackStack()
                is PastContract.PastSideEffect.NavigateToTimelineDetail -> navigateToTimelineDetail(pastSideEffect.dateType, pastSideEffect.dateId)
            }
        }
    }

    when (uiState.loadState) {
        LoadState.Idle -> DateRoadIdleView()

        LoadState.Loading -> DateRoadLoadingView()

        LoadState.Success -> {
            PastScreen(
                padding = padding,
                pastUiState = uiState,
                popBackStack = { viewModel.setSideEffect(PastContract.PastSideEffect.PopBackStack) },
                navigateToTimelineDetail = { dateType, dateId -> viewModel.setSideEffect(PastContract.PastSideEffect.NavigateToTimelineDetail(dateType = dateType, dateId = dateId)) }
            )
        }

        LoadState.Error -> DateRoadErrorView()
    }
}

@Composable
fun PastScreen(
    padding: PaddingValues,
    pastUiState: PastContract.PastUiState = PastContract.PastUiState(),
    popBackStack: () -> Unit,
    navigateToTimelineDetail: (DateType, Int) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(paddingValues = padding)
            .background(color = DateRoadTheme.colors.white)
            .fillMaxSize()
    ) {
        DateRoadBasicTopBar(
            title = stringResource(id = R.string.top_bar_title_past),
            iconLeftResource = R.drawable.ic_top_bar_back_white,
            backGroundColor = DateRoadTheme.colors.white,
            onIconClick = popBackStack
        )
        if (pastUiState.dates.isEmpty()) {
            DateRoadEmptyView(emptyViewType = EmptyViewType.PAST)
        } else {
            LazyColumn(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 6.dp, bottom = 11.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(pastUiState.dates.size) { index ->
                    PastCard(
                        date = pastUiState.dates[index],
                        dateType = DateType.getDateTypeByIndex(index),
                        onClick = { navigateToTimelineDetail(DateType.getDateTypeByIndex(index), pastUiState.dates[index].dateId) }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PastScreenPreview() {
    DATEROADTheme {
        PastScreen(
            padding = PaddingValues(0.dp),
            popBackStack = { },
            navigateToTimelineDetail = { _, _ -> }
        )
    }
}
