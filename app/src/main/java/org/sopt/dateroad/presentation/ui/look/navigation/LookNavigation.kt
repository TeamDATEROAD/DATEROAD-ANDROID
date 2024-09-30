package org.sopt.dateroad.presentation.ui.look.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.sopt.dateroad.presentation.model.MainNavigationBarRoute
import org.sopt.dateroad.presentation.type.EnrollType
import org.sopt.dateroad.presentation.ui.look.LookRoute

fun NavController.navigationLook(navOptions: NavOptions) {
    navigate(
        route = MainNavigationBarRoute.Look::class.simpleName.orEmpty(),
        navOptions = navOptions
    )
}

fun NavGraphBuilder.lookNavGraph(
    padding: PaddingValues,
    navigateToEnroll: (EnrollType, String, Int?) -> Unit,
    navigateToCourseDetail: (Int) -> Unit
) {
    composable(route = MainNavigationBarRoute.Look::class.simpleName.orEmpty()) {
        LookRoute(padding = padding, navigateToEnroll = navigateToEnroll, navigateToCourseDetail = navigateToCourseDetail)
    }
}
