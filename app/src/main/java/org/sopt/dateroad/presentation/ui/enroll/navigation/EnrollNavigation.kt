package org.sopt.dateroad.presentation.ui.enroll.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.sopt.dateroad.presentation.ui.enroll.EnrollRoute

fun NavController.navigationEnroll() {
    navigate(
        route = EnrollRoute.ROUTE
    )
}

fun NavGraphBuilder.enrollGraph(
    padding: PaddingValues,
    popBackStack: () -> Unit
) {
    composable(route = EnrollRoute.ROUTE) {
        EnrollRoute(padding = padding, popBackStack = popBackStack)
    }
}

object EnrollRoute {
    const val ROUTE = "enroll"
}
