package org.sopt.dateroad.presentation.ui.timeline

import org.sopt.dateroad.domain.model.Timeline
import org.sopt.dateroad.presentation.type.TimelineType
import org.sopt.dateroad.presentation.util.base.UiEvent
import org.sopt.dateroad.presentation.util.base.UiSideEffect
import org.sopt.dateroad.presentation.util.base.UiState
import org.sopt.dateroad.presentation.util.view.LoadState

class TimelineContract {
    data class TimelineUiState(
        val loadState: LoadState = LoadState.Idle,
        val timelines: List<Timeline> = listOf(),
        val currentPage: Int = 0,
        val showMaxTimelineCardModal: Boolean = false
    ) : UiState

    sealed interface TimelineSideEffect : UiSideEffect {
        data object NavigateToPast : TimelineSideEffect
        data object NavigateToEnroll : TimelineSideEffect
        data class NavigateToTimelineDetail(val timelineType: TimelineType, val timelineId: Int) : TimelineSideEffect
    }

    sealed class TimelineEvent : UiEvent {
        data class FetchTimeline(val loadState: LoadState, val timelines: List<Timeline>) : TimelineEvent()
        data class PageChanged(val page: Int) : TimelineEvent()
        data object ShowMaxItemsModal : TimelineEvent()
    }
}
