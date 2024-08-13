package org.sopt.dateroad.presentation.ui.advertisement.navigation

import androidx.navigation.NavController

fun NavController.navigationAdvertisement(id: Int) {
    this.navigate(route = AdvertisementRoute.)
}

object  AdvertisementRoute {
    private const val ROUTE = "advertisement"

}