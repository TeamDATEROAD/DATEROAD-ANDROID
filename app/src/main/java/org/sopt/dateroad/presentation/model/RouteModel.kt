package org.sopt.dateroad.presentation.model

import kotlinx.serialization.Serializable

sealed interface Route

sealed interface MainNavigationBarRoute : Route {
    data object Dummy : MainNavigationBarRoute
}