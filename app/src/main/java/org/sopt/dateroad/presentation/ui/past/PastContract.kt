package org.sopt.dateroad.presentation.ui.past

import org.sopt.dateroad.domain.model.Timeline
import org.sopt.dateroad.presentation.type.TimelineBackgroundType
import org.sopt.dateroad.presentation.util.base.UiEvent
import org.sopt.dateroad.presentation.util.base.UiSideEffect
import org.sopt.dateroad.presentation.util.base.UiState
import org.sopt.dateroad.presentation.util.view.LoadState

class PastContract {
    data class PastUiState(
        val loadState: LoadState = LoadState.Idle,
        val dates: List<Timeline> = listOf()
    ) : UiState

    sealed interface PastSideEffect : UiSideEffect {
        data object PopBackStack : PastSideEffect
        data class NavigateToTimelineDetail(val timelineBackgroundType: TimelineBackgroundType, val dateId: Int) : PastSideEffect
    }

    sealed class PastEvent : UiEvent {
        data class FetchPastDate(val loadState: LoadState, val dates: List<Timeline>) : PastEvent()
    }
}
