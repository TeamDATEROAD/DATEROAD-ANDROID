package org.sopt.dateroad.presentation.ui.coursedetail

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import org.sopt.dateroad.domain.model.AdvertisementDetail
import org.sopt.dateroad.domain.model.CourseDetail
import org.sopt.dateroad.domain.model.Place
import org.sopt.dateroad.presentation.util.base.BaseViewModel
import org.sopt.dateroad.presentation.util.view.LoadState

@HiltViewModel
class CourseDetailViewModel @Inject constructor() : BaseViewModel<CourseDetailContract.CourseDetailUiState, CourseDetailContract.CourseDetailSideEffect, CourseDetailContract.CourseDetailEvent>() {
    override fun createInitialState(): CourseDetailContract.CourseDetailUiState = CourseDetailContract.CourseDetailUiState()

    override suspend fun handleEvent(event: CourseDetailContract.CourseDetailEvent) {
        when (event) {
            is CourseDetailContract.CourseDetailEvent.OnDialogPointLack -> setState { copy(isPointLackDialogOpen = true) }
            is CourseDetailContract.CourseDetailEvent.DismissDialogPointLack -> setState { copy(isPointLackDialogOpen = false) }
            is CourseDetailContract.CourseDetailEvent.OnDialogLookedForFree -> setState { copy(isFreeReadDialogOpen = true) }
            is CourseDetailContract.CourseDetailEvent.DismissDialogLookedForFree -> setState { copy(isFreeReadDialogOpen = false) }
            is CourseDetailContract.CourseDetailEvent.OnDialogLookedByPoint -> setState { copy(isPointReadDialogOpen = true) }
            is CourseDetailContract.CourseDetailEvent.DismissDialogLookedByPoint -> setState { copy(isPointReadDialogOpen = false) }
            is CourseDetailContract.CourseDetailEvent.OnLikeButtonClicked -> setState { copy(isLikedButtonChecked = !isLikedButtonChecked) }
            is CourseDetailContract.CourseDetailEvent.OnDeleteButtonClicked -> handleDelete()
            is CourseDetailContract.CourseDetailEvent.OnEditBottomSheet -> setState { copy(isEditBottomSheetOpen = true) }
            is CourseDetailContract.CourseDetailEvent.DismissEditBottomSheet -> setState { copy(isEditBottomSheetOpen = false) }
            is CourseDetailContract.CourseDetailEvent.EnrollSchedule -> enrollSchedule()
            is CourseDetailContract.CourseDetailEvent.OpenCourse -> setState { copy(courseDetail = courseDetail.copy(isAccess = true)) }
            is CourseDetailContract.CourseDetailEvent.InitCourseDetail -> setState { copy(id = event.id, courseDetailType = event.courseDetailType) }
            is CourseDetailContract.CourseDetailEvent.FetchAdvertisementDetail -> setState { copy(loadState = event.loadState, advertisementDetail = event.advertisementDetail) }
            is CourseDetailContract.CourseDetailEvent.FetchCourseDetail -> setState { copy(loadState = event.loadState, courseDetail = event.courseDetail) }
        }
    }

    fun fetchAdvertisementDetail() {
        setEvent(
            CourseDetailContract.CourseDetailEvent.FetchAdvertisementDetail(
                loadState = LoadState.Success,
                advertisementDetail = AdvertisementDetail(
                    images = listOf("https://avatars.githubusercontent.com/u/103172971?v=4", "https://avatars.githubusercontent.com/u/103172971?v=4"),
                    adTagType = "광고",
                    title = "광고입니당",
                    createAt = "2024년 6월 27일",
                    description = "5년차 장기연애 커플이 보장하는 성수동 당일치기 데이트 코스를 소개해 드릴게요. 저희 커플은 12시에 만나서 브런치 집을 갔어요. 여기에서는 프렌치 토스트를 꼭 시키세요. 강추합니다. \n \n1시간 정도 밥을 먹고 바로 성수미술관에 가서 그림을 그렸는데요. 물감이 튈 수 있어서 흰색 옷은 피하는 것을 추천드려요. 2시간 정도 소요가 되는데 저희는 예약을 해둬서 웨이팅 없이 바로 입장했고, 네이버 예약을 이용했습니다. 평일 기준 20,000원이니 꼭 예약해서 가세요!"
                )
            )
        )
    }

    fun fetchCourseDetail() {
        setEvent(
            CourseDetailContract.CourseDetailEvent.FetchCourseDetail(
                loadState = LoadState.Success,
                courseDetail = CourseDetail(
                    courseId = 1,
                    imageList = listOf("https://avatars.githubusercontent.com/u/103172971?v=4", "https://avatars.githubusercontent.com/u/103172971?v=4"),
                    like = 123,
                    totalTime = "5 시간",
                    date = "24년 6월 27일 방문",
                    city = "건대/성수/왕십리",
                    title = "5년차 장기연애 커플이 보장하는 성수동 당일치기 데이트 코스",
                    description = "나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래?\n\n나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래?나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래?나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래?나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래?",
                    places = listOf(
                        Place(sequence = 1, title = "성수미술관 성수점", duration = "2 시간"),
                        Place(sequence = 2, title = "생마차", duration = "3 시간")
                    ),
                    totalCost = "50,000 원",
                    tags = listOf("드라이브", "쇼핑"),
                    isAccess = true,
                    free = 1,
                    isMine = false,
                    totalPoint = 95
                )
            )
        )
    }

    private fun handleDelete() {
        // Implement deletion logic here
    }

    private fun enrollSchedule() {
        // Implement enrollment logic here
    }
}
