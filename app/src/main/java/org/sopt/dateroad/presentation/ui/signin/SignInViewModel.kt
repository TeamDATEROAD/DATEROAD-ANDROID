package org.sopt.dateroad.presentation.ui.signin

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import org.sopt.dateroad.domain.model.SignIn
import org.sopt.dateroad.domain.usecase.GetAccessTokenUseCase
import org.sopt.dateroad.domain.usecase.GetRefreshTokenUseCase
import org.sopt.dateroad.domain.usecase.PostSignInUseCase
import org.sopt.dateroad.domain.usecase.SetAccessTokenUseCase
import org.sopt.dateroad.domain.usecase.SetRefreshTokenUseCase
import org.sopt.dateroad.presentation.util.Token
import org.sopt.dateroad.presentation.util.base.BaseViewModel
import org.sopt.dateroad.presentation.util.view.LoadState

@HiltViewModel
class SignInViewModel @Inject constructor(
    val getAccessTokenUseCase: GetAccessTokenUseCase,
    val getRefreshTokenUseCase: GetRefreshTokenUseCase,
    val setAccessTokenUseCase: SetAccessTokenUseCase,
    val setRefreshTokenUseCase: SetRefreshTokenUseCase,
    val postSignInUseCase: PostSignInUseCase
) : BaseViewModel<SignInContract.SignInUiState, SignInContract.SignInSideEffect, SignInContract.SignInEvent>() {
    override fun createInitialState(): SignInContract.SignInUiState =
        SignInContract.SignInUiState()

    override suspend fun handleEvent(event: SignInContract.SignInEvent) {
        when (event) {
            is SignInContract.SignInEvent.PostSignIn -> setState { copy(loadState = event.loadState) }
            is SignInContract.SignInEvent.OnWebViewClick -> setState { copy(isWebViewOpened = true) }
            is SignInContract.SignInEvent.WebViewClose -> setState { copy(isWebViewOpened = false) }
            is SignInContract.SignInEvent.SetAuthToken -> setState { copy(authTokenLoadState = event.authTokenLoadState) }
        }
    }

    fun setKakaoAccessToken(accessToken: String) {
        setAccessTokenUseCase(accessToken)
        setEvent(SignInContract.SignInEvent.SetAuthToken(authTokenLoadState = LoadState.Success))
    }

    fun postSignIn(signIn: SignIn) {
        viewModelScope.launch {
            setEvent(SignInContract.SignInEvent.PostSignIn(loadState = LoadState.Loading))
            postSignInUseCase(authorization = getAccessTokenUseCase(), signIn = signIn).onSuccess { auth ->
                setEvent(SignInContract.SignInEvent.PostSignIn(loadState = LoadState.Success))
                setAccessTokenUseCase(Token.BEARER + auth.accessToken)
                setRefreshTokenUseCase(auth.refreshToken)
            }.onFailure {
                setEvent(SignInContract.SignInEvent.PostSignIn(loadState = LoadState.Error))
            }
        }
    }

    fun checkAutoLogin() {
        if (getRefreshTokenUseCase().isNotEmpty()) setEvent(SignInContract.SignInEvent.PostSignIn(LoadState.Success))
    }
}
