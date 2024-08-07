package org.sopt.dateroad.presentation.ui.timeline.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.sopt.dateroad.presentation.model.MainNavigationBarRoute
import org.sopt.dateroad.presentation.type.EnrollType
import org.sopt.dateroad.presentation.type.TimelineBackgroundType
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
    navigateToEnroll: (EnrollType, Int?) -> Unit,
    navigateToTimelineDetail: (TimelineBackgroundType, Int) -> Unit,
    popBackStack: () -> Unit
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
