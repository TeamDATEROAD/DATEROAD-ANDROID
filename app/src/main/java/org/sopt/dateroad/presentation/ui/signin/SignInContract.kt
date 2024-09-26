package org.sopt.dateroad.presentation.ui.signin

import org.sopt.dateroad.presentation.util.base.UiEvent
import org.sopt.dateroad.presentation.util.base.UiSideEffect
import org.sopt.dateroad.presentation.util.base.UiState
import org.sopt.dateroad.presentation.util.view.LoadState

class SignInContract {
    data class SignInUiState(
        val loadState: LoadState = LoadState.Idle,
        val authTokenLoadState: LoadState = LoadState.Idle,
        val isWebViewOpened: Boolean = false
    ) : UiState

    sealed interface SignInSideEffect : UiSideEffect {
        data object NavigateToOnboarding : SignInSideEffect
        data object NavigateToHome : SignInSideEffect
    }

    sealed class SignInEvent : UiEvent {
        data class PostSignIn(val loadState: LoadState) : SignInEvent()
        data class SetAuthToken(val authTokenLoadState: LoadState) : SignInEvent()
        data object OnWebViewClick : SignInEvent()
        data object WebViewClose : SignInEvent()
    }
}
