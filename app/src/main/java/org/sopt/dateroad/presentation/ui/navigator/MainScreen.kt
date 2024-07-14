package org.sopt.dateroad.presentation.ui.navigator

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.dateroad.presentation.type.MainNavigationBarItemType
import org.sopt.dateroad.presentation.ui.navigator.component.MainBottomBar
import org.sopt.dateroad.presentation.ui.navigator.component.MainNavHost
import org.sopt.dateroad.presentation.ui.signin.navigation.navigationSignIn
import org.sopt.dateroad.ui.theme.DATEROADTheme

@Composable
fun MainScreen(
    navigator: MainNavigator = rememberMainNavigator()
) {
    MainScreenContent(
        navigator = navigator
    )
}

@Composable
private fun MainScreenContent(
    modifier: Modifier = Modifier,
    navigator: MainNavigator
) {

    Scaffold(
        modifier = modifier,
        content = { padding ->
            MainNavHost(
                navigator = navigator,
                padding = padding
            )
        },
        bottomBar = {
            MainBottomBar(
                isVisible = navigator.showBottomBar(),
                navigationBarItems = MainNavigationBarItemType.entries.toList(),
                currentNavigationBarItem = navigator.currentMainNavigationBarItem,
                onNavigationBarItemSelected = { navigator.navigateMainNavigation(it) }
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    DATEROADTheme {
        MainScreen()
    }
}
