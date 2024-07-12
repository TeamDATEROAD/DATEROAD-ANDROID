package org.sopt.dateroad.presentation.ui.timelinedetail.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.sopt.dateroad.presentation.type.DateType
import org.sopt.dateroad.presentation.ui.timelinedetail.TimelineDetailRoute

fun NavController.navigateToTimelineDetail(dateType: DateType, dateId: Int, navOptions: NavOptions? = null) {
    navigate(TimelineDetailRoutes.route(dateType, dateId), navOptions)
}

fun NavGraphBuilder.timelineDetailGraph(
    padding: PaddingValues,
    popBackStack: () -> Unit
) {
    composable(
        route = TimelineDetailRoutes.ROUTE_WITH_ARGUMENT,
        arguments = listOf(
            navArgument(TimelineDetailRoutes.ARGUMENT_TYPE) { type = NavType.StringType },
            navArgument(TimelineDetailRoutes.ARGUMENT_ID) { type = NavType.IntType }
        )
    ) { backStackEntry ->
        val dateType = DateType.valueOf(backStackEntry.arguments?.getString(TimelineDetailRoutes.ARGUMENT_TYPE) ?: DateType.PINK.name)
        val dateId = backStackEntry.arguments?.getInt(TimelineDetailRoutes.ARGUMENT_ID) ?: 1
        TimelineDetailRoute(
            padding = padding,
            popBackStack = popBackStack,
            dateId = dateId,
            dateType = dateType
        )
    }
}

object TimelineDetailRoutes {
    private const val ROUTE = "timeline_detail"
    const val ARGUMENT_TYPE = "dateType"
    const val ARGUMENT_ID = "dateId"
    const val ROUTE_WITH_ARGUMENT = "$ROUTE/{$ARGUMENT_TYPE}/{$ARGUMENT_ID}"
    fun route(dateType: DateType, dateId: Int) = "$ROUTE/${dateType.name}/$dateId"
}
