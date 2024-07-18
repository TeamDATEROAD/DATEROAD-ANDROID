package org.sopt.dateroad.presentation.ui.signin

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import org.sopt.dateroad.R
import org.sopt.dateroad.presentation.ui.component.button.DateRoadKakaoLoginButton
import org.sopt.dateroad.presentation.ui.component.webview.PrivacyPolicyWebView
import org.sopt.dateroad.presentation.util.CourseDetail.PRIVACY_POLICY_URL
import org.sopt.dateroad.presentation.util.modifier.noRippleClickable
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun SignInRoute(
    viewModel: SignInViewModel = hiltViewModel(),
    navigateToOnboarding: () -> Unit,
    navigateToHome: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { signInSideEffect ->
                when (signInSideEffect) {
                    is SignInContract.SignInSideEffect.NavigateToOnboarding -> navigateToOnboarding()
                    is SignInContract.SignInSideEffect.NavigateToHome -> navigateToHome()
                }
            }
    }

    SignInScreen(
        signInUiState = uiState,
        onSignInClicked = { viewModel.setSideEffect(SignInContract.SignInSideEffect.NavigateToOnboarding) },
        onWebViewClicked = {
            viewModel.setEvent(
                SignInContract.SignInEvent.OnWebViewClick,
                org.sopt.dateroad.presentation.ui.home.HomeContract.HomeEvent.FetchRemainingPoints(loadState = org.sopt.dateroad.presentation.util.view.LoadState.Loading, remainingPoints = currentState.remainingPoints)
            )
        },
        webViewClose = {
            viewModel.setEvent(
                SignInContract.SignInEvent.WebViewClose,
                org.sopt.dateroad.presentation.ui.home.HomeContract.HomeEvent.FetchRemainingPoints(loadState = org.sopt.dateroad.presentation.util.view.LoadState.Loading, remainingPoints = currentState.remainingPoints)
            )
        }
    )
}

@Composable
fun SignInScreen(
    signInUiState: SignInContract.SignInUiState = SignInContract.SignInUiState(),
    onSignInClicked: () -> Unit,
    onWebViewClicked: () -> Unit,
    webViewClose: () -> Unit
) {
    if (signInUiState.isWebViewOpened) {
        PrivacyPolicyWebView(url = PRIVACY_POLICY_URL, onClose = webViewClose)
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
