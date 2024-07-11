package org.sopt.dateroad.presentation.ui.look

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import org.sopt.dateroad.domain.model.Course
import org.sopt.dateroad.presentation.type.RegionType
import org.sopt.dateroad.presentation.util.base.BaseViewModel
import org.sopt.dateroad.presentation.util.view.LoadState

@HiltViewModel
class LookViewModel @Inject constructor() : BaseViewModel<LookContract.LookUiState, LookContract.LookSideEffect, LookContract.LookEvent>() {
    override fun createInitialState(): LookContract.LookUiState = LookContract.LookUiState()

    override suspend fun handleEvent(event: LookContract.LookEvent) {
        when (event) {
            is LookContract.LookEvent.FetchCourses -> fetchCourses()
            is LookContract.LookEvent.OnAreaButtonClicked -> {
                setState { copy(isRegionBottomSheetOpen = true, regionBottomSheetSelectedRegion = RegionType.SEOUL, regionBottomSheetSelectedArea = null) }
            }

            is LookContract.LookEvent.OnResetButtonClicked -> {
                setState { copy(region = null, area = null, money = null) }
            }

            is LookContract.LookEvent.OnRegionBottomSheetDismissRequest -> {
                setState { copy(isRegionBottomSheetOpen = false) }
            }

            is LookContract.LookEvent.OnMoneyChipClicked -> {
                setState { copy(money = event.money.takeUnless { it == currentState.money }) }
            }

            is LookContract.LookEvent.OnRegionBottomSheetButtonClicked -> {
                setState { copy(isRegionBottomSheetOpen = false, region = event.region, area = event.area) }
            }

            is LookContract.LookEvent.OnRegionBottomSheetRegionClicked -> {
                setState { copy(regionBottomSheetSelectedRegion = event.region) }
            }

            is LookContract.LookEvent.OnRegionBottomSheetAreaClicked -> {
                setState { copy(regionBottomSheetSelectedArea = event.area) }
            }
        }
    }

    private fun fetchCourses() {
        setState {
            copy(
                loadState = LoadState.Success,
                courses = listOf(
                    Course(
                        id = 1,
                        url = "https://avatars.githubusercontent.com/u/103172971?v=4",
                        city = "건대/성수/왕십리",
                        title = "성수동 당일치기 데이트 코스 둘러보러 가실까요?",
                        cost = "5만원 이하",
                        duration = "10시간",
                        like = "999"
                    ),
                    Course(
                        id = 1,
                        url = "https://avatars.githubusercontent.com/u/103172971?v=4",
                        city = "홍대",
                        title = "데로 파이띵 !",
                        cost = "10만원 이하",
                        duration = "1시간",
                        like = "3"
                    ),
                    Course(
                        id = 1,
                        url = "https://avatars.githubusercontent.com/u/103172971?v=4",
                        city = "건대/성수/왕십리",
                        title = "성수동 당일치기 데이트 코스 둘러보러 가실까요?",
                        cost = "5만원 이하",
                        duration = "10시간",
                        like = "999"
                    ),
                    Course(
                        id = 1,
                        url = "https://avatars.githubusercontent.com/u/103172971?v=4",
                        city = "홍대",
                        title = "데로 파이띵 !",
                        cost = "10만원 이하",
                        duration = "1시간",
                        like = "3"
                    ),
                    Course(
                        id = 1,
                        url = "https://avatars.githubusercontent.com/u/103172971?v=4",
                        city = "건대/성수/왕십리",
                        title = "성수동 당일치기 데이트 코스 둘러보러 가실까요?",
                        cost = "5만원 이하",
                        duration = "10시간",
                        like = "999"
                    ),
                    Course(
                        id = 1,
                        url = "https://avatars.githubusercontent.com/u/103172971?v=4",
                        city = "홍대",
                        title = "데로 파이띵 !",
                        cost = "10만원 이하",
                        duration = "1시간",
                        like = "3"
                    ),
                    Course(
                        id = 1,
                        url = "https://avatars.githubusercontent.com/u/103172971?v=4",
                        city = "건대/성수/왕십리",
                        title = "성수동 당일치기 데이트 코스 둘러보러 가실까요?",
                        cost = "5만원 이하",
                        duration = "10시간",
                        like = "999"
                    ),
                    Course(
                        id = 1,
                        url = "https://avatars.githubusercontent.com/u/103172971?v=4",
                        city = "홍대",
                        title = "데로 파이띵 !",
                        cost = "10만원 이하",
                        duration = "1시간",
                        like = "3"
                    )
                )
            )
        }
    }
}
