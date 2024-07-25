package org.sopt.dateroad.presentation.ui.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.sopt.dateroad.presentation.type.ProfileType
import org.sopt.dateroad.presentation.ui.profile.ProfileRoute

fun NavController.navigationEnrollProfile() {
    navigate(
        route = EnrollProfileRoute.ROUTE
    )
}

fun NavController.navigationEditProfile() {
    navigate(
        route = EditProfileRoute.ROUTE
    )
}

fun NavGraphBuilder.enrollProfileNavGraph(
    navigateToHome: () -> Unit,
    navigateToMyPage: () -> Unit,
    profileType: ProfileType
) {
    composable(route = EnrollProfileRoute.ROUTE) {
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
    composable(route = EditProfileRoute.ROUTE) {
        ProfileRoute(
            navigationToHome = navigateToHome,
            navigationToMyPage = navigateToMyPage,
            profileType = profileType
        )
    }
}

object EnrollProfileRoute {
    const val ROUTE = "enrollProfile"
}

object EditProfileRoute {
    const val ROUTE = "editProfile"
}
