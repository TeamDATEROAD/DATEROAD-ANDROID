package org.sopt.dateroad.presentation.ui.home.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.sopt.dateroad.presentation.model.MainNavigationBarRoute
import org.sopt.dateroad.presentation.ui.home.HomeRoute

fun NavController.navigationHome(navOptions: NavOptions) {
    navigate(
        route = MainNavigationBarRoute.Home::class.simpleName.orEmpty(),
        navOptions = navOptions
    )
}

fun NavGraphBuilder.homeNavGraph(
    padding: PaddingValues,
    navigateToPointHistory: () -> Unit
) {
    composable(route = MainNavigationBarRoute.Home::class.simpleName.orEmpty()) {
        HomeRoute(padding = padding, navigateToPointHistory = navigateToPointHistory)
    }
}
