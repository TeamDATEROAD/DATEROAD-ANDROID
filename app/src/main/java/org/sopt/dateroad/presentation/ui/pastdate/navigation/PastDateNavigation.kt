package org.sopt.dateroad.presentation.ui.pastdate.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.sopt.dateroad.presentation.ui.read.PastDateRoute

fun NavController.navigateToPastDate(navOptions: NavOptions? = null) {
    this.navigate("past_date", navOptions)
}

fun NavGraphBuilder.pastDateNavGraph(
    padding: PaddingValues
) {
    composable(route = "past_date") {
        PastDateRoute(padding = padding)
    }
}
