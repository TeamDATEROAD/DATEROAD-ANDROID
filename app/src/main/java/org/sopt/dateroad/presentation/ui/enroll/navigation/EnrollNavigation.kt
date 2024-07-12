package org.sopt.dateroad.presentation.ui.enroll.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.sopt.dateroad.presentation.type.EnrollType
import org.sopt.dateroad.presentation.ui.enroll.EnrollRoute

fun NavController.navigationToEnroll(enrollType: EnrollType) {
    navigate(
        route = EnrollRoute.route(enrollType = enrollType)
    )
}

fun NavGraphBuilder.enrollNavGraph(
    padding: PaddingValues,
    popBackStack: () -> Unit
) {
    composable(
        route = EnrollRoute.ROUTE_WITH_ARGUMENT,
        arguments = listOf(
            navArgument(EnrollRoute.ARGUMENT) {
                type = NavType.StringType
            }
        )
    ) { backStackEntry ->
        val enrollType = backStackEntry.arguments?.getString(EnrollRoute.ARGUMENT)?.let {
            EnrollType.valueOf(it)
        } ?: EnrollType.COURSE
        EnrollRoute(padding = padding, popBackStack = popBackStack, enrollType = enrollType)
    }
}

object EnrollRoute {
    const val ROUTE = "enroll"
    const val ARGUMENT = "enrollType"
    const val ROUTE_WITH_ARGUMENT = "$ROUTE/{$ARGUMENT}"
    fun route(enrollType: EnrollType) = "$ROUTE/${enrollType.name}"
}
