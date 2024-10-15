package org.sopt.teamdateroad.presentation.ui.pointhistory.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.sopt.teamdateroad.presentation.ui.pointhistory.PointHistoryRoute

fun NavController.navigationPointHistory() {
    navigate(
        route = PointHistoryRoute.ROUTE
    )
}

fun NavGraphBuilder.pointHistoryGraph(
    padding: PaddingValues,
    popBackStack: () -> Unit
) {
    composable(route = PointHistoryRoute.ROUTE) {
        PointHistoryRoute(padding = padding, popBackStack = popBackStack)
    }
}

object PointHistoryRoute {
    const val ROUTE = "pointHistory"
}
