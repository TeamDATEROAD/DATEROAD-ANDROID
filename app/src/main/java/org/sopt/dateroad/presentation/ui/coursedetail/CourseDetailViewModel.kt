package org.sopt.dateroad.presentation.ui.coursedetail

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import org.sopt.dateroad.domain.usecase.DeleteCourseLikeUseCase
import org.sopt.dateroad.domain.usecase.DeleteCourseUseCase
import org.sopt.dateroad.domain.usecase.GetAdvertisementDetailUseCase
import org.sopt.dateroad.domain.usecase.GetCourseDetailUseCase
import org.sopt.dateroad.domain.usecase.PostCourseLikeUseCase
import org.sopt.dateroad.presentation.ui.home.HomeContract
import org.sopt.dateroad.presentation.util.base.BaseViewModel
import org.sopt.dateroad.presentation.util.view.LoadState

@HiltViewModel
class CourseDetailViewModel @Inject constructor(
    private val deleteCourseUseCase: DeleteCourseUseCase,
    private val deleteCourseLikeUseCase: DeleteCourseLikeUseCase,
    private val getAdvertisementDetailUseCase: GetAdvertisementDetailUseCase,
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
            is CourseDetailContract.CourseDetailEvent.OnEditBottomSheet -> setState { copy(isEditBottomSheetOpen = true) }
            is CourseDetailContract.CourseDetailEvent.DismissEditBottomSheet -> setState { copy(isEditBottomSheetOpen = false) }
            is CourseDetailContract.CourseDetailEvent.EnrollSchedule -> enrollSchedule()
            is CourseDetailContract.CourseDetailEvent.OpenCourse -> setState { copy(courseDetail = courseDetail.copy(isAccess = true)) }
            is CourseDetailContract.CourseDetailEvent.InitCourseDetail -> setState { copy(id = event.id, courseDetailType = event.courseDetailType) }
            is CourseDetailContract.CourseDetailEvent.FetchAdvertisementDetail -> setState { copy(loadState = event.loadState, advertisementDetail = event.advertisementDetail) }
            is CourseDetailContract.CourseDetailEvent.FetchCourseDetail -> setState { copy(loadState = event.loadState, courseDetail = event.courseDetail) }
            is CourseDetailContract.CourseDetailEvent.DeleteCourseLike -> setState { copy(loadState = event.loadState, courseDetail = event.courseDetail) }
            is CourseDetailContract.CourseDetailEvent.PostCourseLike -> setState { copy(loadState = event.loadState, courseDetail = event.courseDetail) }
            is CourseDetailContract.CourseDetailEvent.DeleteCourse -> setState { copy(loadState = event.loadState, deleteLoadState = event.deleteLoadState) }
        }
    }

    fun fetchAdvertisementDetail(advertisementId: Int) {
        viewModelScope.launch {
            setEvent(
                CourseDetailContract.CourseDetailEvent.FetchAdvertisementDetail(loadState = LoadState.Loading, advertisementDetail = currentState.advertisementDetail),
                HomeContract.HomeEvent.FetchRemainingPoints(loadState = LoadState.Loading, remainingPoints = currentState.remainingPoints)
            )
            getAdvertisementDetailUseCase(advertisementId = advertisementId).onSuccess { advertisementDetail ->
                setEvent(
                    CourseDetailContract.CourseDetailEvent.FetchAdvertisementDetail(loadState = LoadState.Success, advertisementDetail = advertisementDetail),
                    HomeContract.HomeEvent.FetchRemainingPoints(loadState = LoadState.Loading, remainingPoints = currentState.remainingPoints)
                )
            }.onFailure {
                setEvent(
                    CourseDetailContract.CourseDetailEvent.FetchAdvertisementDetail(loadState = LoadState.Error, advertisementDetail = currentState.advertisementDetail),
                    HomeContract.HomeEvent.FetchRemainingPoints(loadState = LoadState.Loading, remainingPoints = currentState.remainingPoints)
                )
            }
        }
    }

    fun deleteCourseLike(courseId: Int) {
        viewModelScope.launch {
            setEvent(
                CourseDetailContract.CourseDetailEvent.DeleteCourseLike(loadState = LoadState.Loading, courseDetail = currentState.courseDetail),
                HomeContract.HomeEvent.FetchRemainingPoints(loadState = LoadState.Loading, remainingPoints = currentState.remainingPoints)
            )
            deleteCourseLikeUseCase(courseId = courseId).onSuccess {
                setEvent(
                    CourseDetailContract.CourseDetailEvent.DeleteCourseLike(loadState = LoadState.Success, courseDetail = currentState.courseDetail.copy(isUserLiked = false, like = currentState.courseDetail.like - 1)),
                    HomeContract.HomeEvent.FetchRemainingPoints(loadState = LoadState.Loading, remainingPoints = currentState.remainingPoints)
                )
            }.onFailure {
                setEvent(
                    CourseDetailContract.CourseDetailEvent.DeleteCourseLike(loadState = LoadState.Error, courseDetail = currentState.courseDetail),
                    HomeContract.HomeEvent.FetchRemainingPoints(loadState = LoadState.Loading, remainingPoints = currentState.remainingPoints)
                )
            }
        }
    }

    fun fetchCourseDetail(courseId: Int) {
        viewModelScope.launch {
            setEvent(
                CourseDetailContract.CourseDetailEvent.FetchCourseDetail(loadState = LoadState.Loading, courseDetail = currentState.courseDetail),
                HomeContract.HomeEvent.FetchRemainingPoints(loadState = LoadState.Loading, remainingPoints = currentState.remainingPoints)
            )
            getCourseDetailUseCase(courseId = courseId).onSuccess { courseDetail ->
                setEvent(
                    CourseDetailContract.CourseDetailEvent.FetchCourseDetail(loadState = LoadState.Success, courseDetail = courseDetail),
                    HomeContract.HomeEvent.FetchRemainingPoints(loadState = LoadState.Loading, remainingPoints = currentState.remainingPoints)
                )
            }.onFailure {
                setEvent(
                    CourseDetailContract.CourseDetailEvent.FetchCourseDetail(loadState = LoadState.Error, courseDetail = currentState.courseDetail),
                    HomeContract.HomeEvent.FetchRemainingPoints(loadState = LoadState.Loading, remainingPoints = currentState.remainingPoints)
                )
            }
        }
    }

    fun postCourseLike(courseId: Int) {
        viewModelScope.launch {
            setEvent(
                CourseDetailContract.CourseDetailEvent.PostCourseLike(loadState = LoadState.Loading, courseDetail = currentState.courseDetail),
                HomeContract.HomeEvent.FetchRemainingPoints(loadState = LoadState.Loading, remainingPoints = currentState.remainingPoints)
            )
            postCourseLikeUseCase(courseId = courseId).onSuccess {
                setEvent(
                    CourseDetailContract.CourseDetailEvent.PostCourseLike(loadState = LoadState.Success, courseDetail = currentState.courseDetail.copy(isUserLiked = true, like = currentState.courseDetail.like + 1)),
                    HomeContract.HomeEvent.FetchRemainingPoints(loadState = LoadState.Loading, remainingPoints = currentState.remainingPoints)
                )
            }.onFailure {
                setEvent(
                    CourseDetailContract.CourseDetailEvent.PostCourseLike(loadState = LoadState.Error, courseDetail = currentState.courseDetail),
                    HomeContract.HomeEvent.FetchRemainingPoints(loadState = LoadState.Loading, remainingPoints = currentState.remainingPoints)
                )
            }
        }
    }

    fun deleteCourse(courseId: Int) {
        viewModelScope.launch {
            setEvent(
                CourseDetailContract.CourseDetailEvent.DeleteCourse(loadState = LoadState.Loading, deleteLoadState = LoadState.Loading),
                HomeContract.HomeEvent.FetchRemainingPoints(loadState = LoadState.Loading, remainingPoints = currentState.remainingPoints)
            )
            deleteCourseUseCase(courseId = courseId).onSuccess {
                setEvent(
                    CourseDetailContract.CourseDetailEvent.DeleteCourse(loadState = LoadState.Success, deleteLoadState = LoadState.Success),
                    HomeContract.HomeEvent.FetchRemainingPoints(loadState = LoadState.Loading, remainingPoints = currentState.remainingPoints)
                )
            }.onFailure {
                setEvent(
                    CourseDetailContract.CourseDetailEvent.DeleteCourse(loadState = LoadState.Error, deleteLoadState = LoadState.Error),
                    HomeContract.HomeEvent.FetchRemainingPoints(loadState = LoadState.Loading, remainingPoints = currentState.remainingPoints)
                )
            }
        }
    }

    private fun enrollSchedule() {
        // Implement enrollment logic here
    }
}
