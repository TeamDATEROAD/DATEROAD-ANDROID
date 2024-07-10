package org.sopt.dateroad.presentation.ui.mycourse.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.sopt.dateroad.presentation.type.MyCourseType
import org.sopt.dateroad.presentation.ui.mycourse.MyCourseRoute
import org.sopt.dateroad.presentation.ui.mycourse.navigation.MyCoursesRoute.BASE_TYPE

fun NavController.navigateToMyCourses(myCourseType: MyCourseType) {
    this.navigate(route = MyCoursesRoute.route(myCourseType = myCourseType))
}

fun NavGraphBuilder.myCoursesGraph(
    padding: PaddingValues,
    popBackStack: () -> Unit
) {
    composable(
        route = MyCoursesRoute.ROUTE_WITH_ARGUMENT,
        arguments = listOf(
            navArgument(BASE_TYPE) {
                type = NavType.StringType
            }
        )
    ) { backStackEntry ->
        val myCourseType = backStackEntry.arguments?.getString(BASE_TYPE)?.let {
            MyCourseType.valueOf(it)
        } ?: MyCourseType.ENROLL

        MyCourseRoute(padding = padding, popBackStack = popBackStack, myCourseType = myCourseType)
    }
}

object MyCoursesRoute {
    const val BASE_ROUTE = "myCourses"
    const val ROUTE_WITH_ARGUMENT = "$BASE_ROUTE/{myCourseType}"
    const val BASE_TYPE = "myCourseType"
    fun route(myCourseType: MyCourseType) = "$BASE_ROUTE/${myCourseType.name}"
}
