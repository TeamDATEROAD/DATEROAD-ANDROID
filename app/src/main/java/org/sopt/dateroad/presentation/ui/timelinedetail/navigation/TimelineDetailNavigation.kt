package org.sopt.dateroad.presentation.ui.timelinedetail.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.sopt.dateroad.presentation.type.EnrollType
import org.sopt.dateroad.presentation.type.TimelineBackgroundType
import org.sopt.dateroad.presentation.ui.timelinedetail.TimelineDetailRoute

fun NavController.navigateToTimelineDetail(timelineBackgroundType: TimelineBackgroundType, dateId: Int, navOptions: NavOptions? = null) {
    navigate(TimelineDetailRoutes.route(timelineBackgroundType, dateId), navOptions)
}

fun NavGraphBuilder.timelineDetailGraph(
    padding: PaddingValues,
    popBackStack: () -> Unit,
    navigateToEnroll: (EnrollType, Int) -> Unit
) {
    composable(
        route = TimelineDetailRoutes.ROUTE_WITH_ARGUMENT,
        arguments = listOf(
            navArgument(TimelineDetailRoutes.TIMELINE_BACKGROUND_TYPE) { type = NavType.StringType },
            navArgument(TimelineDetailRoutes.DATE_ID) { type = NavType.IntType }
        )
    ) { backStackEntry ->
        val timelineBackgroundType = TimelineBackgroundType.valueOf(backStackEntry.arguments?.getString(TimelineDetailRoutes.TIMELINE_BACKGROUND_TYPE) ?: TimelineBackgroundType.PINK.name)
        val dateId = backStackEntry.arguments?.getInt(TimelineDetailRoutes.DATE_ID) ?: 1
        TimelineDetailRoute(
            popBackStack = popBackStack,
            dateId = dateId,
            timelineBackgroundType = timelineBackgroundType,
            navigateToEnroll = navigateToEnroll
        )
    }
}

object TimelineDetailRoutes {
    private const val ROUTE = "timeline_detail"
    const val TIMELINE_BACKGROUND_TYPE = "timelineBackgroundType"
    const val DATE_ID = "dateId"
    const val ROUTE_WITH_ARGUMENT = "$ROUTE/{$TIMELINE_BACKGROUND_TYPE}/{$DATE_ID}"

    fun route(timelineBackgroundType: TimelineBackgroundType, dateId: Int) = "$ROUTE/${timelineBackgroundType.name}/$dateId"
}
