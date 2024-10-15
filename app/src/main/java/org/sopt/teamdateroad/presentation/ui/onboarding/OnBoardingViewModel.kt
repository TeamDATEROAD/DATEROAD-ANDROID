package org.sopt.teamdateroad.presentation.ui.onboarding

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import org.sopt.teamdateroad.presentation.util.base.BaseViewModel

@HiltViewModel
class OnBoardingViewModel @Inject constructor() : BaseViewModel<OnBoardingContract.OnBoardingUiState, OnBoardingContract.OnBoardingSideEffect, OnBoardingContract.OnBoardingEvent>() {
    override fun createInitialState(): OnBoardingContract.OnBoardingUiState =
        OnBoardingContract.OnBoardingUiState()

    override suspend fun handleEvent(event: OnBoardingContract.OnBoardingEvent) {}
}
