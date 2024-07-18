package org.sopt.dateroad.presentation.ui.enroll

import org.sopt.dateroad.domain.model.CourseDetail
import org.sopt.dateroad.domain.model.DateDetail
import org.sopt.dateroad.domain.model.Enroll
import org.sopt.dateroad.domain.model.Place
import org.sopt.dateroad.domain.type.RegionType
import org.sopt.dateroad.presentation.type.EnrollScreenType
import org.sopt.dateroad.presentation.type.EnrollType
import org.sopt.dateroad.presentation.ui.component.bottomsheet.model.Picker
import org.sopt.dateroad.presentation.ui.component.textfield.model.TextFieldValidateResult
import org.sopt.dateroad.presentation.util.TimePicker
import org.sopt.dateroad.presentation.util.base.UiEvent
import org.sopt.dateroad.presentation.util.base.UiSideEffect
import org.sopt.dateroad.presentation.util.base.UiState
import org.sopt.dateroad.presentation.util.view.LoadState

class EnrollContract {
    data class EnrollUiState(
        val loadState: LoadState = LoadState.Idle,
        val fetchEnrollState: LoadState = LoadState.Idle,
        val enrollType: EnrollType = EnrollType.COURSE,
        val page: EnrollScreenType = EnrollScreenType.FIRST,
        val enroll: Enroll = Enroll(),
        val isEnrollButtonEnabled: Boolean = false,
        val titleValidateState: TextFieldValidateResult = TextFieldValidateResult.Basic,
        val dateValidateState: TextFieldValidateResult = TextFieldValidateResult.Basic,
        val isDatePickerBottomSheetOpen: Boolean = false,
        val datePickers: List<Picker> = listOf(
            Picker(items = (2000..2024).map { it.toString() }),
            Picker(items = (1..12).map { it.toString() }),
            Picker(items = (1..31).map { it.toString() })
        ),
        val isTimePickerBottomSheetOpen: Boolean = false,
        val timePickers: List<Picker> = listOf(
            Picker(items = listOf(TimePicker.AM, TimePicker.PM)),
            Picker(items = (1..12).map { it.toString() }),
            Picker(items = (0..59).map { it.toString().padStart(2, '0') })
        ),
        val isRegionBottomSheetOpen: Boolean = false,
        val onRegionBottomSheetRegionSelected: RegionType? = RegionType.SEOUL,
        val onRegionBottomSheetAreaSelected: Any? = null,
        val place: Place = Place(),
        val isPlaceEditable: Boolean = true,
        val isDurationBottomSheetOpen: Boolean = false,
        val durationPicker: List<Picker> = listOf(Picker(items = (1..12).map { (it * 0.5).toString() }))
    ) : UiState

    sealed interface EnrollSideEffect : UiSideEffect {
        data object PopBackStack : EnrollSideEffect
        data object NavigateToMyCourseRead : EnrollSideEffect
    }

    sealed class EnrollEvent : UiEvent {
        data object OnEnrollButtonClick : EnrollEvent()
        data object OnDateTextFieldClick : EnrollEvent()
        data object OnSelectedPlaceCourseTimeClick : EnrollEvent()
        data object OnDatePickerBottomSheetDismissRequest : EnrollEvent()
        data object OnTimePickerBottomSheetDismissRequest : EnrollEvent()
        data object OnRegionBottomSheetDismissRequest : EnrollEvent()
        data object OnDurationBottomSheetDismissRequest : EnrollEvent()
        data object OnTimeTextFieldClick : EnrollEvent()
        data object OnRegionTextFieldClick : EnrollEvent()
        data class FetchEnrollCourseType(val enrollType: EnrollType) : EnrollEvent()
        data class FetchCourseDetail(val fetchEnrollState: LoadState, val courseDetail: CourseDetail?): EnrollEvent()
        data class FetchDateDetail(val fetchEnrollState: LoadState, val dateDetail: DateDetail?): EnrollEvent()
        data class SetEnrollButtonEnabled(val isEnrollButtonEnabled: Boolean) : EnrollEvent()
        data class SetImage(val images: List<String>) : EnrollEvent()
        data class OnImageDeleteButtonClick(val index: Int) : EnrollEvent()
        data class OnTitleValueChange(val title: String) : EnrollEvent()
        data class OnDatePickerBottomSheetButtonClick(val date: String) : EnrollEvent()
        data class OnTimePickerBottomSheetButtonClick(val startAt: String) : EnrollEvent()
        data class OnDateChipClicked(val tag: String) : EnrollEvent()
        data class OnRegionBottomSheetRegionChipClick(val country: RegionType) : EnrollEvent()
        data class OnRegionBottomSheetAreaChipClick(val city: Any?) : EnrollEvent()
        data class OnRegionBottomSheetButtonClick(val region: RegionType?, val area: Any?) : EnrollEvent()
        data class OnAddPlaceButtonClick(val place: Place) : EnrollEvent()
        data class OnPlaceCardDragAndDrop(val places: List<Place>) : EnrollEvent()
        data class OnPlaceTitleValueChange(val placeTitle: String) : EnrollEvent()
        data class OnDurationBottomSheetButtonClick(val placeDuration: String) : EnrollEvent()
        data class OnEditableValueChange(val editable: Boolean) : EnrollEvent()
        data class OnPlaceCardDeleteButtonClick(val index: Int) : EnrollEvent()
        data class OnDescriptionValueChange(val description: String) : EnrollEvent()
        data class OnCostValueChange(val cost: String) : EnrollEvent()
        data class Enroll(val loadState: LoadState) : EnrollEvent()
    }
}
