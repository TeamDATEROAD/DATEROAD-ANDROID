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
            popBackStack = popBackStack
        )
    }
}

object TimelineRoutes {
    private const val BASE_ROUTE = "timeline"
    const val ARGUMENT_DATE_ID = "dateId"
    const val ARGUMENT_DATE_TYPE = "dateType"
    const val DETAIL_ROUTE = "$BASE_ROUTE/{$ARGUMENT_DATE_TYPE}/{$ARGUMENT_DATE_ID}"

    fun createRoute(dateType: DateType, dateId: Int) = "$BASE_ROUTE/${dateType.name}/$dateId"
}
