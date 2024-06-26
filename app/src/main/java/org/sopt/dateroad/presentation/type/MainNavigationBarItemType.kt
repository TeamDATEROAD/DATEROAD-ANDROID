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
    DUMMY(
        iconRes = R.drawable.ic_launcher_foreground,
        label = R.string.app_name,
        route = MainNavigationBarRoute.Dummy
    );

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