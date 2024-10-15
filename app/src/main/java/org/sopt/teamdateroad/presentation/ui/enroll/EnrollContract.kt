package org.sopt.teamdateroad.presentation.ui.enroll

import org.sopt.teamdateroad.domain.model.CourseDetail
import org.sopt.teamdateroad.domain.model.Enroll
import org.sopt.teamdateroad.domain.model.Place
import org.sopt.teamdateroad.domain.model.TimelineDetail
import org.sopt.teamdateroad.domain.type.RegionType
import org.sopt.teamdateroad.presentation.type.EnrollScreenType
import org.sopt.teamdateroad.presentation.type.EnrollType
import org.sopt.teamdateroad.presentation.ui.component.bottomsheet.model.Picker
import org.sopt.teamdateroad.presentation.ui.component.textfield.model.TextFieldValidateResult
import org.sopt.teamdateroad.presentation.util.DatePicker.DAY_END
import org.sopt.teamdateroad.presentation.util.DatePicker.DAY_START
import org.sopt.teamdateroad.presentation.util.DatePicker.MONTH_END
import org.sopt.teamdateroad.presentation.util.DatePicker.MONTH_START
import org.sopt.teamdateroad.presentation.util.DatePicker.YEAR_END
import org.sopt.teamdateroad.presentation.util.DatePicker.YEAR_START
import org.sopt.teamdateroad.presentation.util.DatePicker.YEAR_START_INDEX
import org.sopt.teamdateroad.presentation.util.DurationPicker.DURATION_END
import org.sopt.teamdateroad.presentation.util.DurationPicker.DURATION_START
import org.sopt.teamdateroad.presentation.util.TimePicker
import org.sopt.teamdateroad.presentation.util.TimePicker.HOUR_END
import org.sopt.teamdateroad.presentation.util.TimePicker.HOUR_START
import org.sopt.teamdateroad.presentation.util.TimePicker.MINUTE_END
import org.sopt.teamdateroad.presentation.util.TimePicker.MINUTE_START
import org.sopt.teamdateroad.presentation.util.base.UiEvent
import org.sopt.teamdateroad.presentation.util.base.UiSideEffect
import org.sopt.teamdateroad.presentation.util.base.UiState
import org.sopt.teamdateroad.presentation.util.view.LoadState

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
            Picker(items = (YEAR_START..YEAR_END).map { it.toString() }, startIndex = YEAR_START_INDEX),
            Picker(items = (MONTH_START..MONTH_END).map { it.toString() }),
            Picker(items = (DAY_START..DAY_END).map { it.toString() })
        ),
        val isTimePickerBottomSheetOpen: Boolean = false,
        val timePickers: List<Picker> = listOf(
            Picker(items = listOf(TimePicker.AM, TimePicker.PM)),
            Picker(items = (HOUR_START..HOUR_END).map { it.toString() }),
            Picker(items = (MINUTE_START..MINUTE_END).map { it.toString().padStart(2, '0') })
        ),
        val isRegionBottomSheetOpen: Boolean = false,
        val onRegionBottomSheetRegionSelected: RegionType? = RegionType.SEOUL,
        val onRegionBottomSheetAreaSelected: Any? = null,
        val place: Place = Place(),
        val isPlaceEditable: Boolean = true,
        val isDurationBottomSheetOpen: Boolean = false,
        val durationPicker: List<Picker> = listOf(Picker(items = (DURATION_START..DURATION_END).map { (it * 0.5).toString() })),
        val isEnrollSuccessDialogOpen: Boolean = false
    ) : UiState

    sealed interface EnrollSideEffect : UiSideEffect {
        data object PopBackStack : EnrollSideEffect
        data object NavigateToMyCourseRead : EnrollSideEffect
    }

    sealed class EnrollEvent : UiEvent {
        data object OnTopBarBackButtonClick : EnrollEvent()
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
        data class FetchCourseDetail(val fetchEnrollState: LoadState, val courseDetail: CourseDetail?) : EnrollEvent()
        data class FetchTimelineDetail(val fetchEnrollState: LoadState, val timelineDetail: TimelineDetail?) : EnrollEvent()
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
        data class SetTitleValidationState(val titleValidationState: TextFieldValidateResult) : EnrollEvent()
        data class SetDateValidationState(val dateValidationState: TextFieldValidateResult) : EnrollEvent()
        data class SetIsEnrollSuccessDialogOpen(val isEnrollSuccessDialogOpen: Boolean) : EnrollEvent()
    }
}
