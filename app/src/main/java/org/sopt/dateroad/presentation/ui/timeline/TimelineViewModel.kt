package org.sopt.dateroad.presentation.ui.timeline

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import org.sopt.dateroad.domain.type.DateTimeType
import org.sopt.dateroad.domain.usecase.GetDatesUseCase
import org.sopt.dateroad.presentation.util.base.BaseViewModel
import org.sopt.dateroad.presentation.util.view.LoadState

@HiltViewModel
class TimelineViewModel @Inject constructor(
    private val getDatesUseCase: GetDatesUseCase
) : BaseViewModel<TimelineContract.TimelineUiState, TimelineContract.TimelineSideEffect, TimelineContract.TimelineEvent>() {
    override fun createInitialState(): TimelineContract.TimelineUiState = TimelineContract.TimelineUiState()

    override suspend fun handleEvent(event: TimelineContract.TimelineEvent) {
        when (event) {
            is TimelineContract.TimelineEvent.FetchTimeline -> setState { copy(loadState = event.loadState, dates = event.dates) }
            is TimelineContract.TimelineEvent.PageChanged -> setState { copy(currentPage = event.page) }
            is TimelineContract.TimelineEvent.ShowMaxItemsModal -> setState { copy(showMaxDateCardModal = true) }
        }
    }

    fun fetchTimeline(time: DateTimeType) {
        viewModelScope.launch {
            setEvent(TimelineContract.TimelineEvent.FetchTimeline(loadState = LoadState.Loading, dates = currentState.dates))
            getDatesUseCase(time = time)
                .onSuccess { dates ->
                    setEvent(TimelineContract.TimelineEvent.FetchTimeline(loadState = LoadState.Success, dates = dates))
                }
                .onFailure {
                    setEvent(TimelineContract.TimelineEvent.FetchTimeline(loadState = LoadState.Error, dates = currentState.dates))
                }
        }
    }
}
