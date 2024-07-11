package org.sopt.dateroad.presentation.ui.timeline

import org.sopt.dateroad.domain.model.Date
import org.sopt.dateroad.presentation.type.TimelineDetailType
import org.sopt.dateroad.presentation.util.base.UiEvent
import org.sopt.dateroad.presentation.util.base.UiSideEffect
import org.sopt.dateroad.presentation.util.base.UiState
import org.sopt.dateroad.presentation.util.view.LoadState

class TimelineContract {
    data class TimelineUiState(
        val loadState: LoadState = LoadState.Idle,
        val timelineDetailType: TimelineDetailType = TimelineDetailType.TIMELINE,
        val dates: List<Date> = listOf(),
        val currentPage: Int = 0,
        val showModal: Boolean = false
    ) : UiState

    sealed interface TimelineSideEffect : UiSideEffect {
        object MoveToEnroll : TimelineSideEffect
        object ShowMaxItemsModal : TimelineSideEffect
    }

    sealed class TimelineEvent : UiEvent {
        object FetchTimeline : TimelineEvent()
        data class PageChanged(val page: Int) : TimelineEvent()
        object AddDateCardClicked : TimelineEvent()
    }
}
