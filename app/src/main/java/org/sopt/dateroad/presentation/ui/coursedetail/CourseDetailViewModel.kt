package org.sopt.dateroad.presentation.ui.coursedetail

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import org.sopt.dateroad.R
import org.sopt.dateroad.domain.model.CourseDetail
import org.sopt.dateroad.domain.model.Place
import org.sopt.dateroad.presentation.util.base.BaseViewModel
import org.sopt.dateroad.presentation.util.view.LoadState

@HiltViewModel
class CourseDetailViewModel @Inject constructor() : BaseViewModel<CourseDetailContract.CourseDetailUiState, CourseDetailContract.CourseDetailSideEffect, CourseDetailContract.CourseDetailEvent>() {
    override fun createInitialState(): CourseDetailContract.CourseDetailUiState = CourseDetailContract.CourseDetailUiState(
        loadState = LoadState.Success,
        isEditBottomSheetOpen = false,
        isRegionBottomSheetOpen = false,
        isPointReadDialogOpen = false,
        isPointLackDialogOpen = false,
        isFreeReadDialogOpen = false,
        isLikedButtonChecked = false,
        courseDetail = CourseDetail(
            courseId = 1,
            imageList = listOf(R.drawable.img_course_detail_dummy, R.drawable.img_course_detail_dummy),
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
            isAccess = false,
            free = 1,
            isMine = false,
            totalPoint = 95
        ),
        currentImagePage = 0
    )

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
        }
    }

    private fun handleDelete() {
        // Implement deletion logic here
    }

    private fun enrollSchedule() {
        // Implement enrollment logic here
    }
}
