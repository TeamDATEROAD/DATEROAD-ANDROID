package org.sopt.dateroad.presentation.ui.timeline

import org.sopt.dateroad.domain.model.Date
import org.sopt.dateroad.presentation.util.base.UiEvent
import org.sopt.dateroad.presentation.util.base.UiSideEffect
import org.sopt.dateroad.presentation.util.base.UiState
import org.sopt.dateroad.presentation.util.view.LoadState

class TimelineContract {
    data class TimelineUiState(
        val loadState: LoadState = LoadState.Idle,
        val dates: List<Date> = listOf(),
        val showMaxDateCardModal: Boolean = false
    ) : UiState

    sealed interface TimelineSideEffect : UiSideEffect {
        data object NavigationToPast : TimelineSideEffect
        data object NavigateToEnroll : TimelineSideEffect
    }

    sealed class TimelineEvent : UiEvent {
        data object FetchTimeline : TimelineEvent()
        data object AddDateCardClicked : TimelineEvent()
        data object ShowMaxItemsModal : TimelineEvent()
    }
}
