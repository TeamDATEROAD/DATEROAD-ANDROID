package org.sopt.dateroad.presentation.model

sealed interface Route

sealed interface MainNavigationBarRoute : Route {
    data object Dummy : MainNavigationBarRoute
}
