package org.sopt.dateroad.presentation.ui.coursedetail

import org.sopt.dateroad.domain.model.CourseDetail
import org.sopt.dateroad.presentation.util.base.UiEvent
import org.sopt.dateroad.presentation.util.base.UiSideEffect
import org.sopt.dateroad.presentation.util.base.UiState
import org.sopt.dateroad.presentation.util.view.LoadState

class CourseDetailContract {
    data class CourseDetailUiState(
        val loadState: LoadState = LoadState.Idle,
        val isEditBottomSheetOpen: Boolean = false,
        val isRegionBottomSheetOpen: Boolean = false,
        val isPointReadDialogOpen: Boolean = false,
        val isPointLackDialogOpen: Boolean = false,
        val isFreeReadDialogOpen: Boolean = false,
        val isLikedButtonChecked: Boolean = false,
        val courseDetail: CourseDetail,
        val currentImagePage: Int = 0
    ) : UiState

    sealed interface CourseDetailSideEffect : UiSideEffect

    sealed class CourseDetailEvent : UiEvent {
        data object OnLikeButtonClicked : CourseDetailEvent()
        data object OnDialogLookedByPoint : CourseDetailEvent()
        data object DismissDialogLookedByPoint : CourseDetailEvent()
        data object OnDialogLookedForFree : CourseDetailEvent()
        data object DismissDialogLookedForFree : CourseDetailEvent()
        data object OnDialogPointLack : CourseDetailEvent()
        data object DismissDialogPointLack : CourseDetailEvent()
        data object OnDeleteButtonClicked : CourseDetailEvent()
        data object OnEditBottomSheet : CourseDetailEvent()
        data object DismissEditBottomSheet : CourseDetailEvent()
        data object EnrollSchedule : CourseDetailEvent()
        data object OpenCourse : CourseDetailEvent()
    }
}
