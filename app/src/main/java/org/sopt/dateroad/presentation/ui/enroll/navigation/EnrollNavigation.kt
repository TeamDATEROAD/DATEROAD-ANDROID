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

fun NavController.navigationEnroll(enrollType: EnrollType, viewPath: String, courseId: Int? = null) {
    navigate(
        route = EnrollRoute.route(enrollType = enrollType, viewPath = viewPath, courseId = courseId)
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
            navArgument(EnrollRoute.VIEW_PATH) {
                type = NavType.StringType
            },
            navArgument(EnrollRoute.TIMELINE_ID) {
                type = NavType.StringType
                nullable = true
            }
        )
    ) { backStackEntry ->
        val enrollType = backStackEntry.arguments?.getString(EnrollRoute.ENROLL_TYPE)?.let {
            EnrollType.valueOf(it)
        } ?: EnrollType.COURSE

        val viewPath = backStackEntry.arguments?.getString(EnrollRoute.VIEW_PATH).orEmpty()

        val timelineId = backStackEntry.arguments?.getString(EnrollRoute.TIMELINE_ID)?.toIntOrNull()

        EnrollRoute(padding = padding, popBackStack = popBackStack, navigateToMyCourse = navigationToMyCourse, enrollType = enrollType, viewPath = viewPath, timelineId = timelineId)
    }
}

object EnrollRoute {
    const val ROUTE = "enroll"
    const val ENROLL_TYPE = "enrollType"
    const val VIEW_PATH = "viewPath"
    const val TIMELINE_ID = "timelineId"
    const val ROUTE_WITH_ARGUMENT = "$ROUTE/{$ENROLL_TYPE}/{$VIEW_PATH}/{$TIMELINE_ID}"
    fun route(enrollType: EnrollType, viewPath: String, courseId: Int?) = "$ROUTE/${enrollType.name}/$viewPath/$courseId"
}
