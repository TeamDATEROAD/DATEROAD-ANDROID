package org.sopt.dateroad.presentation.ui.login.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.sopt.dateroad.presentation.ui.login.LoginRoute
import org.sopt.dateroad.presentation.ui.login.LoginViewModel

fun NavGraphBuilder.loginGraph(onLogin: () -> Unit) {
    composable(route = LoginRoute.ROUTE) {
        LoginRoute(
            onLogin = onLogin,
            viewModel = LoginViewModel()
        )
    }
}

object LoginRoute {
    const val ROUTE = "login"
}
