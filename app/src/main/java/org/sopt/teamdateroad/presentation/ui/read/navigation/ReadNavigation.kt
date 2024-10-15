package org.sopt.teamdateroad.presentation.ui.read.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.sopt.teamdateroad.presentation.model.MainNavigationBarRoute
import org.sopt.teamdateroad.presentation.type.EnrollType
import org.sopt.teamdateroad.presentation.ui.read.ReadRoute

fun NavController.navigationRead(navOptions: NavOptions) {
    navigate(
        route = MainNavigationBarRoute.Read::class.simpleName.orEmpty(),
        navOptions = navOptions
    )
}

fun NavGraphBuilder.readNavGraph(
    padding: PaddingValues,
    navigateToEnroll: (EnrollType, String, Int?) -> Unit,
    navigateToCourseDetail: (Int) -> Unit
) {
    composable(route = MainNavigationBarRoute.Read::class.simpleName.orEmpty()) {
        ReadRoute(
            padding = padding,
            navigateToEnroll = navigateToEnroll,
            navigateToCourseDetail = navigateToCourseDetail
        )
    }
}
