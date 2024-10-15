package org.sopt.teamdateroad.presentation.ui.read

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import org.sopt.teamdateroad.domain.usecase.GetMyCourseReadUseCase
import org.sopt.teamdateroad.domain.usecase.GetNicknameUseCase
import org.sopt.teamdateroad.presentation.util.base.BaseViewModel
import org.sopt.teamdateroad.presentation.util.view.LoadState

@HiltViewModel
class ReadViewModel @Inject constructor(
    private val getNicknameUseCase: GetNicknameUseCase,
    private val getMyCourseReadUseCase: GetMyCourseReadUseCase
) : BaseViewModel<ReadContract.ReadUiState, ReadContract.ReadSideEffect, ReadContract.ReadEvent>() {
    override fun createInitialState(): ReadContract.ReadUiState =
        ReadContract.ReadUiState()

    override suspend fun handleEvent(event: ReadContract.ReadEvent) {
        when (event) {
            is ReadContract.ReadEvent.FetchMyCourseRead -> setState { copy(loadState = event.loadState, courses = event.courses) }
            is ReadContract.ReadEvent.FetchName -> setState { copy(name = event.name) }
        }
    }

    fun fetchMyCourseRead() {
        viewModelScope.launch {
            setEvent(
                ReadContract.ReadEvent.FetchMyCourseRead(loadState = LoadState.Loading, courses = currentState.courses)
            )
            getMyCourseReadUseCase().onSuccess { courses ->
                setEvent(
                    ReadContract.ReadEvent.FetchMyCourseRead(loadState = LoadState.Success, courses = courses)
                )
            }.onFailure {
                setEvent(
                    ReadContract.ReadEvent.FetchMyCourseRead(loadState = LoadState.Error, courses = currentState.courses)
                )
            }
        }
    }

    fun fetchName() {
        setEvent(ReadContract.ReadEvent.FetchName(name = getNicknameUseCase()))
    }
}
