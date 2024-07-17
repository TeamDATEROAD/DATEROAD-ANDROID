package org.sopt.dateroad.presentation.ui.timelinedetail

import org.sopt.dateroad.domain.model.DateDetail
import org.sopt.dateroad.presentation.util.base.UiEvent
import org.sopt.dateroad.presentation.util.base.UiSideEffect
import org.sopt.dateroad.presentation.util.base.UiState
import org.sopt.dateroad.presentation.util.view.LoadState

class TimelineDetailContract {
    data class TimelineDetailUiState(
        val loadState: LoadState = LoadState.Idle,
        val sourceScreen: Boolean = false,
        val showKakaoDialog: Boolean = false,
        val showDeleteBottomSheet: Boolean = false,
        val showDeleteDialog: Boolean = false,
        val dateDetail: DateDetail = DateDetail(),
        val isDeleting: Boolean = false
    ) : UiState

    sealed interface TimelineDetailSideEffect : UiSideEffect {
        data object PopBackStack : TimelineDetailSideEffect
        // data class NavigateToEnroll(val dateId: Int) : TimelineDetailSideEffect
    }

    sealed class TimelineDetailEvent : UiEvent {
        data class FetchTimelineDetail(val dateId: Int) : TimelineDetailEvent()
        data class SetShowDeleteBottomSheet(val showDeleteBottomSheet: Boolean) : TimelineDetailEvent()
        data class SetShowDeleteDialog(val showDeleteDialog: Boolean) : TimelineDetailEvent()
        data class SetShowKakaoDialog(val showKakaoDialog: Boolean) : TimelineDetailEvent()
        data class SetSourceScreen(val sourceScreen: Boolean) : TimelineDetailEvent()
        data class DeleteDate(val dateId: Int) : TimelineDetailEvent()
    }
}
