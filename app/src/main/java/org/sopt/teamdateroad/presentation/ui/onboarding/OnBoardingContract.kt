package org.sopt.teamdateroad.presentation.ui.onboarding

import org.sopt.teamdateroad.presentation.util.base.UiEvent
import org.sopt.teamdateroad.presentation.util.base.UiSideEffect
import org.sopt.teamdateroad.presentation.util.base.UiState

class OnBoardingContract {
    class OnBoardingUiState : UiState

    sealed interface OnBoardingSideEffect : UiSideEffect {
        data object NavigateToProfile : OnBoardingSideEffect
        data object NavigateToSignIn : OnBoardingSideEffect
    }

    sealed class OnBoardingEvent : UiEvent
}
