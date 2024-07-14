package org.sopt.dateroad.presentation.ui.onboarding.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.sopt.dateroad.presentation.ui.onboarding.OnboardingRoute

fun NavController.navigationToOnboarding() {
    navigate(
        route = OnboardingRoute.ROUTE
    )
}

fun NavGraphBuilder.onboardingNavGraph() {
    composable(route = OnboardingRoute.ROUTE) {
        OnboardingRoute()
    }
}

object OnboardingRoute {
    const val ROUTE = "onboarding"
}
