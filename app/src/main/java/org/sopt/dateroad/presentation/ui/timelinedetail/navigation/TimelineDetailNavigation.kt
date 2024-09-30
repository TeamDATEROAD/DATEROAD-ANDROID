package org.sopt.dateroad.presentation.ui.timelinedetail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.sopt.dateroad.presentation.type.TimelineType
import org.sopt.dateroad.presentation.ui.timelinedetail.TimelineDetailRoute

fun NavController.navigateToTimelineDetail(timelineType: TimelineType, timelineId: Int, navOptions: NavOptions? = null) {
    navigate(TimelineDetailRoutes.route(timelineType, timelineId), navOptions)
}

fun NavGraphBuilder.timelineDetailGraph(
    viewPath: String,
    popBackStack: () -> Unit
) {
    composable(
        route = TimelineDetailRoutes.ROUTE_WITH_ARGUMENT,
        arguments = listOf(
            navArgument(TimelineDetailRoutes.TIMELINE_TYPE) { type = NavType.StringType },
            navArgument(TimelineDetailRoutes.TIMELINE_ID) { type = NavType.IntType }
        )
    ) { backStackEntry ->
        val timelineType = TimelineType.valueOf(backStackEntry.arguments?.getString(TimelineDetailRoutes.TIMELINE_TYPE) ?: TimelineType.PINK.name)
        val timelineId = backStackEntry.arguments?.getInt(TimelineDetailRoutes.TIMELINE_ID) ?: 1

        TimelineDetailRoute(
            popBackStack = popBackStack,
            timelineId = timelineId,
            timelineType = timelineType,
            previousView = viewPath
        )
    }
}

object TimelineDetailRoutes {
    private const val ROUTE = "timeline_detail"
    const val TIMELINE_TYPE = "timelineType"
    const val TIMELINE_ID = "timelineId"
    const val ROUTE_WITH_ARGUMENT = "$ROUTE/{$TIMELINE_TYPE}/{$TIMELINE_ID}"

    fun route(timelineType: TimelineType, timelineId: Int) = "$ROUTE/${timelineType.name}/$timelineId"
}
