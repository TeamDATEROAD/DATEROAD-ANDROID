package org.sopt.dateroad.presentation.ui.coursedetail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.sopt.dateroad.presentation.type.CourseDetailType
import org.sopt.dateroad.presentation.type.EnrollType
import org.sopt.dateroad.presentation.ui.coursedetail.CourseDetailRoute

fun NavController.navigationCourseDetail(courseDetailType: CourseDetailType, id: Int) {
    this.navigate(route = CourseDetailRoute.route(courseDetailType = courseDetailType, id = id))
}

fun NavGraphBuilder.courseDetailGraph(
    popBackStack: () -> Unit,
    navigateToEnroll: (EnrollType, Int?) -> Unit
) {
    composable(
        route = CourseDetailRoute.ROUTE_WITH_ARGUMENT,
        arguments = listOf(
            navArgument(CourseDetailRoute.COURSE_DETAIL_TYPE) {
                type = NavType.StringType
            },
            navArgument(CourseDetailRoute.ID) {
                type = NavType.IntType
            }
        )
    ) { backStackEntry ->
        val courseDetailType = backStackEntry.arguments?.getString(CourseDetailRoute.COURSE_DETAIL_TYPE)?.let {
            CourseDetailType.valueOf(it)
        } ?: CourseDetailType.COURSE

        val id = backStackEntry.arguments?.getInt(CourseDetailRoute.ID) ?: 0

        CourseDetailRoute(
            popBackStack = popBackStack,
            navigateToEnroll = navigateToEnroll,
            courseDetailType = courseDetailType,
            id = id
        )
    }
}

object CourseDetailRoute {
    private const val ROUTE = "courseDetail"
    const val COURSE_DETAIL_TYPE = "enrollType"
    const val ID = "id"
    const val ROUTE_WITH_ARGUMENT = "$ROUTE/{$COURSE_DETAIL_TYPE}/{$ID}"
    fun route(courseDetailType: CourseDetailType, id: Int) = "$ROUTE/${courseDetailType.name}/$id"
}
