package org.sopt.dateroad.presentation.ui.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.R
import org.sopt.dateroad.presentation.ui.component.button.DateRoadKakaoLoginButton
import org.sopt.dateroad.presentation.ui.component.webview.PrivacyPolicyWebView
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun SignInRoute(
    viewModel: SignInViewModel,
    uiState: SignInContract.SignInUiState,
    onSignIn: () -> Unit
) {
    SignInScreen(
        signInUiState = SignInContract.SignInUiState(),
        onSignIn = onSignIn
    )
}

@Composable
fun SignInScreen(
    signInUiState: SignInContract.SignInUiState = SignInContract.SignInUiState(),
    onSignIn: () -> Unit
) {
    if (signInUiState.isWebViewOpened) {
        PrivacyPolicyWebView(url = "http://mammoth-cheese-88e.notion.site") {
            signInUiState.isWebViewOpened
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DateRoadTheme.colors.deepPurple),
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
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "개인정보처리방침",
                color = DateRoadTheme.colors.gray200,
                style = DateRoadTheme.typography.bodyMed15,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable {
                    signInUiState.isWebViewOpened = true
                }
            )
            Spacer(modifier = Modifier.height(37.dp))
        }
    }
}

@Preview
@Composable
fun SignInScreenPreview(modifier: Modifier = Modifier) {
    SignInScreen(onSignIn = {})
}
