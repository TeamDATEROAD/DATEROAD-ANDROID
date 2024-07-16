package org.sopt.dateroad.presentation.ui.timelinedetail

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import org.sopt.dateroad.data.mapper.todomain.toDomain
import org.sopt.dateroad.domain.usecase.DeleteDateUseCase
import org.sopt.dateroad.domain.usecase.GetDateDetailUseCase
import org.sopt.dateroad.presentation.util.base.BaseViewModel
import org.sopt.dateroad.presentation.util.view.LoadState

@HiltViewModel
class TimelineDetailViewModel @Inject constructor(
    private val deleteDateUseCase: DeleteDateUseCase,
    private val getDateDetailUseCase: GetDateDetailUseCase
) : BaseViewModel<TimelineDetailContract.TimelineDetailUiState, TimelineDetailContract.TimelineDetailSideEffect, TimelineDetailContract.TimelineDetailEvent>() {
    override fun createInitialState(): TimelineDetailContract.TimelineDetailUiState = TimelineDetailContract.TimelineDetailUiState()

    override suspend fun handleEvent(event: TimelineDetailContract.TimelineDetailEvent) {
        when (event) {
            is TimelineDetailContract.TimelineDetailEvent.FetchTimelineDetail -> fetchDateDetail(event.dateId)
            is TimelineDetailContract.TimelineDetailEvent.SetShowDeleteBottomSheet -> setState { copy(showDeleteBottomSheet = event.showDeleteBottomSheet) }
            is TimelineDetailContract.TimelineDetailEvent.SetShowDeleteDialog -> setState { copy(showDeleteDialog = event.showDeleteDialog) }
            is TimelineDetailContract.TimelineDetailEvent.SetShowKakaoDialog -> setState { copy(showKakaoDialog = event.showKakaoDialog) }
            is TimelineDetailContract.TimelineDetailEvent.SetSourceScreen -> setState { copy(sourceScreen = event.sourceScreen) }
            is TimelineDetailContract.TimelineDetailEvent.DeleteDate -> deleteDate(event.dateId)
        }
    }

    fun fetchTimelineDetail(dateId: Long) {
        setEvent(TimelineDetailContract.TimelineDetailEvent.FetchTimelineDetail(dateId))
    }

    fun setShowDeleteBottomSheet(show: Boolean) {
        setEvent(TimelineDetailContract.TimelineDetailEvent.SetShowDeleteBottomSheet(showDeleteBottomSheet = show))
    }

    fun setShowDeleteDialog(show: Boolean) {
        setEvent(TimelineDetailContract.TimelineDetailEvent.SetShowDeleteDialog(showDeleteDialog = show))
    }

    fun setShowKakaoDialog(show: Boolean) {
        setEvent(TimelineDetailContract.TimelineDetailEvent.SetShowKakaoDialog(showKakaoDialog = show))
    }

    fun setSourceScreen(source: Boolean) {
        setEvent(TimelineDetailContract.TimelineDetailEvent.SetSourceScreen(sourceScreen = source))
    }

    fun onDeleteConfirm(dateId: Long) {
        setEvent(TimelineDetailContract.TimelineDetailEvent.DeleteDate(dateId))
    }

    private fun fetchDateDetail(dateId: Long) {
        viewModelScope.launch {
            setState { copy(loadState = LoadState.Loading) }
            getDateDetailUseCase(dateId).onSuccess { dateDetail ->
                setState { copy(loadState = LoadState.Success, dateDetail = dateDetail.toDomain()) }
            }.onFailure {
                setState { copy(loadState = LoadState.Error) }
            }
        }
    }

    private fun deleteDate(dateId: Long) {
        viewModelScope.launch {
            setState { copy(isDeleting = true) }
            val result = deleteDateUseCase(dateId)
            result.onSuccess {
                setState { copy(isDeleting = false) }
                setSideEffect(TimelineDetailContract.TimelineDetailSideEffect.PopBackStack)
            }.onFailure {
                setState { copy(isDeleting = false) }
            }
        }
    }
}
