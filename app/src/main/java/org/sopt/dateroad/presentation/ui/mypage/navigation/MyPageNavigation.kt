package org.sopt.dateroad.presentation.ui.mypage.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.sopt.dateroad.presentation.model.MainNavigationBarRoute
import org.sopt.dateroad.presentation.ui.home.MyPageRoute

fun NavController.navigationMyPage(navOptions: NavOptions) {
    navigate(
        route = MainNavigationBarRoute.MyPage::class.simpleName.orEmpty(),
        navOptions = navOptions
    )
}

fun NavGraphBuilder.myPageNavGraph(
    padding: PaddingValues
) {
    composable(route = MainNavigationBarRoute.MyPage::class.simpleName.orEmpty()) {
        MyPageRoute(padding = padding)
    }
}