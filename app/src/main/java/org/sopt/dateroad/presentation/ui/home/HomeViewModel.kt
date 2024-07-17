package org.sopt.dateroad.presentation.ui.home

import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.preferences.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.sopt.dateroad.domain.model.Course
import org.sopt.dateroad.domain.model.MainDate
import org.sopt.dateroad.domain.usecase.GetUserPointUseCase
import org.sopt.dateroad.domain.usecase.GetAdvertisementsUseCase
import org.sopt.dateroad.presentation.util.base.BaseViewModel
import org.sopt.dateroad.presentation.util.view.LoadState
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val GetUserPointUseCase: GetUserPointUseCase,
    private val getAdvertisementsUseCase: GetAdvertisementsUseCase
) : BaseViewModel<HomeContract.HomeUiState, HomeContract.HomeSideEffect, HomeContract.HomeEvent>() {
    private val PREFS_NAME = "org.sopt.dateroad"
    private val KEY_USER_ID = "user_id"
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    override fun createInitialState(): HomeContract.HomeUiState = HomeContract.HomeUiState()

    fun saveUserId(userId: Int) {
        sharedPreferences.edit().putInt(KEY_USER_ID, userId).apply()
    }

    fun getUserId(): Int {
        return sharedPreferences.getInt(KEY_USER_ID, -1)
    }

    override suspend fun handleEvent(event: HomeContract.HomeEvent) {
        when (event) {
            is HomeContract.HomeEvent.ChangeBannerPage -> setState { copy(currentBannerPage = event.page) }
            is HomeContract.HomeEvent.FetchAdvertisements -> setState { copy(loadState = event.loadState, advertisements = event.advertisements) }
            is HomeContract.HomeEvent.FetchLatestCourses -> setState { copy(loadState = event.loadState, latestCourses = event.latestCourses) }
            is HomeContract.HomeEvent.FetchRemainingPoints -> { setState { copy(loadState = event.loadState, remainingPoints = event.remainingPoints) } }
            is HomeContract.HomeEvent.FetchTopLikedCourses -> setState { copy(loadState = event.loadState, topLikedCourses = event.topLikedCourses) }
            is HomeContract.HomeEvent.FetchMainDate -> setState { copy(loadState = event.loadState, mainDate = event.mainDate) }
            is HomeContract.HomeEvent.FetchUserName -> setState { copy(loadState = event.loadState, userName = event.userName) }
        }
    }

    fun fetchProfile() {
        viewModelScope.launch {
            val userId = getUserId()
            setEvent(HomeContract.HomeEvent.FetchUserName(loadState = LoadState.Loading, userName = currentState.userName))
            setEvent(HomeContract.HomeEvent.FetchRemainingPoints(loadState = LoadState.Loading, remainingPoints = currentState.remainingPoints))
            GetUserPointUseCase(userId = userId)
                .onSuccess { userPoint ->
                    saveUserId(userId)
                    setEvent(HomeContract.HomeEvent.FetchUserName(loadState = LoadState.Success, userName = userPoint.name))
                    setEvent(HomeContract.HomeEvent.FetchRemainingPoints(loadState = LoadState.Success, remainingPoints = userPoint.point))
                }
                .onFailure {
                    setEvent(HomeContract.HomeEvent.FetchUserName(loadState = LoadState.Error, userName = currentState.userName))
                    setEvent(HomeContract.HomeEvent.FetchRemainingPoints(loadState = LoadState.Error, remainingPoints = currentState.remainingPoints))
                }
        }
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
