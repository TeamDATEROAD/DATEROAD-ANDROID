package org.sopt.dateroad.presentation.ui.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.sopt.dateroad.R
import org.sopt.dateroad.presentation.ui.component.button.DateRoadKakaoLoginButton
import org.sopt.dateroad.presentation.ui.component.webview.PrivacyPolicyWebView
import org.sopt.dateroad.presentation.util.CourseDetail.PRIVACY_POLICY_URL
import org.sopt.dateroad.presentation.util.modifier.noRippleClickable
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun SignInRoute(
    viewModel: SignInViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SignInScreen(
        signInUiState = uiState,
        onSignIn = { viewModel.setEvent(SignInContract.SignInEvent.PostSignIn) },
        webViewClicked = { viewModel.setEvent(SignInContract.SignInEvent.WebViewClick) },
        webViewClose = { viewModel.setEvent(SignInContract.SignInEvent.WebViewClose) }
    )
}

@Composable
fun SignInScreen(
    signInUiState: SignInContract.SignInUiState = SignInContract.SignInUiState(),
    onSignIn: () -> Unit,
    webViewClicked: () -> Unit,
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
                onClick = {
                    onSignIn()
                }
            )
            Spacer(modifier = Modifier.weight(16f))
            Text(
                text = "개인정보처리방침",
                color = DateRoadTheme.colors.gray200,
                style = DateRoadTheme.typography.bodyMed15,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.noRippleClickable {
                    webViewClicked()
                }
            )
            Spacer(modifier = Modifier.weight(37f))
        }
    }
}
