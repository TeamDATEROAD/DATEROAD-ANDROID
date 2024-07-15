package org.sopt.dateroad.presentation.ui.coursedetail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.sopt.dateroad.presentation.ui.coursedetail.CourseDetailRoute

fun NavController.navigationCourseDetail() {
    navigate(
        route = CourseDetailRoute.ROUTE
    )
}

fun NavGraphBuilder.courseDetailGraph(
    popBackStack: () -> Unit
) {
    composable(route = CourseDetailRoute.ROUTE) {
        CourseDetailRoute(popBackStack = popBackStack)
    }
}

object CourseDetailRoute {
    const val ROUTE = "courseDetail"
}
