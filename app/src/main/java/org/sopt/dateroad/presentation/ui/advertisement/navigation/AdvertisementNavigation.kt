package org.sopt.dateroad.presentation.ui.advertisement.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.sopt.dateroad.presentation.ui.advertisement.AdvertisementRoute

fun NavController.navigationAdvertisement(advertisementId: Int) {
    this.navigate(route = AdvertisementRoute.route(advertisementId = advertisementId))
}

fun NavGraphBuilder.advertisementGraph(
    popBackStack: () -> Unit
) {
    composable(
        route = AdvertisementRoute.ROUTE_WITH_ARGUMENT,
        arguments = listOf(
            navArgument(AdvertisementRoute.ID) {
                type = NavType.IntType
            }
        )
    ) { navBackStackEntry ->
        AdvertisementRoute(
            popBackStack = popBackStack,
            advertisementId = navBackStackEntry.arguments?.getInt(AdvertisementRoute.ID) ?: 0
        )
    }
}

object AdvertisementRoute {
    private const val ROUTE = "advertisement"
    const val ID = "id"
    const val ROUTE_WITH_ARGUMENT = "$ROUTE/{$ID}"
    fun route(advertisementId: Int) = "$ROUTE/$advertisementId"
}
