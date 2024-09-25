package org.sopt.dateroad.presentation.ui.past

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import org.sopt.dateroad.domain.type.TimelineTimeType
import org.sopt.dateroad.domain.usecase.GetTimelinesUseCase
import org.sopt.dateroad.presentation.util.base.BaseViewModel
import org.sopt.dateroad.presentation.util.view.LoadState

@HiltViewModel
class PastViewModel @Inject constructor(
    private val getDatesUseCase: GetTimelinesUseCase
) : BaseViewModel<PastContract.PastUiState, PastContract.PastSideEffect, PastContract.PastEvent>() {
    override fun createInitialState(): PastContract.PastUiState =
        PastContract.PastUiState()

    override suspend fun handleEvent(event: PastContract.PastEvent) {
        when (event) {
            is PastContract.PastEvent.FetchPastDate -> setState { copy(loadState = event.loadState, timelines = event.timelines) }
        }
    }

    fun fetchPastDate(timelineTimeType: TimelineTimeType) {
        viewModelScope.launch {
            setEvent(PastContract.PastEvent.FetchPastDate(loadState = LoadState.Loading, timelines = currentState.timelines))
            getDatesUseCase(timelineTimeType = timelineTimeType).onSuccess { timelines ->
                setEvent(PastContract.PastEvent.FetchPastDate(loadState = LoadState.Success, timelines = timelines))
            }.onFailure {
                setEvent(PastContract.PastEvent.FetchPastDate(loadState = LoadState.Error, timelines = currentState.timelines))
            }
        }
    }
}
