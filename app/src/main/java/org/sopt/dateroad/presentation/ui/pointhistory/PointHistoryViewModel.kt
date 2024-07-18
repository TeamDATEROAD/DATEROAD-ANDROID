package org.sopt.dateroad.presentation.ui.pointhistory

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import org.sopt.dateroad.domain.usecase.GetPointHistoryUseCase
import org.sopt.dateroad.presentation.util.base.BaseViewModel
import org.sopt.dateroad.presentation.util.view.LoadState

@HiltViewModel
class PointHistoryViewModel @Inject constructor(
    private val getPointHistoryUseCase: GetPointHistoryUseCase
) : BaseViewModel<PointHistoryContract.PointHistoryUiState, PointHistoryContract.PointHistorySideEffect, PointHistoryContract.PointHistoryEvent>() {
    override fun createInitialState(): PointHistoryContract.PointHistoryUiState =
        PointHistoryContract.PointHistoryUiState()

    override suspend fun handleEvent(event: PointHistoryContract.PointHistoryEvent) {
        when (event) {
            is PointHistoryContract.PointHistoryEvent.FetchPointHistory -> setState { copy(loadState = event.loadState, pointHistory = event.pointHistory) }
            is PointHistoryContract.PointHistoryEvent.OnTabBarClicked -> setState { copy(pointHistoryTabType = event.pointHistoryTabType) }
        }
    }

    fun fetchPointHistory() {
        viewModelScope.launch {
            setEvent(
                PointHistoryContract.PointHistoryEvent.FetchPointHistory(loadState = LoadState.Loading, pointHistory = currentState.pointHistory),
                org.sopt.dateroad.presentation.ui.home.HomeContract.HomeEvent.FetchRemainingPoints(loadState = org.sopt.dateroad.presentation.util.view.LoadState.Loading, remainingPoints = currentState.remainingPoints)
            )
            getPointHistoryUseCase().onSuccess { pointHistory ->
                setEvent(
                    PointHistoryContract.PointHistoryEvent.FetchPointHistory(loadState = LoadState.Success, pointHistory = pointHistory),
                    org.sopt.dateroad.presentation.ui.home.HomeContract.HomeEvent.FetchRemainingPoints(loadState = org.sopt.dateroad.presentation.util.view.LoadState.Loading, remainingPoints = currentState.remainingPoints)
                )
            }.onFailure {
                setEvent(
                    PointHistoryContract.PointHistoryEvent.FetchPointHistory(loadState = LoadState.Error, pointHistory = currentState.pointHistory),
                    org.sopt.dateroad.presentation.ui.home.HomeContract.HomeEvent.FetchRemainingPoints(loadState = org.sopt.dateroad.presentation.util.view.LoadState.Loading, remainingPoints = currentState.remainingPoints)
                )
            }
        }
    }
}
