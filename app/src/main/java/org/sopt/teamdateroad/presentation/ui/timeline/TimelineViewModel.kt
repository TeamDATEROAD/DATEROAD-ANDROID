package org.sopt.teamdateroad.presentation.ui.timeline

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import org.sopt.teamdateroad.domain.type.TimelineTimeType
import org.sopt.teamdateroad.domain.usecase.GetTimelinesUseCase
import org.sopt.teamdateroad.presentation.util.base.BaseViewModel
import org.sopt.teamdateroad.presentation.util.view.LoadState

@HiltViewModel
class TimelineViewModel @Inject constructor(
    private val getTimelinesUseCase: GetTimelinesUseCase
) : BaseViewModel<TimelineContract.TimelineUiState, TimelineContract.TimelineSideEffect, TimelineContract.TimelineEvent>() {
    override fun createInitialState(): TimelineContract.TimelineUiState = TimelineContract.TimelineUiState()

    override suspend fun handleEvent(event: TimelineContract.TimelineEvent) {
        when (event) {
            is TimelineContract.TimelineEvent.FetchTimeline -> setState { copy(loadState = event.loadState, timelines = event.timelines) }
            is TimelineContract.TimelineEvent.PageChanged -> setState { copy(currentPage = event.page) }
            is TimelineContract.TimelineEvent.ShowMaxItemsModal -> setState { copy(showMaxTimelineCardModal = true) }
        }
    }

    fun fetchTimeline(timelineTimeType: TimelineTimeType) {
        viewModelScope.launch {
            setEvent(TimelineContract.TimelineEvent.FetchTimeline(loadState = LoadState.Loading, timelines = currentState.timelines))
            getTimelinesUseCase(timelineTimeType = timelineTimeType)
                .onSuccess { timelines ->
                    setEvent(TimelineContract.TimelineEvent.FetchTimeline(loadState = LoadState.Success, timelines = timelines))
                }
                .onFailure {
                    setEvent(TimelineContract.TimelineEvent.FetchTimeline(loadState = LoadState.Error, timelines = currentState.timelines))
                }
        }
    }
}
