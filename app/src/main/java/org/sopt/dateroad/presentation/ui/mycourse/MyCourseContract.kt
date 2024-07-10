package org.sopt.dateroad.presentation.ui.mycourse

import org.sopt.dateroad.domain.model.Course
import org.sopt.dateroad.presentation.util.base.UiEvent
import org.sopt.dateroad.presentation.util.base.UiSideEffect
import org.sopt.dateroad.presentation.util.base.UiState
import org.sopt.dateroad.presentation.util.view.LoadState

class MyCourseContract {
    data class MyCourseUiState(
        val loadState: LoadState = LoadState.Idle,
        val courses: List<Course> = emptyList()
    ) : UiState

    sealed interface MyCourseSideEffect : UiSideEffect {
        data object ShowError : MyCourseSideEffect
    }

    sealed class MyCourseEvent : UiEvent {
        data object FetchMyCourses : MyCourseEvent()
    }
}
