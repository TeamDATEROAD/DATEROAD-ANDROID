package org.sopt.dateroad.presentation.ui.navigator.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import org.sopt.dateroad.presentation.ui.home.navigation.homeNavGraph
import org.sopt.dateroad.presentation.ui.look.navigation.lookNavGraph
import org.sopt.dateroad.presentation.ui.mycourse.navigation.myCoursesGraph
import org.sopt.dateroad.presentation.ui.mypage.navigation.myPageNavGraph
import org.sopt.dateroad.presentation.ui.navigator.MainNavigator
import org.sopt.dateroad.presentation.ui.pointhistory.navigation.pointHistoryGraph
import org.sopt.dateroad.presentation.ui.profile.navigation.profileNavGraph
import org.sopt.dateroad.presentation.ui.read.navigation.readNavGraph
import org.sopt.dateroad.presentation.ui.timeline.navigation.timelineNavGraph

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
            startDestination = navigator.startDestination::class.simpleName.orEmpty()
        ) {
            homeNavGraph(
                padding = padding,
                navigateToPointHistory = navigator::navigatePointHistory,
                navigateToLook = navigator::navigateLook,
                navigateToTimeline = navigator::navigateTimeline
            )
            lookNavGraph(
                padding = padding
            )
            timelineNavGraph(
                padding = padding,
                navigateToPastDate = navigator::navigateToPastDate,
                navigateToEnroll = navigator::navigateToEnroll
            )
            readNavGraph(
                padding = padding,
                navigateToMyCourse = navigator::navigateMyCourse
            )
            myPageNavGraph(
                padding = padding,
                navigateToMyCourse = navigator::navigateMyCourse
            )
            pointHistoryGraph(
                padding = padding,
                popBackStack = navigator::popBackStackIfNotHome
            )
            myCoursesGraph(
                padding = padding,
                popBackStack = navigator::popBackStackIfNotHome
            )
            profileNavGraph(
                navigateToHome = navigator::navigateProfile
                // TODO: 추후 navigateHome 으로 변경
            )
        }
    }
}
