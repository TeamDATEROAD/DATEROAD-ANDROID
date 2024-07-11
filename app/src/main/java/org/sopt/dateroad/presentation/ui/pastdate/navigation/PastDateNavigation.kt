package org.sopt.dateroad.presentation.ui.read.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import org.sopt.dateroad.presentation.model.MainNavigationBarRoute

fun NavController.navigationPastDate(navOptions: NavOptions) {
    navigate(
        route = MainNavigationBarRoute.Read::class.simpleName.orEmpty(),
        navOptions = navOptions
    )
}

fun NavGraphBuilder.pastDateNavGraph(
    padding: PaddingValues
) {
//    composable(route = MainNavigationBarRoute.PastDate::class.simpleName.orEmpty()) {
//        PastDateRoute(padding = padding)
//    }
}
