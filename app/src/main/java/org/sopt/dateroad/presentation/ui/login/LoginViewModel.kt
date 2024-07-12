package org.sopt.dateroad.presentation.ui.login

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import org.sopt.dateroad.presentation.util.base.BaseViewModel

@HiltViewModel
class LoginViewModel @Inject constructor() : BaseViewModel<LoginContract.LoginUiState, LoginContract.LoginSideEffect, LoginContract.LoginEvent>() {
    override fun createInitialState(): LoginContract.LoginUiState =
        LoginContract.LoginUiState()

    override suspend fun handleEvent(event: LoginContract.LoginEvent) {
        when (event) {
            is LoginContract.LoginEvent.Login -> setState { copy(isLoggedIn = true) }
            is LoginContract.LoginEvent.WebViewClick -> setState { copy(isWebViewOpened = true) }
        }
    }
}
