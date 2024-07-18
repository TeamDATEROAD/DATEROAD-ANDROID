package org.sopt.dateroad.presentation.ui.timelinedetail

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import org.sopt.dateroad.domain.model.DateDetail
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
            is TimelineDetailContract.TimelineDetailEvent.SetTimelineDetail -> setState { copy(loadState = event.loadState, dateDetail = event.dateDetail) }
            is TimelineDetailContract.TimelineDetailEvent.SetShowDeleteBottomSheet -> setState { copy(showDeleteBottomSheet = event.showDeleteBottomSheet) }
            is TimelineDetailContract.TimelineDetailEvent.SetShowDeleteDialog -> setState { copy(showDeleteDialog = event.showDeleteDialog) }
            is TimelineDetailContract.TimelineDetailEvent.SetShowKakaoDialog -> setState { copy(showKakaoDialog = event.showKakaoDialog) }
            is TimelineDetailContract.TimelineDetailEvent.DeleteDate -> setState { copy(deleteLoadState = event.deleteLoadState) }
            is TimelineDetailContract.TimelineDetailEvent.SetLoadState -> setState { copy(loadState = event.loadState) }
            is TimelineDetailContract.TimelineDetailEvent.SetSideEffect -> setSideEffect(event.sideEffect)
        }
    }

    fun fetchDateDetail(dateId: Int) {
        viewModelScope.launch {
            setEvent(TimelineDetailContract.TimelineDetailEvent.SetTimelineDetail(loadState = LoadState.Loading, dateDetail = currentState.dateDetail))
            getDateDetailUseCase(dateId).onSuccess { dateDetail ->
                setEvent(TimelineDetailContract.TimelineDetailEvent.SetTimelineDetail(loadState = LoadState.Success, dateDetail = dateDetail))
            }.onFailure {
                setEvent(TimelineDetailContract.TimelineDetailEvent.SetTimelineDetail(loadState = LoadState.Error, dateDetail = currentState.dateDetail))
            }
        }
    }

    fun deleteDate(dateId: Int) {
        viewModelScope.launch {
            setEvent(TimelineDetailContract.TimelineDetailEvent.DeleteDate(deleteLoadState = LoadState.Loading))
            deleteDateUseCase(dateId).onSuccess {
                setEvent(TimelineDetailContract.TimelineDetailEvent.DeleteDate(deleteLoadState = LoadState.Success))
            }.onFailure {
                setEvent(TimelineDetailContract.TimelineDetailEvent.DeleteDate(deleteLoadState = LoadState.Error))
            }
        }
    }
}
