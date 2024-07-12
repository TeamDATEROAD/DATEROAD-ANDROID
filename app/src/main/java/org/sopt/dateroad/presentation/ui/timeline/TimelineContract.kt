package org.sopt.dateroad.presentation.ui.timeline

import org.sopt.dateroad.domain.model.Date
import org.sopt.dateroad.presentation.type.DateType
import org.sopt.dateroad.presentation.util.base.UiEvent
import org.sopt.dateroad.presentation.util.base.UiSideEffect
import org.sopt.dateroad.presentation.util.base.UiState
import org.sopt.dateroad.presentation.util.view.LoadState

class TimelineContract {
    data class TimelineUiState(
        val loadState: LoadState = LoadState.Idle,
        val dates: List<Date> = listOf(),
        val currentPage: Int = 0,
        val showMaxDateCardModal: Boolean = false
    ) : UiState

    sealed interface TimelineSideEffect : UiSideEffect {
        object NavigateToEnroll : TimelineSideEffect
        data class NavigateToTimelineDetail(val dateType: DateType) : TimelineSideEffect
    }

    sealed class TimelineEvent : UiEvent {
        data object FetchTimeline : TimelineEvent()
        data class PageChanged(val page: Int) : TimelineEvent()
        data object AddDateCardClicked : TimelineEvent()
        data object ShowMaxItemsModal : TimelineEvent()
    }
}
