package org.sopt.dateroad.presentation.ui.look

import org.sopt.dateroad.domain.model.Course
import org.sopt.dateroad.presentation.type.MoneyTagType
import org.sopt.dateroad.presentation.type.RegionType
import org.sopt.dateroad.presentation.util.base.UiEvent
import org.sopt.dateroad.presentation.util.base.UiSideEffect
import org.sopt.dateroad.presentation.util.base.UiState
import org.sopt.dateroad.presentation.util.view.LoadState

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
        data object NavigateToEnroll : LookSideEffect
    }

    sealed class LookEvent : UiEvent {
        data object FetchCourses : LookEvent()
        data object OnAreaButtonClicked : LookEvent()
        data object OnResetButtonClicked : LookEvent()
        data object OnRegionBottomSheetDismissRequest : LookEvent()
        data class OnMoneyChipClicked(val money: MoneyTagType?) : LookEvent()
        data class OnRegionBottomSheetButtonClicked(val region: RegionType?, val area: Any?) : LookEvent()
        data class OnRegionBottomSheetRegionClicked(val region: RegionType?) : LookEvent()
        data class OnRegionBottomSheetAreaClicked(val area: Any?) : LookEvent()
    }
}
