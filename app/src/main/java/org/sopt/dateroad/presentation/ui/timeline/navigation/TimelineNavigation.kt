package org.sopt.dateroad.presentation.ui.timeline.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import kotlin.reflect.KFunction1
import org.sopt.dateroad.presentation.model.MainNavigationBarRoute
import org.sopt.dateroad.presentation.type.DateType
import org.sopt.dateroad.presentation.type.EnrollType
import org.sopt.dateroad.presentation.ui.timeline.TimelineRoute
import org.sopt.dateroad.presentation.ui.timelinedetail.TimelineDetailRoute

fun NavController.navigationTimeline(navOptions: NavOptions) {
    navigate(
        route = MainNavigationBarRoute.Timeline::class.simpleName.orEmpty(),
        navOptions = navOptions
    )
}

fun NavGraphBuilder.timelineNavGraph(
    padding: PaddingValues,
    navigateToTimelineDetail: (DateType, Int) -> Unit,
    navigateToEnroll: KFunction1<EnrollType, Unit>,
    popBackStack: () -> Unit
) {
    composable(route = MainNavigationBarRoute.Timeline::class.simpleName.orEmpty()) {
        TimelineRoute(
            padding = padding,
            navigateToTimelineDetail = navigateToTimelineDetail,
            navigateToEnroll = navigateToEnroll
        )
    }

    composable(
        route = TimelineRoutes.PINK_ROUTE,
        arguments = listOf(navArgument(TimelineRoutes.ARGUMENT_DATE_ID) { type = NavType.IntType })
    ) { backStackEntry ->
        val dateId = backStackEntry.arguments?.getInt(TimelineRoutes.ARGUMENT_DATE_ID) ?: return@composable
        TimelineDetailRoute(
            padding = padding,
            dateId = dateId,
            dateType = DateType.PINK,
            popBackStack = popBackStack
        )
    }

    composable(
        route = TimelineRoutes.PURPLE_ROUTE,
        arguments = listOf(navArgument(TimelineRoutes.ARGUMENT_DATE_ID) { type = NavType.IntType })
    ) { backStackEntry ->
        val dateId = backStackEntry.arguments?.getInt(TimelineRoutes.ARGUMENT_DATE_ID) ?: return@composable
        TimelineDetailRoute(
            padding = padding,
            dateId = dateId,
            dateType = DateType.PURPLE,
            popBackStack = popBackStack
        )
    }

    composable(
        route = TimelineRoutes.LIME_ROUTE,
        arguments = listOf(navArgument(TimelineRoutes.ARGUMENT_DATE_ID) { type = NavType.IntType })
    ) { backStackEntry ->
        val dateId = backStackEntry.arguments?.getInt(TimelineRoutes.ARGUMENT_DATE_ID) ?: return@composable
        TimelineDetailRoute(
            padding = padding,
            dateId = dateId,
            dateType = DateType.LIME,
            popBackStack = popBackStack
        )
    }
}

object TimelineRoutes {
    private const val BASE_ROUTE = "timeline"
    const val ARGUMENT_DATE_ID = "dateId"
    const val PINK_ROUTE = "$BASE_ROUTE/PINK/{$ARGUMENT_DATE_ID}"
    const val PURPLE_ROUTE = "$BASE_ROUTE/PURPLE/{$ARGUMENT_DATE_ID}"
    const val LIME_ROUTE = "$BASE_ROUTE/LIME/{$ARGUMENT_DATE_ID}"
}
