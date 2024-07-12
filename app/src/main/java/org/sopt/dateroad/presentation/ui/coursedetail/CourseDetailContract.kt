package org.sopt.dateroad.presentation.ui.coursedetail

import org.sopt.dateroad.domain.model.Place
import org.sopt.dateroad.presentation.util.base.UiEvent
import org.sopt.dateroad.presentation.util.base.UiSideEffect
import org.sopt.dateroad.presentation.util.base.UiState
import org.sopt.dateroad.presentation.util.view.LoadState

class CourseDetailContract {
    data class CourseDetailUiState(
        val loadState: LoadState = LoadState.Idle,
        val isAccess:Boolean=false,
        val isMine:Boolean=false,
        val isFree:Boolean=false,
        val isEditBottomSheetOpen:Boolean = false,
        val isRegionBottomSheetOpen: Boolean = false,
        val isPointReadDialogOpen:Boolean = false,
        val isPointLackDialogOpen:Boolean = false,
        val isFreeReadDialogOpen:Boolean = false,
        val isLikedButtonChecked:Boolean=false,
        val like:Int,
        val tags: List<String> = listOf(),
        val imageList: List<Int> = listOf(),
        val currentImagePage:Int,
        val places:List<Place> = listOf(),
        ) : UiState

    sealed interface CourseDetailSideEffect : UiSideEffect

    sealed class CourseDetailEvent : UiEvent {
        data object OnLikeButtonClicked : CourseDetailEvent()
        data object CourseLookedByPoint:CourseDetailEvent()
        data object CourseLookedForFree:CourseDetailEvent()
        data object OnDeleteButtonClicked : CourseDetailEvent()
    }
}