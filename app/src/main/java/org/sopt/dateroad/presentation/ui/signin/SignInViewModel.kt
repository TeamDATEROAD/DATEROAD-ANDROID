package org.sopt.dateroad.presentation.ui.signin

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import org.sopt.dateroad.presentation.util.base.BaseViewModel

@HiltViewModel
class SignInViewModel @Inject constructor() : BaseViewModel<SignInContract.SignInUiState, SignInContract.SignInSideEffect, SignInContract.SignInEvent>() {
    override fun createInitialState(): SignInContract.SignInUiState =
        SignInContract.SignInUiState()

    override suspend fun handleEvent(event: SignInContract.SignInEvent) {
        when (event) {
            is SignInContract.SignInEvent.PostSignIn -> setState { copy(isSignedIn = true) }
            is SignInContract.SignInEvent.WebViewClick -> setState { copy(isWebViewOpened = true) }
        }
    }
}
