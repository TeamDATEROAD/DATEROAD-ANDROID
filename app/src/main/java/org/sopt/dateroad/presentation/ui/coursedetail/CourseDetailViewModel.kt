package org.sopt.dateroad.presentation.ui.coursedetail

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import org.sopt.dateroad.domain.model.UsePoint
import org.sopt.dateroad.domain.usecase.DeleteCourseLikeUseCase
import org.sopt.dateroad.domain.usecase.DeleteCourseUseCase
import org.sopt.dateroad.domain.usecase.GetCourseDetailUseCase
import org.sopt.dateroad.domain.usecase.PostCourseLikeUseCase
import org.sopt.dateroad.domain.usecase.PostUsePointUseCase
import org.sopt.dateroad.presentation.util.Point
import org.sopt.dateroad.presentation.util.base.BaseViewModel
import org.sopt.dateroad.presentation.util.view.LoadState

@HiltViewModel
class CourseDetailViewModel @Inject constructor(
    private val deleteCourseUseCase: DeleteCourseUseCase,
    private val deleteCourseLikeUseCase: DeleteCourseLikeUseCase,
    private val getCourseDetailUseCase: GetCourseDetailUseCase,
    private val postCourseLikeUseCase: PostCourseLikeUseCase,
    private val postUsePointUseCase: PostUsePointUseCase
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
            is CourseDetailContract.CourseDetailEvent.OpenCourse -> setState { copy(courseDetail = courseDetail.copy(isAccess = true)) }
            is CourseDetailContract.CourseDetailEvent.FetchCourseDetail -> setState { copy(loadState = event.loadState, courseDetail = event.courseDetail) }
            is CourseDetailContract.CourseDetailEvent.DeleteCourseLike -> setState { copy(courseDetail = event.courseDetail) }
            is CourseDetailContract.CourseDetailEvent.PostCourseLike -> setState { copy(courseDetail = event.courseDetail) }
            is CourseDetailContract.CourseDetailEvent.DeleteCourse -> setState { copy(loadState = event.loadState, deleteLoadState = event.deleteLoadState) }
            is CourseDetailContract.CourseDetailEvent.PostUsePoint -> setState { copy(usePointLoadState = usePointLoadState) }
            is CourseDetailContract.CourseDetailEvent.OnReportWebViewClicked -> setState { copy(isWebViewOpened = true) }
            is CourseDetailContract.CourseDetailEvent.DismissReportWebView -> setState { copy(isWebViewOpened = false) }
        }
    }

    fun deleteCourseLike(courseId: Int) {
        viewModelScope.launch {
            setEvent(CourseDetailContract.CourseDetailEvent.DeleteCourseLike(courseDetail = currentState.courseDetail))
            deleteCourseLikeUseCase(courseId = courseId).onSuccess {
                setEvent(CourseDetailContract.CourseDetailEvent.DeleteCourseLike(courseDetail = currentState.courseDetail.copy(isUserLiked = false, like = currentState.courseDetail.like - 1)))
            }.onFailure {
                setEvent(CourseDetailContract.CourseDetailEvent.DeleteCourseLike(courseDetail = currentState.courseDetail))
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
            setEvent(
                CourseDetailContract.CourseDetailEvent.PostCourseLike(courseDetail = currentState.courseDetail)
            )
            postCourseLikeUseCase(courseId = courseId).onSuccess {
                setEvent(CourseDetailContract.CourseDetailEvent.PostCourseLike(courseDetail = currentState.courseDetail.copy(isUserLiked = true, like = currentState.courseDetail.like + 1)))
            }.onFailure {
                setEvent(CourseDetailContract.CourseDetailEvent.PostCourseLike(courseDetail = currentState.courseDetail))
            }
        }
    }

    fun postUsePoint(courseId: Int) {
        viewModelScope.launch {
            setEvent(CourseDetailContract.CourseDetailEvent.PostUsePoint(usePointLoadState = LoadState.Loading))
            postUsePointUseCase(courseId = courseId, usePoint = UsePoint(Point.POINT, Point.POINT_USED, "${currentState.courseDetail.title} 코스 열람")).onSuccess {
                setEvent(CourseDetailContract.CourseDetailEvent.PostUsePoint(usePointLoadState = LoadState.Success))
            }.onFailure {
                setEvent(CourseDetailContract.CourseDetailEvent.PostUsePoint(usePointLoadState = LoadState.Error))
            }
        }
    }

    fun deleteCourse(courseId: Int) {
        viewModelScope.launch {
            setEvent(
                CourseDetailContract.CourseDetailEvent.DeleteCourse(loadState = LoadState.Loading, deleteLoadState = LoadState.Loading)
            )
            deleteCourseUseCase(courseId = courseId).onSuccess {
                setEvent(
                    CourseDetailContract.CourseDetailEvent.DeleteCourse(loadState = LoadState.Success, deleteLoadState = LoadState.Success)
                )
            }.onFailure {
                setEvent(
                    CourseDetailContract.CourseDetailEvent.DeleteCourse(loadState = LoadState.Error, deleteLoadState = LoadState.Error)
                )
            }
        }
    }
}
