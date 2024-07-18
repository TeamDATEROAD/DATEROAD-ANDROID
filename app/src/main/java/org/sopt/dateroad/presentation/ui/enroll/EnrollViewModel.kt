package org.sopt.dateroad.presentation.ui.enroll

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.dateroad.data.mapper.toEntity.toEnroll
import org.sopt.dateroad.domain.type.RegionType
import org.sopt.dateroad.domain.usecase.GetCourseDetailUseCase
import org.sopt.dateroad.domain.usecase.GetDateDetailUseCase
import org.sopt.dateroad.domain.usecase.PostCourseUseCase
import org.sopt.dateroad.domain.usecase.PostDateUseCase
import org.sopt.dateroad.presentation.type.EnrollScreenType
import org.sopt.dateroad.presentation.type.EnrollType
import org.sopt.dateroad.presentation.ui.component.textfield.model.TextFieldValidateResult
import org.sopt.dateroad.presentation.util.DatePicker
import org.sopt.dateroad.presentation.util.EnrollScreen.TITLE_MIN_LENGTH
import org.sopt.dateroad.presentation.util.base.BaseViewModel
import org.sopt.dateroad.presentation.util.view.LoadState
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class EnrollViewModel @Inject constructor(
    private val getCourseDetailUseCase: GetCourseDetailUseCase,
    private val getDateDetailUseCase: GetDateDetailUseCase,
    private val postCourseUseCase: PostCourseUseCase,
    private val postDateUseCase: PostDateUseCase
) : BaseViewModel<EnrollContract.EnrollUiState, EnrollContract.EnrollSideEffect, EnrollContract.EnrollEvent>() {
    override fun createInitialState(): EnrollContract.EnrollUiState = EnrollContract.EnrollUiState()

    override suspend fun handleEvent(event: EnrollContract.EnrollEvent) {
        when (event) {
            is EnrollContract.EnrollEvent.OnEnrollButtonClick -> {
                when (currentState.enrollType) {
                    EnrollType.COURSE -> {
                        when (currentState.page) {
                            EnrollScreenType.FIRST -> setState { copy(page = EnrollScreenType.SECOND) }
                            EnrollScreenType.SECOND -> setState { copy(page = EnrollScreenType.THIRD) }
                            EnrollScreenType.THIRD -> postCourse()
                        }
                    }

                    EnrollType.TIMELINE -> {
                        when (currentState.page) {
                            EnrollScreenType.FIRST -> setState { copy(page = EnrollScreenType.SECOND) }
                            EnrollScreenType.SECOND -> postTimeline()
                            EnrollScreenType.THIRD -> Unit
                        }
                    }
                }
            }

            is EnrollContract.EnrollEvent.OnDateTextFieldClick -> setState { copy(isDatePickerBottomSheetOpen = true) }
            is EnrollContract.EnrollEvent.OnTimeTextFieldClick -> setState { copy(isTimePickerBottomSheetOpen = true) }
            is EnrollContract.EnrollEvent.OnRegionTextFieldClick -> setState { copy(isRegionBottomSheetOpen = true, onRegionBottomSheetRegionSelected = RegionType.SEOUL, onRegionBottomSheetAreaSelected = null) }
            is EnrollContract.EnrollEvent.OnSelectedPlaceCourseTimeClick -> setState { copy(isDurationBottomSheetOpen = true) }
            is EnrollContract.EnrollEvent.OnDatePickerBottomSheetDismissRequest -> setState { copy(isDatePickerBottomSheetOpen = false) }
            is EnrollContract.EnrollEvent.OnTimePickerBottomSheetDismissRequest -> setState { copy(isTimePickerBottomSheetOpen = false) }
            is EnrollContract.EnrollEvent.OnRegionBottomSheetDismissRequest -> setState { copy(isRegionBottomSheetOpen = false) }
            is EnrollContract.EnrollEvent.OnDurationBottomSheetDismissRequest -> setState { copy(isDurationBottomSheetOpen = false) }
            is EnrollContract.EnrollEvent.FetchEnrollCourseType -> setState { copy(enrollType = event.enrollType) }
            is EnrollContract.EnrollEvent.FetchCourseDetail -> setState { copy(fetchEnrollState = event.fetchEnrollState, enroll = event.courseDetail?.toEnroll() ?: currentState.enroll) }
            is EnrollContract.EnrollEvent.FetchDateDetail -> setState { copy(fetchEnrollState = event.fetchEnrollState, enroll = event.dateDetail?.toEnroll() ?: currentState.enroll) }
            is EnrollContract.EnrollEvent.SetEnrollButtonEnabled -> setState { copy(isEnrollButtonEnabled = event.isEnrollButtonEnabled) }
            is EnrollContract.EnrollEvent.SetImage -> setState { copy(enroll = currentState.enroll.copy(images = event.images)) }
            is EnrollContract.EnrollEvent.OnImageDeleteButtonClick -> setState { copy(enroll = currentState.enroll.copy(images = currentState.enroll.images.toMutableList().apply { removeAt(event.index) })) }
            is EnrollContract.EnrollEvent.OnTitleValueChange -> setState {
                copy(
                    enroll = currentState.enroll.copy(title = event.title),
                    titleValidateState = when {
                        event.title.isEmpty() -> TextFieldValidateResult.Basic
                        event.title.length >= TITLE_MIN_LENGTH -> TextFieldValidateResult.Success
                        else -> TextFieldValidateResult.ValidationError
                    }
                )
            }

            is EnrollContract.EnrollEvent.OnDatePickerBottomSheetButtonClick -> setState {
                copy(
                    enroll = currentState.enroll.copy(date = event.date),
                    dateValidateState = when {
                        event.date.isEmpty() -> TextFieldValidateResult.Basic
                        currentState.enrollType == EnrollType.COURSE && LocalDate.parse(event.date, DateTimeFormatter.ofPattern(DatePicker.DATE_PATTERN)).isAfter(LocalDate.now()) -> TextFieldValidateResult.ValidationError
                        else -> TextFieldValidateResult.Success
                    },
                    isDatePickerBottomSheetOpen = false
                )
            }

            is EnrollContract.EnrollEvent.OnTimePickerBottomSheetButtonClick -> setState { copy(enroll = currentState.enroll.copy(startAt = event.startAt), isTimePickerBottomSheetOpen = false) }
            is EnrollContract.EnrollEvent.OnDateChipClicked -> setState {
                copy(
                    enroll = enroll.copy(
                        tags = currentState.enroll.tags.toMutableList().apply {
                            if (contains(event.tag)) {
                                remove(event.tag)
                            } else if (size < 3) {
                                add(event.tag)
                            }
                        }
                    )
                )
            }

            is EnrollContract.EnrollEvent.OnRegionBottomSheetRegionChipClick -> setState { copy(onRegionBottomSheetRegionSelected = event.country) }
            is EnrollContract.EnrollEvent.OnRegionBottomSheetAreaChipClick -> setState { copy(onRegionBottomSheetAreaSelected = event.city) }
            is EnrollContract.EnrollEvent.OnRegionBottomSheetButtonClick -> setState { copy(isRegionBottomSheetOpen = false, enroll = currentState.enroll.copy(country = event.region, city = event.area)) }
            is EnrollContract.EnrollEvent.OnAddPlaceButtonClick -> setState { copy(enroll = currentState.enroll.copy(places = currentState.enroll.places.toMutableList().apply { add(event.place) }), place = currentState.place.copy(title = "", duration = "")) }
            is EnrollContract.EnrollEvent.OnPlaceCardDragAndDrop -> setState { copy(enroll = currentState.enroll.copy(places = event.places)) }
            is EnrollContract.EnrollEvent.OnPlaceTitleValueChange -> setState { copy(place = currentState.place.copy(title = event.placeTitle)) }
            is EnrollContract.EnrollEvent.OnDurationBottomSheetButtonClick -> setState { copy(isDurationBottomSheetOpen = false, place = currentState.place.copy(duration = event.placeDuration)) }
            is EnrollContract.EnrollEvent.OnEditableValueChange -> setState { copy(isPlaceEditable = event.editable) }
            is EnrollContract.EnrollEvent.OnPlaceCardDeleteButtonClick -> setState { copy(enroll = currentState.enroll.copy(places = currentState.enroll.places.toMutableList().apply { removeAt(event.index) })) }
            is EnrollContract.EnrollEvent.OnDescriptionValueChange -> setState { copy(enroll = currentState.enroll.copy(description = event.description)) }
            is EnrollContract.EnrollEvent.OnCostValueChange -> setState { copy(enroll = currentState.enroll.copy(cost = event.cost)) }
            is EnrollContract.EnrollEvent.Enroll -> setState { copy(loadState = event.loadState) }
        }
    }

    fun fetchCourseDetail(courseId: Int) {
        viewModelScope.launch {
            setEvent(EnrollContract.EnrollEvent.FetchCourseDetail(fetchEnrollState = LoadState.Loading, courseDetail = null))
            getCourseDetailUseCase(courseId = courseId).onSuccess { courseDetail ->
                setEvent(EnrollContract.EnrollEvent.FetchCourseDetail(fetchEnrollState = LoadState.Success, courseDetail = courseDetail))
            }
            setEvent(EnrollContract.EnrollEvent.FetchCourseDetail(fetchEnrollState = LoadState.Error, courseDetail = null))
        }
    }

    fun fetchDateDetail(dateId: Int) {
        viewModelScope.launch {
            setEvent(EnrollContract.EnrollEvent.FetchDateDetail(fetchEnrollState = LoadState.Loading, dateDetail = null))
            getDateDetailUseCase(dateId = dateId).onSuccess { dateDetail ->
                setEvent(EnrollContract.EnrollEvent.FetchDateDetail(fetchEnrollState = LoadState.Success, dateDetail = dateDetail))
            }.onFailure {
                setEvent(EnrollContract.EnrollEvent.FetchDateDetail(fetchEnrollState = LoadState.Error, dateDetail = null))
            }
        }
    }

    private fun postCourse() {
        viewModelScope.launch {
            setEvent(EnrollContract.EnrollEvent.Enroll(loadState = LoadState.Loading))
            postCourseUseCase(enroll = currentState.enroll).onSuccess {
                setEvent(EnrollContract.EnrollEvent.Enroll(loadState = LoadState.Success))
            }.onFailure {
                setEvent(EnrollContract.EnrollEvent.Enroll(loadState = LoadState.Error))
            }
        }
    }

    private fun postTimeline() {
        viewModelScope.launch {
            setEvent(EnrollContract.EnrollEvent.Enroll(loadState = LoadState.Loading))
            postDateUseCase(date = currentState.enroll).onSuccess {
                setEvent(EnrollContract.EnrollEvent.Enroll(loadState = LoadState.Success))
            }.onFailure {
                setEvent(EnrollContract.EnrollEvent.Enroll(loadState = LoadState.Error))
            }
        }
    }
}
