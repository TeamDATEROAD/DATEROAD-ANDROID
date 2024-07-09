package org.sopt.dateroad.presentation.ui.pointhistory

import dagger.hilt.android.lifecycle.HiltViewModel
import org.sopt.dateroad.domain.model.Point
import org.sopt.dateroad.domain.model.PointHistory
import org.sopt.dateroad.presentation.util.base.BaseViewModel
import org.sopt.dateroad.presentation.util.view.LoadState
import javax.inject.Inject

@HiltViewModel
class PointHistoryViewModel @Inject constructor(

) : BaseViewModel<PointHistoryContract.PointHistoryUiState, PointHistoryContract.PointHistorySideEffect, PointHistoryContract.PointHistoryEvent>() {
    override fun createInitialState(): PointHistoryContract.PointHistoryUiState =
        PointHistoryContract.PointHistoryUiState()

    override suspend fun handleEvent(event: PointHistoryContract.PointHistoryEvent) {
        when (event) {
            is PointHistoryContract.PointHistoryEvent.FetchPointHistory -> fetchPointHistory()
            is PointHistoryContract.PointHistoryEvent.OnTabBarClicked -> setState { copy(pointHistoryTabType = pointHistoryTabType) }
        }
    }

    private fun fetchPointHistory() {
        setState {
            copy(
                loadState = LoadState.Success, pointHistory = PointHistory(
                    gained = listOf(
                        Point(point = "+150", description = "서버의 바다여행", createdAt = "2023.12.31"),
                        Point(point = "+150", description = "서버의 바다여행", createdAt = "2023.12.31"),
                        Point(point = "+150", description = "서버의 바다여행", createdAt = "2023.12.31")
                    ),
                    used = listOf()
                )
            )
        }
    }
}