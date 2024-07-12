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

fun NavController.navigateToTimelineDetail(dateType: DateType, navOptions: NavOptions? = null) {
    navigate("timeline_detail/${dateType.name}", navOptions)
}

fun NavGraphBuilder.timelineDetailGraph(
    padding: PaddingValues,
    popBackStack: () -> Unit
) {
    composable(
        route = "timeline_detail/{dateType}",
        arguments = listOf(navArgument("dateType") { type = NavType.StringType })
    ) { backStackEntry ->
        val dateType = DateType.valueOf(backStackEntry.arguments?.getString("dateType") ?: DateType.PINK.name)
        TimelineDetailRoute(
            padding = padding,
            popBackStack = popBackStack,
            dateId = 1,
            dateType = dateType
        )
    }
}
