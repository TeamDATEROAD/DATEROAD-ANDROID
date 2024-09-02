package org.sopt.dateroad.presentation.ui.coursedetail

import org.sopt.dateroad.domain.model.CourseDetail
import org.sopt.dateroad.presentation.type.EnrollType
import org.sopt.dateroad.presentation.util.base.UiEvent
import org.sopt.dateroad.presentation.util.base.UiSideEffect
import org.sopt.dateroad.presentation.util.base.UiState
import org.sopt.dateroad.presentation.util.view.LoadState

class CourseDetailContract {
    data class CourseDetailUiState(
        val loadState: LoadState = LoadState.Idle,
        val isDeleteCourseBottomSheetOpen: Boolean = false,
        val isRegionBottomSheetOpen: Boolean = false,
        val isReportCourseBottomSheetOpen: Boolean = false,
        val isPointReadDialogOpen: Boolean = false,
        val isPointLackDialogOpen: Boolean = false,
        val isFreeReadDialogOpen: Boolean = false,
        val isDeleteCourseDialogOpen: Boolean = false,
        val isReportCourseDialogOpen: Boolean = false,
        val isLikedButtonChecked: Boolean = false,
        val courseDetail: CourseDetail = CourseDetail(),
        val currentImagePage: Int = 0,
        val deleteLoadState: LoadState = LoadState.Idle,
        var isWebViewOpened: Boolean = false
    ) : UiState

    sealed interface CourseDetailSideEffect : UiSideEffect {
        data class NavigateToEnroll(val enrollType: EnrollType, val id: Int?) : CourseDetailSideEffect
        data object PopBackStack : CourseDetailSideEffect
    }

    sealed class CourseDetailEvent : UiEvent {
        data object OnLikeButtonClicked : CourseDetailEvent()
        data object OnDialogLookedByPoint : CourseDetailEvent()
        data object DismissDialogLookedByPoint : CourseDetailEvent()
        data object OnDialogLookedForFree : CourseDetailEvent()
        data object DismissDialogLookedForFree : CourseDetailEvent()
        data object OnDialogPointLack : CourseDetailEvent()
        data object DismissDialogPointLack : CourseDetailEvent()
        data object OnDialogDeleteCourse : CourseDetailEvent()
        data object DismissDialogDeleteCourse : CourseDetailEvent()
        data object OnDialogReportCourse : CourseDetailEvent()
        data object DismissDialogReportCourse : CourseDetailEvent()
        data object OnDeleteCourseBottomSheet : CourseDetailEvent()
        data object DismissDeleteCourseBottomSheet : CourseDetailEvent()
        data object OnReportCourseBottomSheet : CourseDetailEvent()
        data object DismissReportCourseBottomSheet : CourseDetailEvent()
        data class FetchCourseDetail(val loadState: LoadState, val courseDetail: CourseDetail) : CourseDetailEvent()
        data class PostUsePoint(val isAccess: Boolean) : CourseDetailEvent()
        data class DeleteCourseLike(val courseDetail: CourseDetail) : CourseDetailEvent()
        data class PostCourseLike(val courseDetail: CourseDetail) : CourseDetailEvent()
        data class DeleteCourse(val loadState: LoadState, val deleteLoadState: LoadState) : CourseDetailEvent()
        data object OnReportWebViewClicked : CourseDetailEvent()
        data object DismissReportWebView : CourseDetailEvent()
    }
}
