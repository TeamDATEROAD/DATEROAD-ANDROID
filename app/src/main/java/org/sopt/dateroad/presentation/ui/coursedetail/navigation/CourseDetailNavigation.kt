package org.sopt.dateroad.presentation.ui.coursedetail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.sopt.dateroad.presentation.type.EnrollType
import org.sopt.dateroad.presentation.ui.coursedetail.CourseDetailRoute

fun NavController.navigationCourseDetail(courseId: Int) {
    this.navigate(route = CourseDetailRoute.route(courseId = courseId))
}

fun NavGraphBuilder.courseDetailGraph(
    popBackStack: () -> Unit,
    navigateToEnroll: (EnrollType, String, Int?) -> Unit
) {
    composable(
        route = CourseDetailRoute.ROUTE_WITH_ARGUMENT,
        arguments = listOf(
            navArgument(CourseDetailRoute.ID) {
                type = NavType.IntType
            }
        )
    ) { navBackStackEntry ->
        CourseDetailRoute(
            popBackStack = popBackStack,
            navigateToEnroll = navigateToEnroll,
            courseId = navBackStackEntry.arguments?.getInt(CourseDetailRoute.ID) ?: 0
        )
    }
}

object CourseDetailRoute {
    private const val ROUTE = "courseDetail"
    const val ID = "id"
    const val ROUTE_WITH_ARGUMENT = "$ROUTE/{$ID}"
    fun route(courseId: Int) = "$ROUTE/$courseId"
}
