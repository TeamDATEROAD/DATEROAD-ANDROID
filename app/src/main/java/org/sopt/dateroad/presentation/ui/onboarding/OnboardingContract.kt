package org.sopt.dateroad.presentation.ui.onboarding

import org.sopt.dateroad.presentation.type.OnboardingType
import org.sopt.dateroad.presentation.util.base.UiEvent
import org.sopt.dateroad.presentation.util.base.UiSideEffect
import org.sopt.dateroad.presentation.util.base.UiState

class OnboardingContract {
    data class OnboardingUiState(
        val onboardingType: OnboardingType = OnboardingType.FIRST
    ) : UiState

    sealed interface OnboardingSideEffect : UiSideEffect {
        data object NavigateToProfile : OnboardingSideEffect
    }

    sealed class OnboardingEvent : UiEvent {
        data class SetOnboardingType(val onboardingType: OnboardingType) : OnboardingEvent()
    }
}
