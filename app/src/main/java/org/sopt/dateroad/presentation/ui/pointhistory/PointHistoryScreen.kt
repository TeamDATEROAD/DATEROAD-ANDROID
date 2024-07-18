package org.sopt.dateroad.presentation.ui.pointhistory

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
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
import org.sopt.dateroad.domain.model.Point
import org.sopt.dateroad.domain.model.PointHistory
import org.sopt.dateroad.domain.model.UserPoint
import org.sopt.dateroad.presentation.type.EmptyViewType
import org.sopt.dateroad.presentation.type.PointHistoryTabType
import org.sopt.dateroad.presentation.ui.component.emptyview.DateRoadEmptyView
import org.sopt.dateroad.presentation.ui.component.tabbar.DateRoadTabBar
import org.sopt.dateroad.presentation.ui.component.tabbar.DateRoadTabTitle
import org.sopt.dateroad.presentation.ui.component.topbar.DateRoadBasicTopBar
import org.sopt.dateroad.presentation.ui.pointhistory.component.PointHistoryCard
import org.sopt.dateroad.presentation.ui.pointhistory.component.PointHistoryPointBox
import org.sopt.dateroad.presentation.util.view.LoadState
import org.sopt.dateroad.ui.theme.DATEROADTheme
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun PointHistoryRoute(
    padding: PaddingValues,
    viewModel: PointHistoryViewModel = hiltViewModel(),
    popBackStack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(Unit) {
        viewModel.fetchPointHistory()
        viewModel.fetchUserPoint()
    }

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { pointHistorySideEffect ->
                when (pointHistorySideEffect) {
                    is PointHistoryContract.PointHistorySideEffect.PopBackStack -> popBackStack()
                }
            }
    }

    when (uiState.loadState) {
        LoadState.Success -> {
            PointHistoryScreen(
                padding = padding,
                pointHistoryUiState = uiState,
                onTabBarClicked = { pointHistoryTabType ->
                    viewModel.setEvent(
                        PointHistoryContract.PointHistoryEvent.OnTabBarClicked(pointHistoryTabType)
                    )
                },
                onTopBarIconClicked = { viewModel.setSideEffect(PointHistoryContract.PointHistorySideEffect.PopBackStack) }
            )
        }

        else -> Unit
    }
}

@Composable
fun PointHistoryScreen(
    padding: PaddingValues,
    pointHistoryUiState: PointHistoryContract.PointHistoryUiState = PointHistoryContract.PointHistoryUiState(),
    onTabBarClicked: (PointHistoryTabType) -> Unit,
    onTopBarIconClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(paddingValues = padding)
            .background(color = DateRoadTheme.colors.white)
            .fillMaxSize()
    ) {
        DateRoadBasicTopBar(
            title = stringResource(id = R.string.top_bar_title_point_history),
            iconLeftResource = R.drawable.ic_top_bar_back_white,
            backGroundColor = DateRoadTheme.colors.white,
            onIconClick = onTopBarIconClicked
        )
        Spacer(modifier = Modifier.height(22.dp))
        PointHistoryPointBox(
            modifier = Modifier.padding(horizontal = 16.dp),
            userPoint = pointHistoryUiState.userPoint
        )
        Spacer(modifier = Modifier.height(16.dp))
        DateRoadTabBar(
            selectedTabPosition = pointHistoryUiState.pointHistoryTabType.position
        ) {
            PointHistoryTabType.entries.forEachIndexed { index, pointHistoryTabType ->
                DateRoadTabTitle(
                    title = stringResource(id = pointHistoryTabType.titleRes),
                    selected = index == pointHistoryUiState.pointHistoryTabType.position,
                    position = index,
                    onClick = {
                        onTabBarClicked(pointHistoryTabType)
                    }
                )
            }
        }
        LazyColumn {
            val pointHistory = when (pointHistoryUiState.pointHistoryTabType) {
                PointHistoryTabType.GAINED_HISTORY -> pointHistoryUiState.pointHistory.gained
                PointHistoryTabType.USED_HISTORY -> pointHistoryUiState.pointHistory.used
            }
            if (pointHistory.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier
                            .fillParentMaxSize()
                    ) {
                        DateRoadEmptyView(
                            emptyViewType = when (pointHistoryUiState.pointHistoryTabType) {
                                PointHistoryTabType.USED_HISTORY -> EmptyViewType.POINT_HISTORY_USED_HISTORY
                                PointHistoryTabType.GAINED_HISTORY -> EmptyViewType.POINT_HISTORY_GAINED_HISTORY
                            }
                        )
                    }
                }
            }
            items(pointHistory.size) { index ->
                PointHistoryCard(point = pointHistory[index])
                if (index < pointHistory.size - 1) {
                    HorizontalDivider(
                        color = DateRoadTheme.colors.gray100,
                        thickness = 1.dp
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PointHistoryPreview() {
    DATEROADTheme {
        PointHistoryScreen(
            padding = PaddingValues(0.dp),
            pointHistoryUiState = PointHistoryContract.PointHistoryUiState(
                userPoint = UserPoint(),
                loadState = LoadState.Success,
                pointHistory = PointHistory(
                    gained = listOf(
                        Point(point = "+150", description = "서버의 바다여행", createdAt = "2023.12.31"),
                        Point(point = "+150", description = "서버의 바다여행", createdAt = "2023.12.31"),
                        Point(point = "+150", description = "서버의 바다여행", createdAt = "2023.12.31")
                    ),
                    used = listOf()
                )
            ),
            onTabBarClicked = {},
            onTopBarIconClicked = {}
        )
    }
}
