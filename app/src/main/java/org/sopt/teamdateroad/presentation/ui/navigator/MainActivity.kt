package org.sopt.teamdateroad.presentation.ui.navigator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import org.sopt.teamdateroad.presentation.ui.splash.SplashScreen
import org.sopt.teamdateroad.ui.theme.DATEROADTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().setOnExitAnimationListener { splashScreenView ->
            splashScreenView.remove()
        }
        setContent {
            val navigator: MainNavigator = rememberMainNavigator()
            var showSplash by remember { mutableStateOf(true) }

            DATEROADTheme {
                LaunchedEffect(Unit) {
                    delay(SPLASH_SCREEN_DELAY)
                    showSplash = false
                }
                if (showSplash) {
                    SplashScreen()
                } else {
                    MainScreen(navigator = navigator)
                }
            }
        }
    }

    companion object {
        const val SPLASH_SCREEN_DELAY = 2000L
    }
}
