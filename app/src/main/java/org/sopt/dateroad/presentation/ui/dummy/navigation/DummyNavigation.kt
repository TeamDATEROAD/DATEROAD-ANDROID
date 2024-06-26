package org.sopt.dateroad.presentation.ui.dummy.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.sopt.dateroad.presentation.model.MainNavigationBarRoute
import org.sopt.dateroad.presentation.ui.dummy.DummyRoute

fun NavController.navigationDummy(navOptions: NavOptions) {
    navigate(
        route = MainNavigationBarRoute.Dummy::class.simpleName.orEmpty(),
        navOptions = navOptions
    )
}

fun NavGraphBuilder.dummyNavGraph(
    padding: PaddingValues
) {
    composable(route = MainNavigationBarRoute.Dummy::class.simpleName.orEmpty()) {
        DummyRoute(padding = padding)
    }
}