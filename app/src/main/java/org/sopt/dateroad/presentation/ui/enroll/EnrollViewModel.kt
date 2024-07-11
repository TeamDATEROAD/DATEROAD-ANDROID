package org.sopt.dateroad.presentation.ui.enroll

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import org.sopt.dateroad.presentation.type.RegionType
import org.sopt.dateroad.presentation.ui.component.textfield.model.TextFieldValidateResult
import org.sopt.dateroad.presentation.util.EnrollScreen.TITLE_MIN_LENGTH
import org.sopt.dateroad.presentation.util.base.BaseViewModel
import org.sopt.dateroad.presentation.util.view.LoadState

@HiltViewModel
class EnrollViewModel @Inject constructor() : BaseViewModel<EnrollContract.EnrollUiState, EnrollContract.EnrollSideEffect, EnrollContract.EnrollEvent>() {
    override fun createInitialState(): EnrollContract.EnrollUiState = EnrollContract.EnrollUiState()

    override suspend fun handleEvent(event: EnrollContract.EnrollEvent) {
        when (event) {
            is EnrollContract.EnrollEvent.OnEnrollButtonClick -> postCourse()
            is EnrollContract.EnrollEvent.OnDateTextFieldClick -> setState { copy(isDatePickerBottomSheetOpen = true) }
            is EnrollContract.EnrollEvent.OnTimeTextFieldClick -> setState { copy(isTimePickerBottomSheetOpen = true) }
            is EnrollContract.EnrollEvent.OnRegionTextFieldClick -> setState { copy(isRegionBottomSheetOpen = true, onRegionBottomSheetRegionSelected = RegionType.SEOUL, onRegionBottomSheetAreaSelected = null) }
            is EnrollContract.EnrollEvent.OnDatePickerBottomSheetDismissRequest -> setState { copy(isDatePickerBottomSheetOpen = false) }
            is EnrollContract.EnrollEvent.OnTimePickerBottomSheetDismissRequest -> setState { copy(isTimePickerBottomSheetOpen = false) }
            is EnrollContract.EnrollEvent.OnRegionBottomSheetDismissRequest -> setState { copy(isRegionBottomSheetOpen = false) }
            is EnrollContract.EnrollEvent.OnPlaceDurationClick -> setState { copy(isDurationBottomSheetOpen = true) }
            is EnrollContract.EnrollEvent.OnPageChange -> setState { copy(page = event.page) }
            is EnrollContract.EnrollEvent.SetImage -> setState { copy(images = event.images) }
            is EnrollContract.EnrollEvent.OnDeleteButtonClick -> setState { copy(images = currentState.images.toMutableList().apply { removeAt(event.index) }) }
            is EnrollContract.EnrollEvent.OnTitleValueChange -> setState {
                copy(
                    title = event.title,
                    titleValidateState = when {
                        event.title.length < TITLE_MIN_LENGTH -> TextFieldValidateResult.ValidationError
                        else -> TextFieldValidateResult.Basic
                    }
                )
            }
            is EnrollContract.EnrollEvent.OnDatePickerBottomSheetButtonClick -> setState { copy(date = event.date, isDatePickerBottomSheetOpen = false) }
            is EnrollContract.EnrollEvent.OnTimePickerBottomSheetButtonClick -> setState { copy(startAt = event.startAt, isTimePickerBottomSheetOpen = false) }
            is EnrollContract.EnrollEvent.OnDateChipClicked -> setState {
                copy(
                    tags = currentState.tags.toMutableList().apply {
                        if (contains(event.tag)) {
                            remove(event.tag)
                        } else if (size < 3) {
                            add(event.tag)
                        }
                    }
                )
            }
            is EnrollContract.EnrollEvent.OnRegionBottomSheetRegionChipClick -> setState { copy(onRegionBottomSheetRegionSelected = event.country) }
            is EnrollContract.EnrollEvent.OnRegionBottomSheetAreaChipClick -> setState { copy(onRegionBottomSheetAreaSelected = event.city) }
            is EnrollContract.EnrollEvent.OnRegionBottomSheetButtonClick -> setState { copy(isRegionBottomSheetOpen = false, country = event.region, city = event.area) }
            is EnrollContract.EnrollEvent.OnAddPlaceButtonClick -> setState { copy(place = currentState.place.toMutableList().apply { add(event.place) }) }
            is EnrollContract.EnrollEvent.OnPlaceTitleValueChange -> setState { copy(title = event.title) }
            is EnrollContract.EnrollEvent.OnPlaceEditButtonClick -> setState { copy(isPlaceEditable = event.editable) }
            is EnrollContract.EnrollEvent.OnDescriptionValueChange -> setState { copy(description = event.description) }
            is EnrollContract.EnrollEvent.OnCostValueChange -> setState { copy(cost = event.cost) }
        }
    }

    private fun postCourse() {
        setState { copy(loadState = LoadState.Success) }
    }
}
