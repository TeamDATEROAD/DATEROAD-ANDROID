package org.sopt.dateroad.presentation.ui.look

import dagger.hilt.android.lifecycle.HiltViewModel
import org.sopt.dateroad.presentation.util.base.BaseViewModel
import org.sopt.dateroad.presentation.util.view.LoadState
import javax.inject.Inject

@HiltViewModel
class LookViewModel @Inject constructor() : BaseViewModel<LookContract.LookUiState, LookContract.LookSideEffect, LookContract.LookEvent>() {
    override fun createInitialState(): LookContract.LookUiState = LookContract.LookUiState()

    override suspend fun handleEvent(event: LookContract.LookEvent) {
        when (event) {
            is LookContract.LookEvent.FetchCourses -> fetchCourses()
            is LookContract.LookEvent.OnAreaButtonClicked -> {
                setState { copy(isRegionBottomSheetOpen = true, regionBottomSheetSelectedRegion = null, regionBottomSheetSelectedArea = null) }
            }

            is LookContract.LookEvent.OnResetButtonClicked -> {
                setState { copy(region = null, area = null, money = null) }
            }

            is LookContract.LookEvent.OnRegionBottomSheetDismissRequest -> {
                setState { copy(isRegionBottomSheetOpen = false) }
            }

            is LookContract.LookEvent.OnMoneyChipClicked -> {
                setState { copy(money = event.money) }
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

    private fun fetchCourses() {
        setState {
            copy(
                loadState = LoadState.Success,
                courses = listOf()
            )
        }
    }
}