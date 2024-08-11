package org.sopt.dateroad.presentation.ui.timelinedetail.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.sopt.dateroad.presentation.type.EnrollType
import org.sopt.dateroad.presentation.type.TimelineType
import org.sopt.dateroad.presentation.ui.timelinedetail.TimelineDetailRoute

fun NavController.navigateToTimelineDetail(timelineType: TimelineType, dateId: Int, navOptions: NavOptions? = null) {
    navigate(TimelineDetailRoutes.route(timelineType, dateId), navOptions)
}

fun NavGraphBuilder.timelineDetailGraph(
    padding: PaddingValues,
    popBackStack: () -> Unit,
    navigateToEnroll: (EnrollType, Int) -> Unit
) {
    composable(
        route = TimelineDetailRoutes.ROUTE_WITH_ARGUMENT,
        arguments = listOf(
            navArgument(TimelineDetailRoutes.TIMELINE_TYPE) { type = NavType.StringType },
            navArgument(TimelineDetailRoutes.DATE_ID) { type = NavType.IntType }
        )
    ) { backStackEntry ->
        val timelineType = TimelineType.valueOf(backStackEntry.arguments?.getString(TimelineDetailRoutes.TIMELINE_TYPE) ?: TimelineType.PINK.name)
        val dateId = backStackEntry.arguments?.getInt(TimelineDetailRoutes.DATE_ID) ?: 1
        TimelineDetailRoute(
            popBackStack = popBackStack,
            dateId = dateId,
            timelineType = timelineType,
            navigateToEnroll = navigateToEnroll
        )
    }
}

object TimelineDetailRoutes {
    private const val ROUTE = "timeline_detail"
    const val TIMELINE_TYPE = "timelineType"
    const val DATE_ID = "dateId"
    const val ROUTE_WITH_ARGUMENT = "$ROUTE/{$TIMELINE_TYPE}/{$DATE_ID}"

    fun route(timelineType: TimelineType, dateId: Int) = "$ROUTE/${timelineType.name}/$dateId"
}
