package org.sopt.dateroad.presentation.ui.mycourse

import org.sopt.dateroad.domain.model.Course
import org.sopt.dateroad.presentation.type.MyCourseType
import org.sopt.dateroad.presentation.util.base.UiEvent
import org.sopt.dateroad.presentation.util.base.UiSideEffect
import org.sopt.dateroad.presentation.util.base.UiState
import org.sopt.dateroad.presentation.util.view.LoadState

class MyCourseContract {
    data class MyCourseUiState(
        val loadState: LoadState = LoadState.Idle,
        val myCourseType: MyCourseType = MyCourseType.READ,
        val courses: List<Course> = listOf()
    ) : UiState

    sealed interface MyCourseSideEffect : UiSideEffect {
        data class NavigateToEnroll(val courseId: Int) : MyCourseSideEffect
        data class NavigateToCourseDetail(val courseId: Int) : MyCourseSideEffect
        data object PopBackStack : MyCourseSideEffect
    }

    sealed class MyCourseEvent : UiEvent {
        data class FetchMyCourseRead(val loadState: LoadState, val courses: List<Course>) : MyCourseEvent()
        data class FetchMyCourseEnroll(val loadState: LoadState, val courses: List<Course>) : MyCourseEvent()
        data class SetMyCourseType(val myCourseType: MyCourseType) : MyCourseEvent()
    }
}
