package org.sopt.dateroad.presentation.ui.signin.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.sopt.dateroad.presentation.ui.signin.SignInRoute

fun NavController.navigationSignIn() {
    navigate(
        route = SignInRoute.ROUTE
    )
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
