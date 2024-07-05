package org.sopt.dateroad.presentation.type

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import org.sopt.dateroad.R
import org.sopt.dateroad.presentation.model.MainNavigationBarRoute
import org.sopt.dateroad.presentation.model.Route

enum class MainNavigationBarItemType(
    @DrawableRes val iconRes: Int,
    @StringRes val label: Int,
    val route: MainNavigationBarRoute
) {
    HOME(
        iconRes = R.drawable.ic_nav_home_selected,
        label = R.string.main_navigation_bar_item_home,
        route = MainNavigationBarRoute.Home
    ),
    LOOK(
        iconRes = R.drawable.ic_nav_look_selected,
        label = R.string.main_navigation_bar_item_look,
        route = MainNavigationBarRoute.Look
    ),
    TIMELINE(
        iconRes = R.drawable.ic_nav_timeline_selected,
        label = R.string.main_navigation_bar_item_timeline,
        route = MainNavigationBarRoute.Timeline
    ),
    READ(
        iconRes = R.drawable.ic_nav_read_selected,
        label = R.string.main_navigation_bar_item_read,
        route = MainNavigationBarRoute.Read
    ),
    MY_PAGE(
        iconRes = R.drawable.ic_nav_my_page_selected,
        label = R.string.main_navigation_bar_item_my_page,
        route = MainNavigationBarRoute.MyPage
    )
//    SEARCH(
//        iconRes = R.drawable.ic_nav_search_selected,
//        label = R.string.main_navigation_bar_item_search,
//        route = MainNavigationBarRoute.Search
//    )
    ;

    companion object {
        @Composable
        fun find(predicate: @Composable (MainNavigationBarRoute) -> Boolean): MainNavigationBarItemType? {
            return entries.find { predicate(it.route) }
        }

        @Composable
        fun contains(predicate: @Composable (Route) -> Boolean): Boolean {
            return entries.map { it.route }.any { predicate(it) }
        }
    }
}
