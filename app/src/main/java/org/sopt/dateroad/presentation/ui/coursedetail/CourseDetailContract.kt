package org.sopt.dateroad.presentation.ui.coursedetail

import org.sopt.dateroad.domain.model.Place
import org.sopt.dateroad.presentation.util.base.UiEvent
import org.sopt.dateroad.presentation.util.base.UiSideEffect
import org.sopt.dateroad.presentation.util.base.UiState
import org.sopt.dateroad.presentation.util.view.LoadState

class CourseDetailContract {
    data class CourseDetailUiState(
        val loadState: LoadState = LoadState.Idle,
        val isAccess: Boolean = false,
        val isMine: Boolean = false,
        val free: Int = 0,
        val isEditBottomSheetOpen: Boolean = false,
        val isRegionBottomSheetOpen: Boolean = false,
        val isPointReadDialogOpen: Boolean = false,
        val isPointLackDialogOpen: Boolean = false,
        val isFreeReadDialogOpen: Boolean = false,
        val isLikedButtonChecked: Boolean = false,
        val like: Int = 0,
        val date: String = "",
        val title: String = "",
        val totalCost: String = "",
        val totalPoint: Int = 0,
        val city: String = "",
        val totalTime: String = "",
        val description: String = "",
        val tags: List<String> = listOf(),
        val imageList: List<Int> = listOf(),
        val currentImagePage: Int = 0,
        val places: List<Place> = listOf()
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
