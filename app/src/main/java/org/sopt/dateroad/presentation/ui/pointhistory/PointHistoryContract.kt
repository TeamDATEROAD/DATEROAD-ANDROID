package org.sopt.dateroad.presentation.ui.pointhistory

import org.sopt.dateroad.domain.model.PointHistory
import org.sopt.dateroad.presentation.type.PointHistoryTabType
import org.sopt.dateroad.presentation.util.base.UiEvent
import org.sopt.dateroad.presentation.util.base.UiSideEffect
import org.sopt.dateroad.presentation.util.base.UiState
import org.sopt.dateroad.presentation.util.view.LoadState

class PointHistoryContract {
    data class PointHistoryUiState(
        val loadState: LoadState = LoadState.Idle,
        val name: String = "",
        val point: Int = 0,
        val pointHistoryTabType: PointHistoryTabType = PointHistoryTabType.GAINED_HISTORY,
        val pointHistory: PointHistory = PointHistory()
    ) : UiState

    sealed interface PointHistorySideEffect : UiSideEffect {
        data object PopBackStack : PointHistorySideEffect
    }

    sealed class PointHistoryEvent : UiEvent {
        data object FetchPointHistory : PointHistoryEvent()
        data class OnTabBarClicked(val pointHistoryTabType: PointHistoryTabType) : PointHistoryEvent()
    }
}
