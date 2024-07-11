package org.sopt.dateroad.presentation.ui.onboarding

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import org.sopt.dateroad.presentation.util.base.BaseViewModel

@HiltViewModel
class OnboardingViewModel @Inject constructor() : BaseViewModel<OnboardingContract.OnboardingUiState, OnboardingContract.OnboardingSideEffect, OnboardingContract.OnboardingEvent>() {
    override fun createInitialState(): OnboardingContract.OnboardingUiState =
        OnboardingContract.OnboardingUiState()

    override suspend fun handleEvent(event: OnboardingContract.OnboardingEvent) {
        when (event) {
            is OnboardingContract.OnboardingEvent.SetOnboardingType -> setState { copy(onboardingType = event.onboardingType) }
        }
    }
}
