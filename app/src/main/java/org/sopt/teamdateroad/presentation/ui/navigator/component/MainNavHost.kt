package org.sopt.teamdateroad.presentation.ui.navigator.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import org.sopt.teamdateroad.presentation.ui.advertisement.navigation.advertisementGraph
import org.sopt.teamdateroad.presentation.ui.coursedetail.navigation.courseDetailGraph
import org.sopt.teamdateroad.presentation.ui.enroll.navigation.enrollNavGraph
import org.sopt.teamdateroad.presentation.ui.home.navigation.homeNavGraph
import org.sopt.teamdateroad.presentation.ui.look.navigation.lookNavGraph
import org.sopt.teamdateroad.presentation.ui.mycourse.navigation.myCoursesNavGraph
import org.sopt.teamdateroad.presentation.ui.mypage.navigation.myPageNavGraph
import org.sopt.teamdateroad.presentation.ui.navigator.MainNavigator
import org.sopt.teamdateroad.presentation.ui.onboarding.navigation.onboardingNavGraph
import org.sopt.teamdateroad.presentation.ui.past.navigation.pastNavGraph
import org.sopt.teamdateroad.presentation.ui.pointguide.navigation.pointGuideGraph
import org.sopt.teamdateroad.presentation.ui.pointhistory.navigation.pointHistoryGraph
import org.sopt.teamdateroad.presentation.ui.profile.navigation.profileNavGraph
import org.sopt.teamdateroad.presentation.ui.read.navigation.readNavGraph
import org.sopt.teamdateroad.presentation.ui.signin.navigation.signInGraph
import org.sopt.teamdateroad.presentation.ui.timeline.navigation.timelineNavGraph
import org.sopt.teamdateroad.presentation.ui.timelinedetail.navigation.timelineDetailGraph
import org.sopt.teamdateroad.presentation.util.ViewPath.HOME
import org.sopt.teamdateroad.presentation.util.ViewPath.TIMELINE
import org.sopt.teamdateroad.ui.theme.DateRoadTheme

@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    navigator: MainNavigator,
    padding: PaddingValues
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(DateRoadTheme.colors.white)
    ) {
        NavHost(
            navController = navigator.navHostController,
            startDestination = navigator.startDestination
        ) {
            advertisementGraph(
                popBackStack = navigator::popBackStackIfNotHome
            )

            courseDetailGraph(
                popBackStack = navigator::popBackStackIfNotHome,
                navigateToEnroll = navigator::navigateToEnroll
            )

            enrollNavGraph(
                padding = padding,
                popBackStack = navigator::popBackStackIfNotHome,
                navigationToMyCourse = navigator::navigateToMyCourse
            )

            homeNavGraph(
                padding = padding,
                navigateToPointHistory = navigator::navigateToPointHistory,
                navigateToLook = navigator::navigateMainNavigation,
                navigateToTimelineDetail = navigator::navigateToTimelineDetail,
                navigateToEnroll = navigator::navigateToEnroll,
                navigateToAdvertisement = navigator::navigateToAdvertisement,
                navigateToCourseDetail = navigator::navigateToCourseDetail
            )

            lookNavGraph(
                padding = padding,
                navigateToEnroll = navigator::navigateToEnroll,
                navigateToCourseDetail = navigator::navigateToCourseDetail
            )

            myCoursesNavGraph(
                padding = padding,
                popBackStack = navigator::popBackStackIfNotHome,
                navigateToEnroll = navigator::navigateToEnroll,
                navigateToCourseDetail = navigator::navigateToCourseDetail
            )

            myPageNavGraph(
                padding = padding,
                navigateToPointHistory = navigator::navigateToPointHistory,
                navigateToMyCourse = navigator::navigateToMyCourse,
                navigateToPointGuide = navigator::navigateToPointGuide,
                navigateToSignIn = navigator::navigateToSignIn,
                navigateToProfile = navigator::navigateToProfile
            )

            onboardingNavGraph(
                navigateToProfile = navigator::navigateToProfile,
                navigateToSignIn = navigator::navigateToSignIn
            )

            pastNavGraph(
                padding = padding,
                popBackStack = navigator::popBackStackIfNotHome,
                navigateToTimelineDetail = navigator::navigateToTimelineDetail
            )

            pointGuideGraph(
                padding = padding,
                popBackStack = navigator::popBackStackIfNotHome
            )

            pointHistoryGraph(
                padding = padding,
                popBackStack = navigator::popBackStackIfNotHome
            )

            profileNavGraph(
                navigateToHome = navigator::navigateToHome,
                navigateToMyPage = navigator::navigateToMyPage,
                popBackStack = navigator::popBackStackIfNotHome
            )

            readNavGraph(
                padding = padding,
                navigateToEnroll = navigator::navigateToEnroll,
                navigateToCourseDetail = navigator::navigateToCourseDetail
            )

            signInGraph(
                navigateToOnboarding = navigator::navigateToOnboarding,
                navigateToHome = navigator::navigateToHome
            )

            timelineNavGraph(
                padding = padding,
                navigateToPast = navigator::navigateToPast,
                navigateToEnroll = navigator::navigateToEnroll,
                navigateToTimelineDetail = navigator::navigateToTimelineDetail
            )

            val previousRoute = navigator.navHostController.previousBackStackEntry?.destination?.route ?: "Unknown"

            val previousView = when (previousRoute) {
                HOME -> "홈"
                TIMELINE -> "데이트 일정"
                else -> "Unknown"
            }

            timelineDetailGraph(
                popBackStack = navigator::popBackStackIfNotHome,
                viewPath = previousView
            )
        }
    }
}
