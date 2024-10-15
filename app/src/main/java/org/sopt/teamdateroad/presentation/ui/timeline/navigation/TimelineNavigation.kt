package org.sopt.teamdateroad.presentation.ui.timeline.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.sopt.teamdateroad.presentation.model.MainNavigationBarRoute
import org.sopt.teamdateroad.presentation.type.EnrollType
import org.sopt.teamdateroad.presentation.type.TimelineType
import org.sopt.teamdateroad.presentation.ui.timeline.TimelineRoute

fun NavController.navigationTimeline(navOptions: NavOptions) {
    navigate(
        route = MainNavigationBarRoute.Timeline::class.simpleName.orEmpty(),
        navOptions = navOptions
    )
}

fun NavGraphBuilder.timelineNavGraph(
    padding: PaddingValues,
    navigateToPast: () -> Unit,
    navigateToEnroll: (EnrollType, String, Int?) -> Unit,
    navigateToTimelineDetail: (TimelineType, Int) -> Unit
) {
    composable(route = MainNavigationBarRoute.Timeline::class.simpleName.orEmpty()) {
        TimelineRoute(
            padding = padding,
            navigateToPast = navigateToPast,
            navigateToEnroll = navigateToEnroll,
            navigateToTimelineDetail = navigateToTimelineDetail
        )
    }
}
