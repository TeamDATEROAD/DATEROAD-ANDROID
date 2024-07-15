package org.sopt.dateroad.presentation.ui.timeline.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.sopt.dateroad.presentation.model.MainNavigationBarRoute
import org.sopt.dateroad.presentation.type.EnrollType
import org.sopt.dateroad.presentation.ui.timeline.TimelineRoute

fun NavController.navigationTimeline(navOptions: NavOptions) {
    navigate(
        route = MainNavigationBarRoute.Timeline::class.simpleName.orEmpty(),
        navOptions = navOptions
    )
}

fun NavGraphBuilder.timelineNavGraph(
    padding: PaddingValues,
    navigateToPast: () -> Unit,
    navigateToEnroll: (EnrollType, Int?) -> Unit
    navigateToTimelineDetail: (DateType, Int) -> Unit,
    popBackStack: () -> Unit
) {
    composable(route = MainNavigationBarRoute.Timeline::class.simpleName.orEmpty()) {
        TimelineRoute(
            padding = padding,
            navigateToPast = navigateToPast,
            navigateToEnroll = navigateToEnroll
            navigateToTimelineDetail = navigateToTimelineDetail,
            popBackStack = popBackStack
        )
    }

    composable(
        route = TimelineRoutes.DETAIL_ROUTE,
        arguments = listOf(
            navArgument(TimelineRoutes.ARGUMENT_DATE_ID) { type = NavType.IntType },
            navArgument(TimelineRoutes.ARGUMENT_DATE_TYPE) { type = NavType.StringType }
        )
    ) { backStackEntry ->
        val dateId = backStackEntry.arguments?.getInt(TimelineRoutes.ARGUMENT_DATE_ID) ?: return@composable
        val dateTypeString = backStackEntry.arguments?.getString(TimelineRoutes.ARGUMENT_DATE_TYPE) ?: return@composable
        val dateType = DateType.valueOf(dateTypeString)

        TimelineDetailRoute(
            padding = padding,
            dateId = dateId,
            dateType = dateType,
            popBackStack = popBackStack,
            sourceScreen = true
        )
    }
}

object TimelineRoutes {
    private const val BASE_ROUTE = "timeline"
    const val ARGUMENT_DATE_ID = "dateId"
    const val ARGUMENT_DATE_TYPE = "dateType"
    const val DETAIL_ROUTE = "$BASE_ROUTE/{$ARGUMENT_DATE_TYPE}/{$ARGUMENT_DATE_ID}"
}
