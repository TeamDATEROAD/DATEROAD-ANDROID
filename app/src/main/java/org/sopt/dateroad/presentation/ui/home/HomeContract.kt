package org.sopt.dateroad.presentation.ui.home

import org.sopt.dateroad.domain.model.Advertisement
import org.sopt.dateroad.domain.model.Course
import org.sopt.dateroad.domain.model.MainDate
import org.sopt.dateroad.domain.model.UserPoint
import org.sopt.dateroad.presentation.type.CourseDetailType
import org.sopt.dateroad.presentation.type.EnrollType
import org.sopt.dateroad.presentation.util.base.UiEvent
import org.sopt.dateroad.presentation.util.base.UiSideEffect
import org.sopt.dateroad.presentation.util.base.UiState
import org.sopt.dateroad.presentation.util.view.LoadState

class HomeContract {
    data class HomeUiState(
        val loadState: LoadState = LoadState.Idle,
        val mainDate: MainDate = MainDate(),
        val topLikedCourses: List<Course> = listOf(),
        val latestCourses: List<Course> = listOf(),
        val advertisements: List<Advertisement> = listOf(),
        val userPoint: UserPoint = UserPoint(),
        val currentBannerPage: Int = 0,
        val profileImageUrl: String? = null
    ) : UiState

    sealed interface HomeSideEffect : UiSideEffect {
        data object NavigateToPointHistory : HomeSideEffect
        data object NavigateToLook : HomeSideEffect
        data object NavigateToTimeline : HomeSideEffect
        data class NavigateToEnroll(val enrollType: EnrollType, val id: Int?) : HomeSideEffect
        data class NavigateToCourseDetail(val courseDetailType: CourseDetailType, val id: Int) : HomeSideEffect
    }

    sealed class HomeEvent : UiEvent {
        data class FetchNearestDate(val loadState: LoadState, val mainDate: MainDate) : HomeEvent()
        data class FetchTopLikedCourses(val loadState: LoadState, val topLikedCourses: List<Course>) : HomeEvent()
        data class FetchLatestCourses(val loadState: LoadState, val latestCourses: List<Course>) : HomeEvent()
        data class FetchAdvertisements(val loadState: LoadState, val advertisements: List<Advertisement>) : HomeEvent()
        data class FetchUserPoint(val loadState: LoadState, val userPoint: UserPoint) : HomeEvent()
        data class ChangeBannerPage(val page: Int) : HomeEvent()
        data class FetchProfileImage(val loadState: LoadState, val profileImageUrl: String?) : HomeEvent()
    }
}
