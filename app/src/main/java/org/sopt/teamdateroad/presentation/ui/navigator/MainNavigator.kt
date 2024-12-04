package org.sopt.teamdateroad.presentation.ui.navigator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import org.sopt.teamdateroad.presentation.model.MainNavigationBarRoute
import org.sopt.teamdateroad.presentation.model.Route
import org.sopt.teamdateroad.presentation.type.EnrollType
import org.sopt.teamdateroad.presentation.type.MainNavigationBarItemType
import org.sopt.teamdateroad.presentation.type.MyCourseType
import org.sopt.teamdateroad.presentation.type.ProfileType
import org.sopt.teamdateroad.presentation.type.TimelineType
import org.sopt.teamdateroad.presentation.ui.advertisement.navigation.navigationAdvertisement
import org.sopt.teamdateroad.presentation.ui.coursedetail.navigation.navigationCourseDetail
import org.sopt.teamdateroad.presentation.ui.enroll.navigation.navigationEnroll
import org.sopt.teamdateroad.presentation.ui.home.navigation.navigationHome
import org.sopt.teamdateroad.presentation.ui.look.navigation.navigationLook
import org.sopt.teamdateroad.presentation.ui.mycourse.navigation.navigateMyCourses
import org.sopt.teamdateroad.presentation.ui.mypage.navigation.navigationMyPage
import org.sopt.teamdateroad.presentation.ui.onboarding.navigation.navigationOnboarding
import org.sopt.teamdateroad.presentation.ui.past.navigation.navigationPast
import org.sopt.teamdateroad.presentation.ui.pointguide.navigation.navigationPointGuide
import org.sopt.teamdateroad.presentation.ui.pointhistory.navigation.navigationPointHistory
import org.sopt.teamdateroad.presentation.ui.profile.navigation.navigationProfile
import org.sopt.teamdateroad.presentation.ui.read.navigation.navigationRead
import org.sopt.teamdateroad.presentation.ui.signin.navigation.SignInRoute
import org.sopt.teamdateroad.presentation.ui.signin.navigation.navigationSignIn
import org.sopt.teamdateroad.presentation.ui.timeline.navigation.navigationTimeline
import org.sopt.teamdateroad.presentation.ui.timelinedetail.navigation.navigateToTimelineDetail

class MainNavigator(
    val navHostController: NavHostController
) {
    private val currentDestination: NavDestination?
        @Composable get() = navHostController.currentBackStackEntryAsState().value?.destination

    val startDestination = SignInRoute.ROUTE

    val currentMainNavigationBarItem: MainNavigationBarItemType?
        @Composable get() = MainNavigationBarItemType.find { mainNavigationBarRoute ->
            currentDestination?.route == mainNavigationBarRoute::class.simpleName
        }

    fun navigateMainNavigation(mainNavigationBarItemType: MainNavigationBarItemType) {
        navOptions {
            popUpTo(MainNavigationBarRoute.Home::class.simpleName.orEmpty()) {
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

    fun navigateToAdvertisement(advertisementId: Int) {
        navHostController.navigationAdvertisement(advertisementId = advertisementId)
    }

    fun navigateToCourseDetail(courseId: Int) {
        navHostController.navigationCourseDetail(courseId = courseId)
    }

    fun navigateToEnroll(enrollType: EnrollType, viewPath: String, courseId: Int?) {
        navHostController.navigationEnroll(enrollType = enrollType, viewPath = viewPath, courseId = courseId)
    }

    fun navigateToHome(navOptions: NavOptions? = null) {
        navHostController.navigationHome(
            navOptions ?: navOptions {
                popUpTo(navHostController.graph.findStartDestination().id) {
                    inclusive = true
                }
                launchSingleTop = true
            }
        )
    }

    fun navigateToMyCourse(myCourseType: MyCourseType) {
        navHostController.navigateMyCourses(myCourseType = myCourseType)
    }

    fun navigateToMyPage(navOptions: NavOptions? = null) {
        navHostController.navigationMyPage(
            navOptions ?: navOptions {
                popUpTo(navHostController.graph.findStartDestination().id) {
                    inclusive = true
                }
                launchSingleTop = true
            }
        )
    }

    fun navigateToOnboarding() {
        navHostController.navigationOnboarding()
    }

    fun navigateToPast() {
        navHostController.navigationPast()
    }

    fun navigateToPointGuide() {
        navHostController.navigationPointGuide()
    }

    fun navigateToPointHistory() {
        navHostController.navigationPointHistory()
    }

    fun navigateToProfile(profileType: ProfileType) {
        navHostController.navigationProfile(profileType = profileType)
    }

    fun navigateToSignIn() {
        navHostController.navigationSignIn()
    }

    fun navigateToTimelineDetail(timelineType: TimelineType, dateId: Int) {
        navHostController.navigateToTimelineDetail(timelineType, dateId)
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
