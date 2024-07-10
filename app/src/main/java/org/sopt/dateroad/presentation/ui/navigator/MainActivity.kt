package org.sopt.dateroad.presentation.ui.navigator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.dateroad.presentation.ui.profile.ProfileRoute
import org.sopt.dateroad.ui.theme.DATEROADTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navigator: MainNavigator = rememberMainNavigator()

            DATEROADTheme {
                MainScreen(
                    navigator = navigator
                )
            }
        }
    }
}
