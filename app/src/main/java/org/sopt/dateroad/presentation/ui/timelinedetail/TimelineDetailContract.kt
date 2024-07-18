package org.sopt.dateroad.presentation.ui.timelinedetail

import android.content.Context
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
        val dateDetail: DateDetail = DateDetail()
    ) : UiState

    sealed interface TimelineDetailSideEffect : UiSideEffect {
        data object PopBackStack : TimelineDetailSideEffect
    }

    sealed class TimelineDetailEvent : UiEvent {
        data class FetchTimelineDetail(val loadState: LoadState, val dateId: Int?) : TimelineDetailEvent()
        data class SetShowDeleteBottomSheet(val showDeleteBottomSheet: Boolean) : TimelineDetailEvent()
        data class SetShowDeleteDialog(val showDeleteDialog: Boolean) : TimelineDetailEvent()
        data class SetShowKakaoDialog(val showKakaoDialog: Boolean) : TimelineDetailEvent()
        data class SetSourceScreen(val sourceScreen: Boolean) : TimelineDetailEvent()
        data class ShareKakao(val context: Context, val dateDetail: DateDetail) : TimelineDetailEvent()
    }
}
