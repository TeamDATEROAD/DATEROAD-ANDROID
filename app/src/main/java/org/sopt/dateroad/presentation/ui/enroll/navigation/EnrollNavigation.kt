package org.sopt.dateroad.presentation.ui.enroll.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.sopt.dateroad.presentation.type.EnrollType
import org.sopt.dateroad.presentation.type.MyCourseType
import org.sopt.dateroad.presentation.ui.enroll.EnrollRoute
import org.sopt.dateroad.presentation.ui.mycourse.navigation.MyCourseRoute

fun NavController.navigationEnroll(enrollType: EnrollType, courseId: Int? = null) {
    navigate(
        route = EnrollRoute.route(enrollType = enrollType, courseId = courseId)
    ) {
        popUpTo(MyCourseRoute.route(MyCourseType.READ)) { inclusive = true }
        launchSingleTop = true
    }
}

fun NavGraphBuilder.enrollNavGraph(
    padding: PaddingValues,
    popBackStack: () -> Unit,
    navigationToMyCourse: (MyCourseType) -> Unit
) {
    composable(
        route = EnrollRoute.ROUTE_WITH_ARGUMENT,
        arguments = listOf(
            navArgument(EnrollRoute.ENROLL_TYPE) {
                type = NavType.StringType
            },
            navArgument(EnrollRoute.COURSE_ID) {
                type = NavType.StringType
                nullable = true
            }
        )
    ) { backStackEntry ->
        val enrollType = backStackEntry.arguments?.getString(EnrollRoute.ENROLL_TYPE)?.let {
            EnrollType.valueOf(it)
        } ?: EnrollType.COURSE
        val courseId = backStackEntry.arguments?.getString(EnrollRoute.COURSE_ID)?.toIntOrNull()
        EnrollRoute(padding = padding, popBackStack = popBackStack, navigateToMyCourse = navigationToMyCourse, enrollType = enrollType, id = courseId)
    }
}

object EnrollRoute {
    const val ROUTE = "enroll"
    const val ENROLL_TYPE = "enrollType"
    const val COURSE_ID = "courseId"
    const val ROUTE_WITH_ARGUMENT = "$ROUTE/{$ENROLL_TYPE}/{$COURSE_ID}"
    fun route(enrollType: EnrollType, courseId: Int?) = "$ROUTE/${enrollType.name}/$courseId"
}
