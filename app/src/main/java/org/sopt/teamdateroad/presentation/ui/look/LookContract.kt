package org.sopt.teamdateroad.presentation.ui.look

import org.sopt.teamdateroad.domain.model.Course
import org.sopt.teamdateroad.domain.type.MoneyTagType
import org.sopt.teamdateroad.domain.type.RegionType
import org.sopt.teamdateroad.presentation.util.base.UiEvent
import org.sopt.teamdateroad.presentation.util.base.UiSideEffect
import org.sopt.teamdateroad.presentation.util.base.UiState
import org.sopt.teamdateroad.presentation.util.view.LoadState

class LookContract {
    data class LookUiState(
        val loadState: LoadState = LoadState.Idle,
        val isRegionBottomSheetOpen: Boolean = false,
        val region: RegionType? = null,
        val area: Any? = null,
        val money: MoneyTagType? = null,
        val regionBottomSheetSelectedRegion: RegionType? = RegionType.SEOUL,
        val regionBottomSheetSelectedArea: Any? = null,
        val courses: List<Course> = listOf()
    ) : UiState

    sealed interface LookSideEffect : UiSideEffect {
        data class NavigateToCourseDetail(val courseId: Int) : LookSideEffect
        data object NavigateToEnroll : LookSideEffect
    }

    sealed class LookEvent : UiEvent {
        data object OnAreaButtonClicked : LookEvent()
        data object OnResetButtonClicked : LookEvent()
        data object OnRegionBottomSheetDismissRequest : LookEvent()
        data class FetchCourses(val loadState: LoadState, val courses: List<Course>) : LookEvent()
        data class OnMoneyChipClicked(val money: MoneyTagType?) : LookEvent()
        data class OnRegionBottomSheetButtonClicked(val region: RegionType?, val area: Any?) : LookEvent()
        data class OnRegionBottomSheetRegionClicked(val region: RegionType?) : LookEvent()
        data class OnRegionBottomSheetAreaClicked(val area: Any?) : LookEvent()
    }
}
