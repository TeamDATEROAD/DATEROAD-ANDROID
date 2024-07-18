package org.sopt.dateroad.presentation.ui.look

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import org.sopt.dateroad.domain.type.MoneyTagType
import org.sopt.dateroad.domain.type.RegionType
import org.sopt.dateroad.domain.usecase.GetFilteredCourses
import org.sopt.dateroad.presentation.util.base.BaseViewModel
import org.sopt.dateroad.presentation.util.view.LoadState

@HiltViewModel
class LookViewModel @Inject constructor(
    private val getFilteredCourses: GetFilteredCourses
) : BaseViewModel<LookContract.LookUiState, LookContract.LookSideEffect, LookContract.LookEvent>() {
    override fun createInitialState(): LookContract.LookUiState = LookContract.LookUiState()

    override suspend fun handleEvent(event: LookContract.LookEvent) {
        when (event) {
            is LookContract.LookEvent.OnAreaButtonClicked -> {
                setState { copy(isRegionBottomSheetOpen = true, regionBottomSheetSelectedRegion = RegionType.SEOUL, regionBottomSheetSelectedArea = null) }
            }

            is LookContract.LookEvent.OnResetButtonClicked -> {
                setState { copy(region = null, area = null, money = null) }
            }

            is LookContract.LookEvent.OnRegionBottomSheetDismissRequest -> {
                setState { copy(isRegionBottomSheetOpen = false) }
            }

            is LookContract.LookEvent.FetchCourses -> setState { copy(loadState = event.loadState, courses = event.courses) }

            is LookContract.LookEvent.OnMoneyChipClicked -> {
                setState { copy(money = event.money.takeUnless { it == currentState.money }) }
            }

            is LookContract.LookEvent.OnRegionBottomSheetButtonClicked -> {
                setState { copy(isRegionBottomSheetOpen = false, region = event.region, area = event.area) }
            }

            is LookContract.LookEvent.OnRegionBottomSheetRegionClicked -> {
                setState { copy(regionBottomSheetSelectedRegion = event.region) }
            }

            is LookContract.LookEvent.OnRegionBottomSheetAreaClicked -> {
                setState { copy(regionBottomSheetSelectedArea = event.area) }
            }
        }
    }

    fun fetchFilteredCourses(country: RegionType?, city: Any?, cost: MoneyTagType?) {
        viewModelScope.launch {
            setEvent(
                LookContract.LookEvent.FetchCourses(loadState = LoadState.Loading, courses = currentState.courses)
            )
            getFilteredCourses(country = country, city = city, cost = cost).onSuccess { courses ->
                setEvent(
                    LookContract.LookEvent.FetchCourses(loadState = LoadState.Success, courses = courses)
                )
            }.onFailure {
                setEvent(
                    LookContract.LookEvent.FetchCourses(loadState = LoadState.Error, courses = currentState.courses)
                )
            }
        }
    }
}
