package org.sopt.dateroad.presentation.ui.mycourse

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import org.sopt.dateroad.domain.usecase.GetMyCourseEnrollUseCase
import org.sopt.dateroad.domain.usecase.GetMyCourseReadUseCase
import org.sopt.dateroad.presentation.util.base.BaseViewModel
import org.sopt.dateroad.presentation.util.view.LoadState

@HiltViewModel
class MyCourseViewModel @Inject constructor(
    private val getMyCourseEnrollUseCase: GetMyCourseEnrollUseCase,
    private val getMyCourseReadUseCase: GetMyCourseReadUseCase
) : BaseViewModel<MyCourseContract.MyCourseUiState, MyCourseContract.MyCourseSideEffect, MyCourseContract.MyCourseEvent>() {
    override fun createInitialState(): MyCourseContract.MyCourseUiState = MyCourseContract.MyCourseUiState()

    override suspend fun handleEvent(event: MyCourseContract.MyCourseEvent) {
        when (event) {
            is MyCourseContract.MyCourseEvent.FetchMyCourseEnroll -> setState { copy(loadState = event.loadState, courses = event.courses) }
            is MyCourseContract.MyCourseEvent.FetchMyCourseRead -> setState { copy(loadState = event.loadState, courses = event.courses) }
            is MyCourseContract.MyCourseEvent.SetMyCourseType -> setState { copy(myCourseType = event.myCourseType) }
        }
    }

    fun fetchMyCourseRead() {
        viewModelScope.launch {
            setEvent(
                MyCourseContract.MyCourseEvent.FetchMyCourseRead(loadState = LoadState.Loading, courses = currentState.courses),
                org.sopt.dateroad.presentation.ui.home.HomeContract.HomeEvent.FetchRemainingPoints(loadState = org.sopt.dateroad.presentation.util.view.LoadState.Loading, remainingPoints = currentState.remainingPoints)
            )
            getMyCourseReadUseCase().onSuccess { courses ->
                setEvent(
                    MyCourseContract.MyCourseEvent.FetchMyCourseRead(loadState = LoadState.Success, courses = courses),
                    org.sopt.dateroad.presentation.ui.home.HomeContract.HomeEvent.FetchRemainingPoints(loadState = org.sopt.dateroad.presentation.util.view.LoadState.Loading, remainingPoints = currentState.remainingPoints)
                )
            }.onFailure {
                setEvent(
                    MyCourseContract.MyCourseEvent.FetchMyCourseRead(loadState = LoadState.Error, courses = currentState.courses),
                    org.sopt.dateroad.presentation.ui.home.HomeContract.HomeEvent.FetchRemainingPoints(loadState = org.sopt.dateroad.presentation.util.view.LoadState.Loading, remainingPoints = currentState.remainingPoints)
                )
            }
        }
    }

    fun fetchMyCourseEnroll() {
        viewModelScope.launch {
            setEvent(
                MyCourseContract.MyCourseEvent.FetchMyCourseEnroll(loadState = LoadState.Loading, courses = currentState.courses),
                org.sopt.dateroad.presentation.ui.home.HomeContract.HomeEvent.FetchRemainingPoints(loadState = org.sopt.dateroad.presentation.util.view.LoadState.Loading, remainingPoints = currentState.remainingPoints)
            )
            getMyCourseEnrollUseCase().onSuccess { courses ->
                setEvent(
                    MyCourseContract.MyCourseEvent.FetchMyCourseEnroll(loadState = LoadState.Success, courses = courses),
                    org.sopt.dateroad.presentation.ui.home.HomeContract.HomeEvent.FetchRemainingPoints(loadState = org.sopt.dateroad.presentation.util.view.LoadState.Loading, remainingPoints = currentState.remainingPoints)
                )
            }.onFailure {
                setEvent(
                    MyCourseContract.MyCourseEvent.FetchMyCourseEnroll(loadState = LoadState.Error, courses = currentState.courses),
                    org.sopt.dateroad.presentation.ui.home.HomeContract.HomeEvent.FetchRemainingPoints(loadState = org.sopt.dateroad.presentation.util.view.LoadState.Loading, remainingPoints = currentState.remainingPoints)
                )
            }
        }
    }
}
