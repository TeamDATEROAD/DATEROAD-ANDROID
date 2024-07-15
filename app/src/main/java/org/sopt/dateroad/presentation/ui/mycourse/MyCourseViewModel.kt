package org.sopt.dateroad.presentation.ui.mycourse

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.dateroad.domain.usecase.PostMyCourseEnrollUseCase
import org.sopt.dateroad.domain.usecase.PostMyCourseReadUseCase
import org.sopt.dateroad.presentation.util.base.BaseViewModel
import org.sopt.dateroad.presentation.util.view.LoadState
import javax.inject.Inject

@HiltViewModel
class MyCourseViewModel @Inject constructor(
    private val postMyCourseEnrollUseCase: PostMyCourseEnrollUseCase,
    private val postMyCourseReadUseCase: PostMyCourseReadUseCase
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
            setEvent(MyCourseContract.MyCourseEvent.FetchMyCourseRead(loadState = LoadState.Loading, courses = currentState.courses))
            postMyCourseReadUseCase().onSuccess { courses ->
                setEvent(MyCourseContract.MyCourseEvent.FetchMyCourseRead(loadState = LoadState.Success, courses = courses))
            }.onFailure {
                setEvent(MyCourseContract.MyCourseEvent.FetchMyCourseRead(loadState = LoadState.Error, courses = currentState.courses))
            }
        }
    }

    fun fetchMyCourseEnroll() {
        viewModelScope.launch {
            setEvent(MyCourseContract.MyCourseEvent.FetchMyCourseEnroll(loadState = LoadState.Loading, courses = currentState.courses))
            postMyCourseEnrollUseCase().onSuccess { courses ->
                setEvent(MyCourseContract.MyCourseEvent.FetchMyCourseEnroll(loadState = LoadState.Success, courses = courses))
            }.onFailure {
                setEvent(MyCourseContract.MyCourseEvent.FetchMyCourseEnroll(loadState = LoadState.Error, courses = currentState.courses))
            }
        }
    }
}
