package org.sopt.dateroad.presentation.ui.login.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.sopt.dateroad.presentation.ui.login.LoginRoute

fun NavGraphBuilder.loginGraph(onLogin: () -> Unit) {
    composable(route = LoginRoute.ROUTE) {
        LoginRoute(onLogin = onLogin)
    }
}

object LoginRoute {
    const val ROUTE = "login"
}