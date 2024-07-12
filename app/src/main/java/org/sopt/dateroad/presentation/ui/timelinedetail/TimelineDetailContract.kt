package org.sopt.dateroad.presentation.ui.timelinedetail

import org.sopt.dateroad.domain.model.DateDetail
import org.sopt.dateroad.presentation.util.base.UiEvent
import org.sopt.dateroad.presentation.util.base.UiSideEffect
import org.sopt.dateroad.presentation.util.base.UiState
import org.sopt.dateroad.presentation.util.view.LoadState

class TimelineDetailContract {
    data class TimelineDetailUiState(
        val loadState: LoadState = LoadState.Idle,
        val showKakaoDialog: Boolean = false,
        val showDeleteBottomSheet: Boolean = false,
        val showDeleteDialog: Boolean = false,
        val dateDetail: DateDetail = DateDetail()
    ) : UiState

    sealed interface TimelineDetailSideEffect : UiSideEffect {
        data object PopBackStack : TimelineDetailSideEffect
    }

    sealed class TimelineDetailEvent : UiEvent {
        data class FetchTimelineDetail(val dateId: Int) : TimelineDetailEvent()
        data object ShowDeleteBottomSheet : TimelineDetailEvent()
        data object ShowDeleteDialog : TimelineDetailEvent()
        data object ShowKaKaoModal : TimelineDetailEvent()
    }
}