package org.sopt.teamdateroad.presentation.ui.signin.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.sopt.teamdateroad.presentation.ui.signin.SignInRoute

fun NavController.navigationSignIn() {
    navigate(
        route = SignInRoute.ROUTE
    ) {
        popUpTo(graph.id) {
            inclusive = true
        }
    }
}

fun NavGraphBuilder.signInGraph(
    navigateToOnboarding: () -> Unit,
    navigateToHome: () -> Unit
) {
    composable(route = SignInRoute.ROUTE) {
        SignInRoute(
            navigateToOnboarding = navigateToOnboarding,
            navigateToHome = navigateToHome
        )
    }
}

object SignInRoute {
    const val ROUTE = "signIn"
}
