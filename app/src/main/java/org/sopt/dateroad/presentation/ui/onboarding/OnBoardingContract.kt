package org.sopt.dateroad.presentation.ui.onboarding

import org.sopt.dateroad.presentation.util.base.UiEvent
import org.sopt.dateroad.presentation.util.base.UiSideEffect
import org.sopt.dateroad.presentation.util.base.UiState

class OnBoardingContract {
    class OnBoardingUiState : UiState

    sealed interface OnBoardingSideEffect : UiSideEffect {
        data object NavigateToProfile : OnBoardingSideEffect
    }
    sealed class OnBoardingEvent : UiEvent
}
