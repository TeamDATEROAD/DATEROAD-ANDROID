package org.sopt.dateroad.presentation.ui.home

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import org.sopt.dateroad.domain.model.Course
import kotlinx.coroutines.launch
import org.sopt.dateroad.domain.model.Advertisement
import org.sopt.dateroad.domain.model.MainDate
import org.sopt.dateroad.domain.usecase.GetAdvertisementsUseCase
import org.sopt.dateroad.domain.usecase.GetNearestDateUseCase
import org.sopt.dateroad.domain.type.SortByType
import org.sopt.dateroad.domain.usecase.GetSortedCoursesUseCase
import org.sopt.dateroad.presentation.util.base.BaseViewModel
import org.sopt.dateroad.presentation.util.view.LoadState

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAdvertisementsUseCase: GetAdvertisementsUseCase,
    private val getNearestDateUseCase: GetNearestDateUseCase,
) : BaseViewModel<HomeContract.HomeUiState, HomeContract.HomeSideEffect, HomeContract.HomeEvent>() {
    class HomeViewModel @Inject constructor(
        private val getSortedCoursesUseCase: GetSortedCoursesUseCase,
    ) : BaseViewModel<HomeContract.HomeUiState, HomeContract.HomeSideEffect, HomeContract.HomeEvent>() {
        override fun createInitialState(): HomeContract.HomeUiState = HomeContract.HomeUiState()

        override suspend fun handleEvent(event: HomeContract.HomeEvent) {
            when (event) {
                is HomeContract.HomeEvent.ChangeBannerPage -> setState { copy(currentBannerPage = event.page) }
                is HomeContract.HomeEvent.FetchAdvertisements -> setState { copy(loadState = event.loadState, advertisements = event.advertisements) }
                is HomeContract.HomeEvent.FetchLatestCourses -> setState { copy(loadState = event.loadState, latestCourses = event.latestCourses) }
                is HomeContract.HomeEvent.FetchRemainingPoints -> setState { copy(loadState = event.loadState, remainingPoints = event.remainingPoints) }
                is HomeContract.HomeEvent.FetchTopLikedCourses -> setState { copy(loadState = event.loadState, topLikedCourses = event.topLikedCourses) }
                is HomeContract.HomeEvent.FetchMainDate -> setState { copy(loadState = event.loadState, mainDate = event.mainDate) }
                is HomeContract.HomeEvent.FetchUserName -> setState { copy(loadState = event.loadState, userName = event.userName) }
            }
        }

        fun changeBannerPage(page: Int) {
            setEvent(HomeContract.HomeEvent.ChangeBannerPage(page))
        }

        fun fetchSortedCourses(sortBy: SortByType) {
            viewModelScope.launch {
                setState { copy(loadState = LoadState.Loading) }
                getSortedCoursesUseCase(sortBy)
                    .onSuccess { responseCoursesDto ->
                        if (sortBy == SortByType.POPULAR) {
                            setEvent(HomeContract.HomeEvent.FetchTopLikedCourses(loadState = LoadState.Success, topLikedCourses = responseCoursesDto))
                        } else {
                            setEvent(HomeContract.HomeEvent.FetchLatestCourses(loadState = LoadState.Success, latestCourses = responseCoursesDto))
                        }
                    }
                    .onFailure {
                        setState { copy(loadState = LoadState.Error) }
                    }
            }
        }

        fun fetchProfile() {
            setEvent(
                HomeContract.HomeEvent.FetchUserName(
                    loadState = LoadState.Success,
                    userName = "이현진"
                )
            )
        }

        fun fetchAdvertisements() {
            viewModelScope.launch {
                setEvent(HomeContract.HomeEvent.FetchAdvertisements(loadState = LoadState.Loading, advertisements = currentState.advertisements))
                getAdvertisementsUseCase()
                    .onSuccess { advertisements ->
                        setEvent(HomeContract.HomeEvent.FetchAdvertisements(loadState = LoadState.Success, advertisements = advertisements))
                    }
                    .onFailure {
                        setEvent(HomeContract.HomeEvent.FetchAdvertisements(loadState = LoadState.Error, advertisements = currentState.advertisements))
                    }
            }
        }

        fun fetchRemainingPoints() {
            setEvent(
                HomeContract.HomeEvent.FetchRemainingPoints(
                    loadState = LoadState.Success,
                    remainingPoints = 100
                )
            )
        }

        fun fetchTopLikedCourses() {
            setEvent(
                HomeContract.HomeEvent.FetchTopLikedCourses(
                    loadState = LoadState.Success,
                    topLikedCourses = listOf(
                        Course(
                            courseId = 1,
                            thumbnail = "https://avatars.githubusercontent.com/u/103172971?v=4",
                            city = "건대/성수/왕십리",
                            title = "데이트할사람~",
                            cost = "100만원",
                            duration = "21시간",
                            like = "150"
                        ),
                        Course(
                            courseId = 2,
                            thumbnail = "https://avatars.githubusercontent.com/u/103172971?v=4",
                            city = "건대/성수/왕십리",
                            title = "데이트할사람데이트할사람데이트할사람데이트할사람데이트할사람데이트할사람데이트할사람데이트할사람",
                            cost = "150만원",
                            duration = "6시간",
                            like = "200"
                        )
                    )
                )
            )
        }

        fun fetchNearestDate() {
            viewModelScope.launch {
                setEvent(HomeContract.HomeEvent.FetchMainDate(loadState = LoadState.Loading, mainDate = MainDate()))
                getNearestDateUseCase()
                    .onSuccess { mainDate ->
                        setEvent(HomeContract.HomeEvent.FetchMainDate(loadState = LoadState.Success, mainDate = mainDate))
                    }
                    .onFailure {
                        setEvent(HomeContract.HomeEvent.FetchMainDate(loadState = LoadState.Error, mainDate = MainDate()))
                    }
            }
            fun fetchMainDate() {
                setEvent(
                    HomeContract.HomeEvent.FetchMainDate(
                        loadState = LoadState.Success,
                        mainDate = MainDate(
                            dateId = 1,
                            dDay = "3",
                            dateName = "성수 데이트",
                            month = 6,
                            day = 23,
                            startAt = "14:00 PM"
                        )
                    )
                )
            }

            fun fetchUserName() {
                setEvent(
                    HomeContract.HomeEvent.FetchUserName(
                        loadState = LoadState.Success,
                        userName = "이현진"
                    )
                )
            }
        }
    }
}
