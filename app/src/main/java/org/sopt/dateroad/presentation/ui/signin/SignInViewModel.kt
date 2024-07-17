package org.sopt.dateroad.presentation.ui.signin

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kakao.sdk.auth.model.OAuthToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.dateroad.domain.model.EditProfile
import org.sopt.dateroad.domain.usecase.SetAccessTokenUseCase
import org.sopt.dateroad.presentation.ui.profile.ProfileContract
import javax.inject.Inject
import org.sopt.dateroad.presentation.util.base.BaseViewModel
import org.sopt.dateroad.presentation.util.view.LoadState

@HiltViewModel
class SignInViewModel @Inject constructor(
    val setAccessTokenUseCase: SetAccessTokenUseCase
) : BaseViewModel<SignInContract.SignInUiState, SignInContract.SignInSideEffect, SignInContract.SignInEvent>() {
    override fun createInitialState(): SignInContract.SignInUiState =
        SignInContract.SignInUiState()

    override suspend fun handleEvent(event: SignInContract.SignInEvent) {
        when (event) {
            is SignInContract.SignInEvent.PostSignIn -> setState { copy(isSignedIn = true) }
            is SignInContract.SignInEvent.OnWebViewClick -> setState { copy(isWebViewOpened = true) }
            is SignInContract.SignInEvent.WebViewClose -> setState { copy(isWebViewOpened = false) }
        }
    }
    fun setAccessToken(accessToken: String) {
        viewModelScope.launch {
            Log.d("ㅋㅋ","되냐?")
            setAccessTokenUseCase(accessToken)
        }
    }
}


