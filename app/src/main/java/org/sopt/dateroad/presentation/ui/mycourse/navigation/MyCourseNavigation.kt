package org.sopt.dateroad.presentation.ui.mycourse.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.sopt.dateroad.presentation.type.MyCourseType
import org.sopt.dateroad.presentation.ui.mycourse.MyCourseRoute
import org.sopt.dateroad.presentation.ui.mycourse.navigation.MyCoursesRoute.ROUTE_WITH_ARGUMENT

fun NavController.navigateToMyCourses(myCourseType: MyCourseType) {
    this.navigate(route = MyCoursesRoute.route(myCourseType = myCourseType))
}

fun NavGraphBuilder.myCoursesGraph(
    padding: PaddingValues,
    popBackStack: () -> Unit
) {
    composable(
        route = MyCoursesRoute.ARGUMENT,
        arguments = listOf(
            navArgument(ROUTE_WITH_ARGUMENT) {
                type = NavType.StringType
            }
        )
    ) { backStackEntry ->
        val myCourseType = backStackEntry.arguments?.getString(ROUTE_WITH_ARGUMENT)?.let {
            MyCourseType.valueOf(it)
        } ?: MyCourseType.ENROLL

        MyCourseRoute(padding = padding, popBackStack = popBackStack, myCourseType = myCourseType)
    }
}

object MyCoursesRoute {
    private const val ROUTE = "myCourses"
    const val ARGUMENT = "myCourseType"
    const val ROUTE_WITH_ARGUMENT = "$ROUTE/{myCourseType}"
    fun route(myCourseType: MyCourseType) = "$ROUTE/${myCourseType.name}"
}
