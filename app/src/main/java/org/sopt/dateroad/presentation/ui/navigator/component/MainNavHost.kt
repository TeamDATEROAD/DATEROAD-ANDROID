package org.sopt.dateroad.presentation.ui.navigator.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import org.sopt.dateroad.presentation.type.ProfileType
import org.sopt.dateroad.presentation.ui.coursedetail.navigation.courseDetailGraph
import org.sopt.dateroad.presentation.ui.enroll.navigation.enrollNavGraph
import org.sopt.dateroad.presentation.ui.home.navigation.homeNavGraph
import org.sopt.dateroad.presentation.ui.look.navigation.lookNavGraph
import org.sopt.dateroad.presentation.ui.mycourse.navigation.myCoursesNavGraph
import org.sopt.dateroad.presentation.ui.mypage.navigation.myPageNavGraph
import org.sopt.dateroad.presentation.ui.navigator.MainNavigator
import org.sopt.dateroad.presentation.ui.onboarding.navigation.onboardingNavGraph
import org.sopt.dateroad.presentation.ui.past.navigation.pastNavGraph
import org.sopt.dateroad.presentation.ui.pointguide.navigation.pointGuideGraph
import org.sopt.dateroad.presentation.ui.pointhistory.navigation.pointHistoryGraph
import org.sopt.dateroad.presentation.ui.profile.navigation.editProfileNavGraph
import org.sopt.dateroad.presentation.ui.profile.navigation.enrollProfileNavGraph
import org.sopt.dateroad.presentation.ui.read.navigation.readNavGraph
import org.sopt.dateroad.presentation.ui.signin.navigation.signInGraph
import org.sopt.dateroad.presentation.ui.timeline.navigation.timelineNavGraph
import org.sopt.dateroad.presentation.ui.timelinedetail.navigation.timelineDetailGraph
import org.sopt.dateroad.ui.theme.DateRoadTheme

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
                navigateToLook = navigator::navigateToLook,
                navigateToTimeline = navigator::navigateTimeline,
                navigateToEnroll = navigator::navigateToEnroll,
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
                navigateToEnroll = navigator::navigateToEnroll
            )

            myPageNavGraph(
                padding = padding,
                navigateToPointHistory = navigator::navigateToPointHistory,
                navigateToMyCourse = navigator::navigateToMyCourse,
                navigateToPointGuide = navigator::navigateToPointGuide,
                navigateToLogin = navigator::navigateToSignIn,
                navigateToEditProfile = navigator::navigateToEditProfile
            )

            onboardingNavGraph(navigateToEnrollProfile = navigator::navigateToEnrollProfile)

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

            enrollProfileNavGraph(
                navigateToHome = navigator::navigateToHome,
                navigateToMyPage = navigator::navigateToMyPage,
                profileType = ProfileType.Enroll
            )
            editProfileNavGraph(
                navigateToHome = navigator::navigateToHome,
                navigateToMyPage = navigator::navigateToMyPage,
                profileType = ProfileType.Edit
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
                navigateToTimelineDetail = navigator::navigateToTimelineDetail,
                popBackStack = navigator::popBackStackIfNotHome
            )

            timelineDetailGraph(
                padding = padding,
                popBackStack = navigator::popBackStackIfNotHome,
                navigateToEnroll = navigator::navigateToEnroll
            )
        }
    }
}
