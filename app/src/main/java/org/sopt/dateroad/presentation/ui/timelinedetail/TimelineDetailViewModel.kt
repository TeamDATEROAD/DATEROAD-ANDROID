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
            is TimelineDetailContract.TimelineDetailEvent.FetchTimelineDetail -> fetchDateDetail(event.dateId)
            is TimelineDetailContract.TimelineDetailEvent.SetTimelineDetail -> setTimelineDetail(event.loadState, event.dateDetail)
            is TimelineDetailContract.TimelineDetailEvent.SetShowDeleteBottomSheet -> setState { copy(showDeleteBottomSheet = event.showDeleteBottomSheet) }
            is TimelineDetailContract.TimelineDetailEvent.SetShowDeleteDialog -> setState { copy(showDeleteDialog = event.showDeleteDialog) }
            is TimelineDetailContract.TimelineDetailEvent.SetShowKakaoDialog -> setState { copy(showKakaoDialog = event.showKakaoDialog) }
            is TimelineDetailContract.TimelineDetailEvent.SetSourceScreen -> setState { copy(sourceScreen = event.sourceScreen) }
            is TimelineDetailContract.TimelineDetailEvent.DeleteDate -> deleteDate(event.dateId)
            is TimelineDetailContract.TimelineDetailEvent.SetLoadState -> setState { copy(loadState = event.loadState) }
            is TimelineDetailContract.TimelineDetailEvent.SetSideEffect -> setSideEffect(event.sideEffect)
        }
    }

    fun fetchTimelineDetail(dateId: Int) {
        setEvent(TimelineDetailContract.TimelineDetailEvent.FetchTimelineDetail(dateId))
    }

    fun setSourceScreen(source: Boolean) {
        setEvent(TimelineDetailContract.TimelineDetailEvent.SetSourceScreen(sourceScreen = source))
    }

    fun onDeleteConfirm(dateId: Int) {
        setEvent(TimelineDetailContract.TimelineDetailEvent.DeleteDate(dateId))
    }

    private fun fetchDateDetail(dateId: Int) {
        viewModelScope.launch {
            setEvent(TimelineDetailContract.TimelineDetailEvent.SetLoadState(LoadState.Loading))
            getDateDetailUseCase(dateId).onSuccess { dateDetail ->
                setEvent(TimelineDetailContract.TimelineDetailEvent.SetTimelineDetail(LoadState.Success, dateDetail))
            }.onFailure {
                setEvent(TimelineDetailContract.TimelineDetailEvent.SetTimelineDetail(LoadState.Error))
            }
        }
    }

    private fun deleteDate(dateId: Int) {
        viewModelScope.launch {
            setEvent(TimelineDetailContract.TimelineDetailEvent.SetLoadState(LoadState.Loading))
            deleteDateUseCase(dateId).onSuccess {
                setEvent(TimelineDetailContract.TimelineDetailEvent.SetTimelineDetail(LoadState.Success))
                setEvent(TimelineDetailContract.TimelineDetailEvent.SetSideEffect(TimelineDetailContract.TimelineDetailSideEffect.PopBackStack))
            }.onFailure {
                setEvent(TimelineDetailContract.TimelineDetailEvent.SetTimelineDetail(LoadState.Error))
            }
        }
    }

    private fun setTimelineDetail(loadState: LoadState, dateDetail: DateDetail?) {
        setEvent(TimelineDetailContract.TimelineDetailEvent.SetTimelineDetail(loadState, dateDetail))
    }
}
