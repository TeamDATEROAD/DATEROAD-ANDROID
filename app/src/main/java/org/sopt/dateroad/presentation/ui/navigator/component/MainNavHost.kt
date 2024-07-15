package org.sopt.dateroad.presentation.ui.navigator.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import org.sopt.dateroad.presentation.ui.enroll.navigation.enrollNavGraph
import org.sopt.dateroad.presentation.ui.home.navigation.homeNavGraph
import org.sopt.dateroad.presentation.ui.look.navigation.lookNavGraph
import org.sopt.dateroad.presentation.ui.mycourse.navigation.myCoursesNavGraph
import org.sopt.dateroad.presentation.ui.mypage.navigation.myPageNavGraph
import org.sopt.dateroad.presentation.ui.navigator.MainNavigator
import org.sopt.dateroad.presentation.ui.onboarding.navigation.onboardingNavGraph
import org.sopt.dateroad.presentation.ui.past.navigation.pastNavGraph
import org.sopt.dateroad.presentation.ui.pointhistory.navigation.pointHistoryGraph
import org.sopt.dateroad.presentation.ui.profile.navigation.profileNavGraph
import org.sopt.dateroad.presentation.ui.read.navigation.readNavGraph
import org.sopt.dateroad.presentation.ui.signin.navigation.signInGraph
import org.sopt.dateroad.presentation.ui.timeline.navigation.timelineNavGraph
import org.sopt.dateroad.presentation.ui.timelinedetail.navigation.timelineDetailGraph

@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    navigator: MainNavigator,
    padding: PaddingValues
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceDim)
    ) {
        NavHost(
            navController = navigator.navHostController,
            startDestination = navigator.startDestination
        ) {
            enrollNavGraph(
                padding = padding,
                popBackStack = navigator::popBackStackIfNotHome,
                navigationToMyCourse = navigator::navigateToMyCourse
            )

            homeNavGraph(
                padding = padding,
                navigateToPointHistory = navigator::navigateToPointHistory,
                navigateToLook = navigator::navigateToLook,
                navigateToTimeline = navigator::navigateTimeline,
                navigateToEnroll = navigator::navigateToEnroll
            )

            lookNavGraph(
                padding = padding,
                navigateToEnroll = navigator::navigateToEnroll
            )

            myCoursesNavGraph(
                padding = padding,
                popBackStack = navigator::popBackStackIfNotHome
            )

            myPageNavGraph(
                padding = padding,
                navigateToPointHistory = navigator::navigateToPointHistory,
                navigateToMyCourse = navigator::navigateToMyCourse
            )

            onboardingNavGraph(navigateToProfile = navigator::navigateToProfile)

            pastNavGraph(
                padding = padding,
                popBackStack = navigator::popBackStackIfNotHome,
                navigateToTimelineDetail = navigator::navigateToTimelineDetail
            )

            pointHistoryGraph(
                padding = padding,
                popBackStack = navigator::popBackStackIfNotHome
            )

            pointHistoryGraph(
                padding = padding,
                popBackStack = navigator::popBackStackIfNotHome
            )

            profileNavGraph(
                navigateToHome = navigator::navigateToProfile
                // TODO: 추후 navigateHome 으로 변경
            )

            signInGraph(
                navigateToOnboarding = navigator::navigateToOnboarding,
                navigateToHome = navigator::navigateToHome
            )

            readNavGraph(
                padding = padding,
                navigateToEnroll = navigator::navigateToEnroll
            )

            timelineNavGraph(
                padding = padding,
                navigateToPast = navigator::navigateToPast,
                navigateToEnroll = navigator::navigateToEnroll,
                navigateToTimelineDetail = navigator::navigateToTimelineDetail,
                popBackStack = navigator::popBackStackIfNotHome
            )

            timelineDetailGraph(
                padding = padding,
                popBackStack = navigator::popBackStackIfNotHome
            )
        }
    }
}
