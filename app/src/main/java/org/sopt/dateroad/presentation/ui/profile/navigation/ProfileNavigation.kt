package org.sopt.dateroad.presentation.ui.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.sopt.dateroad.presentation.type.ProfileType
import org.sopt.dateroad.presentation.ui.profile.ProfileRoute

fun NavController.navigationProfile() {
    navigate(
        route = ProfileRoute.ROUTE
    )
}

fun NavGraphBuilder.enrollProfileNavGraph(
    navigateToHome: () -> Unit,
    navigateToMyPage: () -> Unit,
    profileType: ProfileType
) {
    composable(route = ProfileRoute.ROUTE) {
        ProfileRoute(
            navigationToHome = navigateToHome,
            navigationToMyPage = navigateToMyPage,
            profileType = profileType
        )
    }
}

fun NavGraphBuilder.editProfileNavGraph(
    navigateToHome: () -> Unit,
    navigateToMyPage: () -> Unit,
    profileType: ProfileType
) {
    composable(route = ProfileRoute.ROUTE) {
        ProfileRoute(
            navigationToHome = navigateToHome,
            navigationToMyPage = navigateToMyPage,
            profileType = profileType
        )
    }
}

object ProfileRoute {
    const val ROUTE = "profile"
}
