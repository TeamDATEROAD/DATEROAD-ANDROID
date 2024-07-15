package org.sopt.dateroad.presentation.ui.read.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.sopt.dateroad.presentation.model.MainNavigationBarRoute
import org.sopt.dateroad.presentation.type.CourseDetailType
import org.sopt.dateroad.presentation.type.EnrollType
import org.sopt.dateroad.presentation.ui.read.ReadRoute

fun NavController.navigationRead(navOptions: NavOptions) {
    navigate(
        route = MainNavigationBarRoute.Read::class.simpleName.orEmpty(),
        navOptions = navOptions
    )
}

fun NavGraphBuilder.readNavGraph(
    padding: PaddingValues,
    navigateToEnroll: (EnrollType, Int?) -> Unit,
    navigateToCourseDetail: (CourseDetailType, Int) -> Unit
) {
    composable(route = MainNavigationBarRoute.Read::class.simpleName.orEmpty()) {
        ReadRoute(
            padding = padding,
            navigateToEnroll = navigateToEnroll,
            navigateToCourseDetail = navigateToCourseDetail
        )
    }
}
