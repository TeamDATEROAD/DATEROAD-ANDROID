package org.sopt.dateroad.presentation.ui.home

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import org.sopt.dateroad.domain.model.Advertisement
import org.sopt.dateroad.domain.model.Course
import org.sopt.dateroad.domain.model.MainDate
import org.sopt.dateroad.presentation.util.base.BaseViewModel
import org.sopt.dateroad.presentation.util.view.LoadState

@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel<HomeContract.HomeUiState, HomeContract.HomeSideEffect, HomeContract.HomeEvent>() {
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
                        imageUrl = "https://i.namu.wiki/i/wXGU6DZbHowc6IB0GYPJpcmdDkLO3TW3MHzjg63jcTJvIzaBKhYqR0l9toBMHTv2OSU4eFKfPOlfrSQpymDJlA.webp",
                        title = "비오는 장마철,\n실내데이트 어떠세요?",
                        tag = "에디터 픽"
                    ),
                    Advertisement(
                        advertisementId = 2,
                        imageUrl = "https://i.namu.wiki/i/wXGU6DZbHowc6IB0GYPJpcmdDkLO3TW3MHzjg63jcTJvIzaBKhYqR0l9toBMHTv2OSU4eFKfPOlfrSQpymDJlA.webp",
                        title = "얘두랑~ 나랑 데이트 할사람?얘두랑~ 나랑 데이트 할사람?얘두랑~ 나랑 데이트 할사람?얘두랑~ 나랑 데이트 할사람?",
                        tag = "에디터 픽"
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
                        id = 3,
                        url = "https://i.namu.wiki/i/gA_FoJIHIwSsBvHRiiR-k11sjIVKV_tibI5c7o4NAGTOS4KHLpJ9sMwm93qc5eH5cL7Vm0j6XQFT_ZdOZgZ_zJ86fAqfqk24VZivOZMTBUOiO_Tk3oa45R3AQzIYSXOrbvkAMcukVFInmo4d8MvCdA.webp",
                        city = "부천",
                        title = "부천에서는 뭐하면서 놀면 좋을까요? 흐음.... 부천에서 놀게 있나?",
                        cost = "10원",
                        duration = "1시간",
                        like = "100"
                    ),
                    Course(
                        id = 4,
                        url = "https://i.namu.wiki/i/gA_FoJIHIwSsBvHRiiR-k11sjIVKV_tibI5c7o4NAGTOS4KHLpJ9sMwm93qc5eH5cL7Vm0j6XQFT_ZdOZgZ_zJ86fAqfqk24VZivOZMTBUOiO_Tk3oa45R3AQzIYSXOrbvkAMcukVFInmo4d8MvCdA.webp",
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
                        id = 1,
                        url = "https://avatars.githubusercontent.com/u/103172971?v=4",
                        city = "건대/성수/왕십리",
                        title = "데이트할사람~",
                        cost = "100만원",
                        duration = "21시간",
                        like = "150"
                    ),
                    Course(
                        id = 2,
                        url = "https://avatars.githubusercontent.com/u/103172971?v=4",
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

    fun fetchUserName() {
        setEvent(
            HomeContract.HomeEvent.FetchUserName(
                loadState = LoadState.Success,
                userName = "이현진"
            )
        )
    }
}
