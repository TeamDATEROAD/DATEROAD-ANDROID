package org.sopt.dateroad.presentation.ui.signin.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.sopt.dateroad.presentation.ui.profile.navigation.ProfileRoute
import org.sopt.dateroad.presentation.ui.signin.SignInContract
import org.sopt.dateroad.presentation.ui.signin.SignInRoute
import org.sopt.dateroad.presentation.ui.signin.SignInViewModel

fun NavController.navigationSignIn() {
    navigate(
        route = SignInRoute.ROUTE
    )
}

fun NavGraphBuilder.signInGraph() {
    composable(route = SignInRoute.ROUTE) {
        SignInRoute()
    }
}

object SignInRoute {
    const val ROUTE = "signIn"
}
