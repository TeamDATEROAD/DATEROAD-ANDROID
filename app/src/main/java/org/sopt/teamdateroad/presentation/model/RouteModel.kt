package org.sopt.teamdateroad.presentation.model

sealed interface Route

sealed interface MainNavigationBarRoute : Route {
    data object Dummy : MainNavigationBarRoute
    data object Home : MainNavigationBarRoute
    data object Look : MainNavigationBarRoute
    data object Timeline : MainNavigationBarRoute
    data object Read : MainNavigationBarRoute
    data object MyPage : MainNavigationBarRoute
// TODO:    data object Search : MainNavigationBarRoute
}
