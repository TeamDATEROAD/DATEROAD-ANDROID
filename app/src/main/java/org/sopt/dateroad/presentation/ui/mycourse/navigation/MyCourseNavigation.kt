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

fun NavController.navigateMyCourses(myCourseType: MyCourseType) {
    this.navigate(route = MyCoursesRoute.route(myCourseType = myCourseType))
}

fun NavGraphBuilder.myCoursesNavGraph(
    padding: PaddingValues,
    popBackStack: () -> Unit
) {
    composable(
        route = ROUTE_WITH_ARGUMENT,
        arguments = listOf(
            navArgument(MyCoursesRoute.ARGUMENT) {
                type = NavType.StringType
            }
        )
    ) { backStackEntry ->
        val myCourseType = backStackEntry.arguments?.getString(MyCoursesRoute.ARGUMENT)?.let {
            MyCourseType.valueOf(it)
        } ?: MyCourseType.ENROLL

        MyCourseRoute(padding = padding, popBackStack = popBackStack, myCourseType = myCourseType)
    }
}

object MyCoursesRoute {
    private const val ROUTE = "myCourses"
    const val ARGUMENT = "myCourseType"
    const val ROUTE_WITH_ARGUMENT = "$ROUTE/{$ARGUMENT}"
    fun route(myCourseType: MyCourseType) = "$ROUTE/${myCourseType.name}"
}
