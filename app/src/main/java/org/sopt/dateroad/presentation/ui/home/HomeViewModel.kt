package org.sopt.dateroad.presentation.ui.home

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import org.sopt.dateroad.domain.model.Advertisement
import org.sopt.dateroad.domain.model.Course
import org.sopt.dateroad.domain.model.MainDate
import org.sopt.dateroad.domain.usecase.GetUserProfileMainUseCase
import org.sopt.dateroad.presentation.util.base.BaseViewModel
import org.sopt.dateroad.presentation.util.view.LoadState

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserProfileMainUseCase: GetUserProfileMainUseCase
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

    fun fetchProfile() {
        viewModelScope.launch {
            setState { copy(loadState = LoadState.Loading) }
            getUserProfileMainUseCase(userId = 1)
                .onSuccess { userPoint ->
                    setState {
                        copy(
                            userName = userPoint.name,
                            remainingPoints = userPoint.point,
                            loadState = LoadState.Success
                        )
                    }
                }
                .onFailure {
                    setState { copy(loadState = LoadState.Error) }
                }
        }
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

    fun fetchLatestCourses() {
        setEvent(
            HomeContract.HomeEvent.FetchLatestCourses(
                loadState = LoadState.Success,
                latestCourses = listOf(
                    Course(
                        courseId = 3,
                        thumbnail = "https://i.namu.wiki/i/gA_FoJIHIwSsBvHRiiR-k11sjIVKV_tibI5c7o4NAGTOS4KHLpJ9sMwm93qc5eH5cL7Vm0j6XQFT_ZdOZgZ_zJ86fAqfqk24VZivOZMTBUOiO_Tk3oa45R3AQzIYSXOrbvkAMcukVFInmo4d8MvCdA.webp",
                        city = "부천",
                        title = "부천에서는 뭐하면서 놀면 좋을까요? 흐음.... 부천에서 놀게 있나?",
                        cost = "10원",
                        duration = "1시간",
                        like = "100"
                    ),
                    Course(
                        courseId = 4,
                        thumbnail = "https://i.namu.wiki/i/gA_FoJIHIwSsBvHRiiR-k11sjIVKV_tibI5c7o4NAGTOS4KHLpJ9sMwm93qc5eH5cL7Vm0j6XQFT_ZdOZgZ_zJ86fAqfqk24VZivOZMTBUOiO_Tk3oa45R3AQzIYSXOrbvkAMcukVFInmo4d8MvCdA.webp",
                        city = "제주",
                        title = "제주도에서 한라봉 따먹을 사람?",
                        cost = "120만원",
                        duration = "48시간",
                        like = "999+"
                    )
                )
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
}
