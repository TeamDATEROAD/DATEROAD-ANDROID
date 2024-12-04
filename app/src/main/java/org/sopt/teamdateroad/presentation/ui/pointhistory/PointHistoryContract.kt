package org.sopt.teamdateroad.presentation.ui.pointhistory

import org.sopt.teamdateroad.domain.model.PointHistory
import org.sopt.teamdateroad.domain.model.UserPoint
import org.sopt.teamdateroad.presentation.type.PointHistoryTabType
import org.sopt.teamdateroad.presentation.util.base.UiEvent
import org.sopt.teamdateroad.presentation.util.base.UiSideEffect
import org.sopt.teamdateroad.presentation.util.base.UiState
import org.sopt.teamdateroad.presentation.util.view.LoadState

class PointHistoryContract {
    data class PointHistoryUiState(
        val loadState: LoadState = LoadState.Idle,
        val userPoint: UserPoint = UserPoint(),
        val pointHistoryTabType: PointHistoryTabType = PointHistoryTabType.GAINED_HISTORY,
        val pointHistory: PointHistory = PointHistory()
    ) : UiState

    sealed interface PointHistorySideEffect : UiSideEffect {
        data object PopBackStack : PointHistorySideEffect
    }

    sealed class PointHistoryEvent : UiEvent {
        data class FetchPointHistory(val loadState: LoadState, val pointHistory: PointHistory) : PointHistoryEvent()
        data class FetchUserPoint(val loadState: LoadState, val userPoint: UserPoint) : PointHistoryEvent()
        data class OnTabBarClicked(val pointHistoryTabType: PointHistoryTabType) : PointHistoryEvent()
    }
}
