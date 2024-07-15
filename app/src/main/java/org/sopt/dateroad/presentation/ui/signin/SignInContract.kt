package org.sopt.dateroad.presentation.ui.signin

import org.sopt.dateroad.presentation.util.base.UiEvent
import org.sopt.dateroad.presentation.util.base.UiSideEffect
import org.sopt.dateroad.presentation.util.base.UiState
import org.sopt.dateroad.presentation.util.view.LoadState

class SignInContract {
    data class SignInUiState(
        val loadState: LoadState = LoadState.Idle,
        val isSignedIn: Boolean = false,
        var isWebViewOpened: Boolean = false
    ) : UiState

    sealed interface SignInSideEffect : UiSideEffect {
        data object NavigateToHome : SignInSideEffect
    }

    sealed class SignInEvent : UiEvent {
        data object PostSignIn : SignInEvent()
        data object WebViewClick : SignInEvent()
        data object WebViewClose : SignInEvent()
    }
}
