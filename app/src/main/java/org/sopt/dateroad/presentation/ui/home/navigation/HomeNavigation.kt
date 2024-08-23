package org.sopt.dateroad.presentation.ui.home.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.sopt.dateroad.presentation.model.MainNavigationBarRoute
import org.sopt.dateroad.presentation.type.EnrollType
import org.sopt.dateroad.presentation.ui.home.HomeRoute

fun NavController.navigationHome(navOptions: NavOptions) {
    navigate(
        route = MainNavigationBarRoute.Home::class.simpleName.orEmpty(),
        navOptions = navOptions
    )
}

fun NavGraphBuilder.homeNavGraph(
    padding: PaddingValues,
    navigateToPointHistory: () -> Unit,
    navigateToLook: () -> Unit,
    navigateToTimeline: () -> Unit,
    navigateToEnroll: (EnrollType, Int?) -> Unit,
    navigateToAdvertisement: (Int) -> Unit,
    navigateToCourseDetail: (Int) -> Unit
) {
    composable(route = MainNavigationBarRoute.Home::class.simpleName.orEmpty()) {
        HomeRoute(
            padding = padding,
            navigateToPointHistory = navigateToPointHistory,
            navigateToLook = navigateToLook,
            navigateToTimeline = navigateToTimeline,
            navigateToEnroll = navigateToEnroll,
            navigateToAdvertisementDetail = navigateToAdvertisement,
            navigateToCourseDetail = navigateToCourseDetail
        )
    }
}
