package org.sopt.dateroad.presentation.ui.navigator.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import org.sopt.dateroad.presentation.ui.dummy.navigation.dummyNavGraph
import org.sopt.dateroad.presentation.ui.navigator.MainNavigator

@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    navigator: MainNavigator,
    padding: PaddingValues
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceDim)
    ) {
        NavHost(
            navController = navigator.navHostController,
            startDestination = navigator.startDestination::class.simpleName.orEmpty()
        ) {
            dummyNavGraph(
                padding = padding
            )
        }
    }
}