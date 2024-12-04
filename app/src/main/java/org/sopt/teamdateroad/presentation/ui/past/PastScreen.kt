package org.sopt.teamdateroad.presentation.ui.past

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
import org.sopt.teamdateroad.R
import org.sopt.teamdateroad.domain.type.TimelineTimeType
import org.sopt.teamdateroad.presentation.type.EmptyViewType
import org.sopt.teamdateroad.presentation.type.TimelineType
import org.sopt.teamdateroad.presentation.ui.component.topbar.DateRoadBasicTopBar
import org.sopt.teamdateroad.presentation.ui.component.view.DateRoadEmptyView
import org.sopt.teamdateroad.presentation.ui.component.view.DateRoadErrorView
import org.sopt.teamdateroad.presentation.ui.component.view.DateRoadIdleView
import org.sopt.teamdateroad.presentation.ui.component.view.DateRoadLoadingView
import org.sopt.teamdateroad.presentation.ui.past.component.PastCard
import org.sopt.teamdateroad.presentation.util.view.LoadState
import org.sopt.teamdateroad.ui.theme.DATEROADTheme
import org.sopt.teamdateroad.ui.theme.DateRoadTheme

@Composable
fun PastRoute(
    padding: PaddingValues,
    viewModel: PastViewModel = hiltViewModel(),
    popBackStack: () -> Unit,
    navigateToTimelineDetail: (TimelineType, Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(Unit) {
        viewModel.fetchPastDate(TimelineTimeType.PAST)
    }

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle).collect { pastSideEffect ->
            when (pastSideEffect) {
                is PastContract.PastSideEffect.PopBackStack -> popBackStack()
                is PastContract.PastSideEffect.NavigateToTimelineDetail -> navigateToTimelineDetail(pastSideEffect.timelineType, pastSideEffect.timelineId)
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
                navigateToTimelineDetail = { timelineType, timelineId -> viewModel.setSideEffect(PastContract.PastSideEffect.NavigateToTimelineDetail(timelineType = timelineType, timelineId = timelineId)) }
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
    navigateToTimelineDetail: (TimelineType, Int) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(paddingValues = padding)
            .background(color = DateRoadTheme.colors.white)
            .fillMaxSize()
    ) {
        DateRoadBasicTopBar(
            title = stringResource(id = R.string.top_bar_title_past),
            leftIconResource = R.drawable.ic_top_bar_back_white,
            backGroundColor = DateRoadTheme.colors.white,
            onLeftIconClick = popBackStack
        )
        if (pastUiState.timelines.isEmpty()) {
            DateRoadEmptyView(emptyViewType = EmptyViewType.PAST)
        } else {
            LazyColumn(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 6.dp, bottom = 11.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(pastUiState.timelines.size) { index ->
                    PastCard(
                        timeline = pastUiState.timelines[index],
                        timelineType = TimelineType.getTimelineTypeByIndex(index),
                        onClick = { navigateToTimelineDetail(TimelineType.getTimelineTypeByIndex(index), pastUiState.timelines[index].timelineId) }
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
