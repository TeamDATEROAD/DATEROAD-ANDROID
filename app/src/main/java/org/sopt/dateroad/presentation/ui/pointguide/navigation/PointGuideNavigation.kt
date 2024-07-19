package org.sopt.dateroad.presentation.ui.pointguide.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.sopt.dateroad.presentation.ui.pointguide.ProfileGuideRoute

fun NavController.navigationPointGuide() {
    navigate(
        route = PointGuideRoute.ROUTE
    )
}

fun NavGraphBuilder.pointGuideGraph(
    padding: PaddingValues,
    popBackStack: () -> Unit
) {
    composable(route = PointGuideRoute.ROUTE) {
        ProfileGuideRoute(
            padding = padding,
            popBackStack = popBackStack
        )
    }
}

object PointGuideRoute {
    const val ROUTE = "pointGuide"
}
