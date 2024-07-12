package org.sopt.dateroad.presentation.ui.login

import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState
import org.sopt.dateroad.R
import org.sopt.dateroad.presentation.ui.component.button.DateRoadKakaoLoginButton
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun LoginRoute(onLogin: () -> Unit) {
    LoginScreen(onLogin = onLogin)
}

@Composable
fun LoginScreen(onLogin: () -> Unit) {
    var showWebView by remember { mutableStateOf(false) }

    if (showWebView) {
        PrivacyPolicyWebView {
            showWebView = false
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
            DateRoadKakaoLoginButton(modifier = Modifier.padding(horizontal = 30.dp),
                onClick = {
                    onLogin()
                })
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "개인정보처리방침",
                color = DateRoadTheme.colors.gray200,
                style = DateRoadTheme.typography.bodyMed15,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable {
                    showWebView = true
                }
            )
            Spacer(modifier = Modifier.height(37.dp))
        }
    }
}

@Composable
fun PrivacyPolicyWebView(onClose: () -> Unit) {
    val webViewState = rememberWebViewState(url = "http://mammoth-cheese-88e.notion.site")
    var webView: android.webkit.WebView? by remember { mutableStateOf(null) }

    BackHandler(enabled = true) {
        if (webView?.canGoBack() == true) {
            webView?.goBack()
        } else {
            onClose()
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        WebView(
            state = webViewState,
            modifier = Modifier.weight(1f),
            onCreated = { webViewInstance ->
                with(webViewInstance) {
                    settings.run {
                        javaScriptEnabled = true
                        domStorageEnabled = true
                        javaScriptCanOpenWindowsAutomatically = false
                    }
                    webViewClient = object : WebViewClient() {
                        override fun onPageFinished(view: android.webkit.WebView?, url: String?) {
                            super.onPageFinished(view, url)
                        }
                    }
                }
                webView = webViewInstance
            }
        )
    }
}

@Preview
@Composable
fun LoginScreenPreview(modifier: Modifier = Modifier) {
    LoginScreen(onLogin = {})
}
