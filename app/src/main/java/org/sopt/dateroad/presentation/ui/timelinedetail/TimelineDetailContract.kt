package org.sopt.dateroad.presentation.ui.timelinedetail

import android.content.Context
import org.sopt.dateroad.domain.model.TimelineDetail
import org.sopt.dateroad.presentation.util.base.UiEvent
import org.sopt.dateroad.presentation.util.base.UiSideEffect
import org.sopt.dateroad.presentation.util.base.UiState
import org.sopt.dateroad.presentation.util.view.LoadState

class TimelineDetailContract {
    data class TimelineDetailUiState(
        val loadState: LoadState = LoadState.Idle,
        val deleteLoadState: LoadState = LoadState.Idle,
        val showKakaoDialog: Boolean = false,
        val showDeleteBottomSheet: Boolean = false,
        val showDeleteDialog: Boolean = false,
        val timelineDetail: TimelineDetail = TimelineDetail()
    ) : UiState

    sealed interface TimelineDetailSideEffect : UiSideEffect {
        data object PopBackStack : TimelineDetailSideEffect
        data class NavigateToEnroll(val id: Int) : TimelineDetailSideEffect
    }

    sealed class TimelineDetailEvent : UiEvent {
        data class SetTimelineDetail(val loadState: LoadState, val timelineDetail: TimelineDetail) : TimelineDetailEvent()
        data class SetShowDeleteBottomSheet(val showDeleteBottomSheet: Boolean) : TimelineDetailEvent()
        data class SetShowDeleteDialog(val showDeleteDialog: Boolean) : TimelineDetailEvent()
        data class SetShowKakaoDialog(val showKakaoDialog: Boolean) : TimelineDetailEvent()
        data class DeleteTimeline(val deleteLoadState: LoadState) : TimelineDetailEvent()
        data class SetLoadState(val loadState: LoadState) : TimelineDetailEvent()
        data class SetSideEffect(val sideEffect: TimelineDetailSideEffect) : TimelineDetailEvent()
        data class ShareKakao(val context: Context, val timelineDetail: TimelineDetail) : TimelineDetailEvent()
    }
}
