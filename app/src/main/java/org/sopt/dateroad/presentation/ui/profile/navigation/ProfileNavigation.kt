package org.sopt.dateroad.presentation.ui.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.sopt.dateroad.presentation.ui.profile.ProfileRoute

fun NavController.navigationProfile() {
    navigate(
        route = ProfileRoute.ROUTE
    )
}

fun NavGraphBuilder.profileNavGraph() {
    composable(route = ProfileRoute.ROUTE) {
        ProfileRoute()
    }
}

object ProfileRoute {
    const val ROUTE = "profile"
}
