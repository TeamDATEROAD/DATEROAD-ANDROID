package org.sopt.dateroad.presentation.ui.home

import org.sopt.dateroad.domain.model.Advertisement
import org.sopt.dateroad.domain.model.Course
import org.sopt.dateroad.domain.model.MainDate
import org.sopt.dateroad.presentation.util.base.UiEvent
import org.sopt.dateroad.presentation.util.base.UiSideEffect
import org.sopt.dateroad.presentation.util.base.UiState
import org.sopt.dateroad.presentation.util.view.LoadState

class HomeContract {
    data class HomeUiState(
        val loadState: LoadState = LoadState.Idle,
        val mainDate: MainDate? = null,
        val topLikedCourses: List<Course> = listOf(),
        val latestCourses: List<Course> = listOf(),
        val advertisements: List<Advertisement> = listOf(),
        val userName: String = "",
        val remainingPoints: String = "0",
        val currentBannerPage: Int = 0
    ) : UiState

    sealed interface HomeSideEffect : UiSideEffect {
        data class NavigateToCourseListPage(val sortedBy: String) : HomeSideEffect
        data class NavigateToCourseDetailPage(val courseId: Int) : HomeSideEffect
        data class NavigateToEditorPickPage(val courseId: Int) : HomeSideEffect
        data class NavigateToDateDetailPage(val dateId: Int) : HomeSideEffect
        data object NavigateToTimeline : HomeSideEffect
        data object NavigateToPointHistoryPage : HomeSideEffect
    }

    sealed class HomeEvent : UiEvent {
        data class FetchMainDate(val loadState: LoadState, val mainDate: MainDate?) : HomeEvent()
        data class FetchTopLikedCourses(val loadState: LoadState, val topLikedCourses: List<Course>) : HomeEvent()
        data class FetchLatestCourses(val loadState: LoadState, val latestCourses: List<Course>) : HomeEvent()
        data class FetchAdvertisements(val loadState: LoadState, val advertisements: List<Advertisement>) : HomeEvent()
        data class FetchUserName(val loadState: LoadState, val userName: String) : HomeEvent()
        data class FetchRemainingPoints(val loadState: LoadState, val remainingPoints: String) : HomeEvent()
        data class ChangeBannerPage(val page: Int) : HomeEvent()
    }
}
