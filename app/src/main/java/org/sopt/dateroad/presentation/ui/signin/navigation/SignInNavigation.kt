package org.sopt.dateroad.presentation.ui.signin.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.sopt.dateroad.presentation.ui.signin.SignInRoute
import org.sopt.dateroad.presentation.ui.signin.SignInViewModel

fun NavGraphBuilder.signInGraph(onSignIn: () -> Unit) {
    composable(route = SignInRoute.ROUTE) {
        SignInRoute(
            onSignIn = onSignIn,
            viewModel = SignInViewModel()
        )
    }
}

object SignInRoute {
    const val ROUTE = "signIn"
}
