package org.sopt.dateroad.presentation.ui.home

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import org.sopt.dateroad.domain.model.Advertisement
import org.sopt.dateroad.domain.model.MainDate
import org.sopt.dateroad.domain.type.SortByType
import org.sopt.dateroad.domain.usecase.GetSortedCoursesUseCase
import org.sopt.dateroad.presentation.util.base.BaseViewModel
import org.sopt.dateroad.presentation.util.view.LoadState

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getSortedCoursesUseCase: GetSortedCoursesUseCase
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
        setEvent(
            HomeContract.HomeEvent.FetchAdvertisements(
                loadState = LoadState.Success,
                advertisements = listOf(
                    Advertisement(
                        advertisementId = 1,
                        imageUrl = "https://i.namu.wiki/i/wXGU6DZbHowc6IB0GYPJpcmdDkLO3TW3MHzjg63jcTJvIzaBKhYqR0l9toBMHTv2OSU4eFKfPOlfrSQpymDJlA.webp"
                    ),
                    Advertisement(
                        advertisementId = 2,
                        imageUrl = "https://media.bunjang.co.kr/images/crop/1031740910_w%7Bres%7D.jpg"
                    ),
                    Advertisement(
                        advertisementId = 3,
                        imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQBYWlIYyriqT9L-q6PKYG6-oNocmGGbxbd-g&s"
                    ),
                    Advertisement(
                        advertisementId = 4,
                        imageUrl = "https://i.pinimg.com/736x/6b/18/ce/6b18cedab2c67f9020c071486306df20.jpg"
                    )
                )
            )
        )
    }

    fun fetchRemainingPoints() {
        setEvent(
            HomeContract.HomeEvent.FetchRemainingPoints(
                loadState = LoadState.Success,
                remainingPoints = 100
            )
        )
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
