package org.sopt.dateroad.presentation.ui.navigator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import org.sopt.dateroad.presentation.model.MainNavigationBarRoute
import org.sopt.dateroad.presentation.model.Route
import org.sopt.dateroad.presentation.type.EnrollType
import org.sopt.dateroad.presentation.type.MainNavigationBarItemType
import org.sopt.dateroad.presentation.type.MyCourseType
import org.sopt.dateroad.presentation.ui.enroll.navigation.navigationEnroll
import org.sopt.dateroad.presentation.ui.home.navigation.navigationHome
import org.sopt.dateroad.presentation.ui.look.navigation.navigationLook
import org.sopt.dateroad.presentation.ui.mycourse.navigation.navigateToMyCourses
import org.sopt.dateroad.presentation.ui.mypage.navigation.navigationMyPage
import org.sopt.dateroad.presentation.ui.onboarding.navigation.navigationOnboarding
import org.sopt.dateroad.presentation.ui.pastdate.navigation.navigateToPastDate
import org.sopt.dateroad.presentation.ui.pointhistory.navigation.navigationPointHistory
import org.sopt.dateroad.presentation.ui.profile.navigation.navigationProfile
import org.sopt.dateroad.presentation.ui.read.navigation.navigationRead
import org.sopt.dateroad.presentation.ui.timeline.navigation.navigationTimeline

class MainNavigator(
    val navHostController: NavHostController
) {
    private val currentDestination: NavDestination?
        @Composable get() = navHostController.currentBackStackEntryAsState().value?.destination

    val startDestination = MainNavigationBarItemType.HOME.route

    val currentMainNavigationBarItem: MainNavigationBarItemType?
        @Composable get() = MainNavigationBarItemType.find { mainNavigationBarRoute ->
            currentDestination?.route == mainNavigationBarRoute::class.simpleName
        }

    fun navigateMainNavigation(mainNavigationBarItemType: MainNavigationBarItemType) {
        navOptions {
            popUpTo(navHostController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }.let { navOptions ->
            when (mainNavigationBarItemType) {
                MainNavigationBarItemType.HOME -> navHostController.navigationHome(navOptions)
                MainNavigationBarItemType.LOOK -> navHostController.navigationLook(navOptions)
                MainNavigationBarItemType.TIMELINE -> navHostController.navigationTimeline(navOptions)
                MainNavigationBarItemType.READ -> navHostController.navigationRead(navOptions)
                MainNavigationBarItemType.MY_PAGE -> navHostController.navigationMyPage(navOptions)
                // TODO:MainNavigationBarItemType.SEARCH -> navHostController.navigationDummy(navOptions)
            }
        }
    }

    fun navigateToEnroll(enrollType: EnrollType) {
        navHostController.navigationEnroll(enrollType = enrollType)
    }

    fun navigatePointHistory() {
        navHostController.navigationPointHistory()
    }

    fun navigateOnboarding() {
        navHostController.navigationOnboarding()
    }

    fun navigateProfile() {
        navHostController.navigationProfile()
    }

    fun navigateToMyCourse(myCourseType: MyCourseType) {
        navHostController.navigateToMyCourses(myCourseType = myCourseType)
    }

    fun navigateToPastDate() {
        navHostController.navigateToPastDate()
    }

    private fun popBackStack() {
        navHostController.popBackStack()
    }

    fun popBackStackIfNotHome() {
        if (!isSameCurrentDestination<MainNavigationBarRoute.Dummy>()) {
            popBackStack()
        }
    }

    private inline fun <reified T : Route> isSameCurrentDestination(): Boolean =
        navHostController.currentDestination?.route == T::class.simpleName

    @Composable
    fun showBottomBar(): Boolean = MainNavigationBarItemType.contains {
        currentDestination?.route == it::class.simpleName
    }
}

@Composable
fun rememberMainNavigator(
    navHostController: NavHostController = rememberNavController()
): MainNavigator = remember(navHostController) {
    MainNavigator(navHostController = navHostController)
}
