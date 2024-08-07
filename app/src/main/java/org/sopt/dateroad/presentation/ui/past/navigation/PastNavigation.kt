package org.sopt.dateroad.presentation.ui.past.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.sopt.dateroad.presentation.type.TimelineBackgroundType
import org.sopt.dateroad.presentation.ui.past.PastRoute

fun NavController.navigationPast() {
    navigate(
        route = PastRoute.ROUTE
    )
}

fun NavGraphBuilder.pastNavGraph(
    padding: PaddingValues,
    popBackStack: () -> Unit,
    navigateToTimelineDetail: (TimelineBackgroundType, Int) -> Unit
) {
    composable(route = PastRoute.ROUTE) {
        PastRoute(
            padding = padding,
            popBackStack = popBackStack,
            navigateToTimelineDetail = navigateToTimelineDetail
        )
    }
}

object PastRoute {
    const val ROUTE = "past"
}
