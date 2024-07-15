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
        data object NavigateToPast : TimelineSideEffect
        data object NavigateToEnroll : TimelineSideEffect
        data class NavigateToTimelineDetail(val dateType: DateType, val dateId: Int) : TimelineSideEffect
    }

    sealed class TimelineEvent : UiEvent {
        data class FetchTimeline(val loadState: LoadState, val dates: List<Date>) : TimelineEvent()
        data class PageChanged(val page: Int) : TimelineEvent()
        data object AddDateCardClicked : TimelineEvent()
        data object ShowMaxItemsModal : TimelineEvent()
    }
}
