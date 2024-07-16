package org.sopt.dateroad.presentation.ui.coursedetail

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import org.sopt.dateroad.domain.model.AdvertisementDetail
import org.sopt.dateroad.domain.usecase.DeleteCourseLikeUseCase
import org.sopt.dateroad.domain.usecase.GetCourseDetailUseCase
import org.sopt.dateroad.domain.usecase.PostCourseLikeUseCase
import org.sopt.dateroad.presentation.util.base.BaseViewModel
import org.sopt.dateroad.presentation.util.view.LoadState

@HiltViewModel
class CourseDetailViewModel @Inject constructor(
    private val deleteCourseLikeUseCase: DeleteCourseLikeUseCase,
    private val getCourseDetailUseCase: GetCourseDetailUseCase,
    private val postCourseLikeUseCase: PostCourseLikeUseCase
) : BaseViewModel<CourseDetailContract.CourseDetailUiState, CourseDetailContract.CourseDetailSideEffect, CourseDetailContract.CourseDetailEvent>() {
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
            is CourseDetailContract.CourseDetailEvent.DeleteCourseLike -> setState { copy(loadState = event.loadState, courseDetail = courseDetail.copy(isUserLiked = false, like = courseDetail.like - 1)) }
            is CourseDetailContract.CourseDetailEvent.PostCourseLike -> setState { copy(loadState = event.loadState, courseDetail = courseDetail.copy(isUserLiked = true, like = courseDetail.like + 1)) }
        }
    }

    fun fetchAdvertisementDetail() {
        setEvent(
            CourseDetailContract.CourseDetailEvent.FetchAdvertisementDetail(
                loadState = LoadState.Success,
                advertisementDetail = AdvertisementDetail(
                    images = listOf("https://avatars.githubusercontent.com/u/103172971?v=4", "https://avatars.githubusercontent.com/u/103172971?v=4"),
                    tag = "광고",
                    title = "광고입니당",
                    createAt = "2024년 6월 27일",
                    description = "5년차 장기연애 커플이 보장하는 성수동 당일치기 데이트 코스를 소개해 드릴게요. 저희 커플은 12시에 만나서 브런치 집을 갔어요. 여기에서는 프렌치 토스트를 꼭 시키세요. 강추합니다. \n \n1시간 정도 밥을 먹고 바로 성수미술관에 가서 그림을 그렸는데요. 물감이 튈 수 있어서 흰색 옷은 피하는 것을 추천드려요. 2시간 정도 소요가 되는데 저희는 예약을 해둬서 웨이팅 없이 바로 입장했고, 네이버 예약을 이용했습니다. 평일 기준 20,000원이니 꼭 예약해서 가세요!"
                )
            )
        )
    }

    fun deleteCourseLike(courseId: Int) {
        viewModelScope.launch {
            setEvent(CourseDetailContract.CourseDetailEvent.DeleteCourseLike(loadState = LoadState.Loading))
            deleteCourseLikeUseCase(courseId = courseId).onSuccess {
                setEvent(CourseDetailContract.CourseDetailEvent.DeleteCourseLike(loadState = LoadState.Success))
            }.onFailure {
                setEvent(CourseDetailContract.CourseDetailEvent.DeleteCourseLike(loadState = LoadState.Error))
            }
        }
    }

    fun fetchCourseDetail(courseId: Int) {
        viewModelScope.launch {
            setEvent(CourseDetailContract.CourseDetailEvent.FetchCourseDetail(loadState = LoadState.Loading, courseDetail = currentState.courseDetail))
            getCourseDetailUseCase(courseId = courseId).onSuccess { courseDetail ->
                setEvent(CourseDetailContract.CourseDetailEvent.FetchCourseDetail(loadState = LoadState.Success, courseDetail = courseDetail))
            }.onFailure {
                setEvent(CourseDetailContract.CourseDetailEvent.FetchCourseDetail(loadState = LoadState.Error, courseDetail = currentState.courseDetail))
            }
        }
    }

    fun postCourseLike(courseId: Int) {
        viewModelScope.launch {
            setEvent(CourseDetailContract.CourseDetailEvent.PostCourseLike(loadState = LoadState.Loading))
            postCourseLikeUseCase(courseId = courseId).onSuccess {
                setEvent(CourseDetailContract.CourseDetailEvent.PostCourseLike(loadState = LoadState.Success))
            }.onFailure {
                setEvent(CourseDetailContract.CourseDetailEvent.PostCourseLike(loadState = LoadState.Error))
            }
        }
    }

    private fun handleDelete() {
        // Implement deletion logic here
    }

    private fun enrollSchedule() {
        // Implement enrollment logic here
    }
}
