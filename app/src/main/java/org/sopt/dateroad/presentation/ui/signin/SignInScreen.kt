package org.sopt.dateroad.presentation.ui.signin

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import org.sopt.dateroad.R
import org.sopt.dateroad.domain.model.SignIn
import org.sopt.dateroad.presentation.ui.component.button.DateRoadKakaoLoginButton
import org.sopt.dateroad.presentation.ui.component.view.DateRoadLoadingView
import org.sopt.dateroad.presentation.ui.component.view.DateRoadWebView
import org.sopt.dateroad.presentation.util.WebViewUrl.PRIVACY_POLICY_URL
import org.sopt.dateroad.presentation.util.modifier.noRippleClickable
import org.sopt.dateroad.presentation.util.view.LoadState
import org.sopt.dateroad.ui.theme.DateRoadTheme

fun setLayoutLoginKakaoClickListener(context: Context, callback: (OAuthToken?, Throwable?) -> Unit) {
    if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
        UserApiClient.instance.loginWithKakaoTalk(context, callback = callback)
    } else {
        UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
    }
}

@Composable
fun SignInRoute(
    viewModel: SignInViewModel = hiltViewModel(),
    navigateToOnboarding: () -> Unit,
    navigateToHome: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

    val callback: (OAuthToken?, Throwable?) -> Unit = { oAuthToken, message ->
        if (oAuthToken != null) {
            viewModel.setKakaoAccessToken(oAuthToken.accessToken)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.checkAutoLogin()
    }

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { signInSideEffect ->
                when (signInSideEffect) {
                    is SignInContract.SignInSideEffect.NavigateToOnboarding -> {
                        navigateToOnboarding()
                    }

                    is SignInContract.SignInSideEffect.NavigateToHome -> navigateToHome()
                }
            }
    }

    LaunchedEffect(uiState.authTokenLoadState) {
        when (uiState.authTokenLoadState) {
            LoadState.Success -> viewModel.postSignIn(signIn = SignIn("KAKAO"))
            else -> Unit
        }
    }

    when (uiState.loadState) {
        LoadState.Idle -> {
            SignInScreen(
                signInUiState = uiState,
                onSignInClicked = {
                    setLayoutLoginKakaoClickListener(context = context, callback = callback)
                },
                onWebViewClicked = { viewModel.setEvent(SignInContract.SignInEvent.OnWebViewClick) },
                webViewClose = { viewModel.setEvent(SignInContract.SignInEvent.WebViewClose) }
            )
        }

        LoadState.Loading -> DateRoadLoadingView()

        LoadState.Success -> navigateToHome()

        LoadState.Error -> navigateToOnboarding()
    }
}

@Composable
fun SignInScreen(
    signInUiState: SignInContract.SignInUiState = SignInContract.SignInUiState(),
    onSignInClicked: () -> Unit,
    onWebViewClicked: () -> Unit,
    webViewClose: () -> Unit
) {
    if (signInUiState.isWebViewOpened) {
        DateRoadWebView(url = PRIVACY_POLICY_URL, onClose = webViewClose)
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DateRoadTheme.colors.purple600),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(226f))
            Image(painter = painterResource(id = R.drawable.ic_splash_logo), contentDescription = null)
            Spacer(modifier = Modifier.weight(167f))
            DateRoadKakaoLoginButton(
                modifier = Modifier.padding(horizontal = 30.dp),
                onClick = onSignInClicked
            )
            Spacer(modifier = Modifier.weight(16f))
            Text(
                text = "개인정보처리방침",
                color = DateRoadTheme.colors.gray200,
                style = DateRoadTheme.typography.bodyMed15,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.noRippleClickable(onClick = onWebViewClicked)
            )
            Spacer(modifier = Modifier.weight(37f))
        }
    }
}
