package org.sopt.teamdateroad.presentation.ui.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.sopt.teamdateroad.presentation.type.ProfileType
import org.sopt.teamdateroad.presentation.ui.profile.ProfileRoute
import org.sopt.teamdateroad.presentation.ui.profile.navigation.ProfileRoute.ROUTE_WITH_ARGUMENT

fun NavController.navigationProfile(profileType: ProfileType) {
    navigate(
        route = ProfileRoute.route(profileType = profileType)
    )
}

fun NavGraphBuilder.profileNavGraph(
    navigateToHome: () -> Unit,
    navigateToMyPage: () -> Unit,
    popBackStack: () -> Unit

) {
    composable(
        route = ROUTE_WITH_ARGUMENT,
        arguments = listOf(
            navArgument(ProfileRoute.ARGUMENT) {
                type = NavType.StringType
            }
        )
    ) { backStackEntry ->
        val profileType = backStackEntry.arguments?.getString(ProfileRoute.ARGUMENT)?.let {
            ProfileType.valueOf(it)
        } ?: ProfileType.EDIT

        ProfileRoute(
            navigationToHome = navigateToHome,
            navigationToMyPage = navigateToMyPage,
            profileType = profileType,
            popBackStack = popBackStack

        )
    }
}

object ProfileRoute {
    const val ROUTE = "Profile"
    const val ARGUMENT = "profileType"
    const val ROUTE_WITH_ARGUMENT = "$ROUTE/{$ARGUMENT}"

    fun route(profileType: ProfileType) = "$ROUTE/${profileType.name}"
}
