package org.sopt.dateroad.presentation.ui.enroll

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import org.sopt.dateroad.presentation.util.base.BaseViewModel
import org.sopt.dateroad.presentation.util.view.LoadState

@HiltViewModel
class EnrollViewModel @Inject constructor() : BaseViewModel<EnrollContract.EnrollUiState, EnrollContract.EnrollSideEffect, EnrollContract.EnrollEvent>() {
    override fun createInitialState(): EnrollContract.EnrollUiState = EnrollContract.EnrollUiState()

    override suspend fun handleEvent(event: EnrollContract.EnrollEvent) {
        when (event) {
            is EnrollContract.EnrollEvent.OnEnrollButtonClicked -> postCourse()
            is EnrollContract.EnrollEvent.OnPlaceDurationClick -> setState { copy(isDurationBottomSheetOpen = true) }
            is EnrollContract.EnrollEvent.OnPageChange -> setState { copy(page = event.page) }
            is EnrollContract.EnrollEvent.OnPhotoButtonClick -> setState { copy(images = event.images) }
            is EnrollContract.EnrollEvent.OnDeleteButtonClick -> setState { copy(images = currentState.images.toMutableList().apply { removeAt(event.index) }) }
            is EnrollContract.EnrollEvent.OnTitleValueChange -> setState { copy(title = event.title) }
            is EnrollContract.EnrollEvent.OnDatePickerBottomSheetButtonClicked -> setState { copy(date = event.date) }
            is EnrollContract.EnrollEvent.OnTimePickerBottomSheetButtonClicked -> setState { copy(startAt = event.startAt) }
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

            is EnrollContract.EnrollEvent.OnRegionBottomSheetRegionChipClicked -> setState { copy(country = event.country) }
            is EnrollContract.EnrollEvent.OnRegionBottomSheetAreaChipClicked -> setState { copy(city = event.city) }
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
