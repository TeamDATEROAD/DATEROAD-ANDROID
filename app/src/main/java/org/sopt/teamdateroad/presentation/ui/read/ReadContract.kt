package org.sopt.teamdateroad.presentation.ui.read

import org.sopt.teamdateroad.domain.model.Course
import org.sopt.teamdateroad.presentation.util.base.UiEvent
import org.sopt.teamdateroad.presentation.util.base.UiSideEffect
import org.sopt.teamdateroad.presentation.util.base.UiState
import org.sopt.teamdateroad.presentation.util.view.LoadState

class ReadContract {
    data class ReadUiState(
        val loadState: LoadState = LoadState.Idle,
        val name: String = "",
        val courses: List<Course> = listOf()
    ) : UiState

    sealed interface ReadSideEffect : UiSideEffect {
        data object NavigateToEnroll : ReadSideEffect
        data class NavigateToCourseDetail(val courseId: Int) : ReadSideEffect
    }

    sealed class ReadEvent : UiEvent {
        data class FetchMyCourseRead(val loadState: LoadState, val courses: List<Course>) : ReadEvent()
        data class FetchName(val name: String) : ReadEvent()
    }
}
