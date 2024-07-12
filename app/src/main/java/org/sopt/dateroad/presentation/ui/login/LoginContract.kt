package org.sopt.dateroad.presentation.ui.login

import org.sopt.dateroad.presentation.util.base.UiEvent
import org.sopt.dateroad.presentation.util.base.UiSideEffect
import org.sopt.dateroad.presentation.util.base.UiState
import org.sopt.dateroad.presentation.util.view.LoadState

class LoginContract {
    data class LoginUiState(
        val loadState: LoadState = LoadState.Idle,
        val isLoggedIn: Boolean = false,
        val isWebViewOpened: Boolean = false
    ) : UiState

    sealed interface LoginSideEffect : UiSideEffect {
        data object NavigateToHome : LoginSideEffect
    }

    sealed class LoginEvent : UiEvent {
        data object Login : LoginEvent()
        data object WebViewClick : LoginEvent()
    }
}
