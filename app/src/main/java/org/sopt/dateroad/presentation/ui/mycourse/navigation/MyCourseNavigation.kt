package org.sopt.dateroad.presentation.ui.mycourse.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.sopt.dateroad.presentation.ui.mycourse.MyCourseRoute

fun NavController.navigateToMyCourses(navOptions: NavOptions) {
    navigate(route = MyCoursesRoute.ROUTE)
}

fun NavGraphBuilder.myCoursesGraph(
    padding: PaddingValues,
    popBackStack: () -> Unit
) {
    composable(route = MyCoursesRoute.ROUTE) {
        MyCourseRoute(padding = padding, popBackStack = popBackStack)
    }
}

object MyCoursesRoute {
    const val ROUTE = "myCourses"
}
