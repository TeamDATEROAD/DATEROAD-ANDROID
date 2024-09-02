package org.sopt.dateroad.presentation.ui.mycourse.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.sopt.dateroad.presentation.type.EnrollType
import org.sopt.dateroad.presentation.type.MyCourseType
import org.sopt.dateroad.presentation.ui.mycourse.MyCourseRoute
import org.sopt.dateroad.presentation.ui.mycourse.navigation.MyCourseRoute.ROUTE_WITH_ARGUMENT

fun NavController.navigateMyCourses(myCourseType: MyCourseType) {
    this.navigate(route = MyCourseRoute.route(myCourseType = myCourseType))
}

fun NavGraphBuilder.myCoursesNavGraph(
    padding: PaddingValues,
    popBackStack: () -> Unit,
    navigateToEnroll: (EnrollType, Int?) -> Unit,
    navigateToCourseDetail: (Int) -> Unit
) {
    composable(
        route = ROUTE_WITH_ARGUMENT,
        arguments = listOf(
            navArgument(MyCourseRoute.ARGUMENT) {
                type = NavType.StringType
            }
        )
    ) { backStackEntry ->
        val myCourseType = backStackEntry.arguments?.getString(MyCourseRoute.ARGUMENT)?.let {
            MyCourseType.valueOf(it)
        } ?: MyCourseType.ENROLL

        MyCourseRoute(padding = padding, popBackStack = popBackStack, myCourseType = myCourseType, navigateToEnroll = navigateToEnroll, navigateToCourseDetail = navigateToCourseDetail)
    }
}

object MyCourseRoute {
    private const val ROUTE = "myCourses"
    const val ARGUMENT = "myCourseType"
    const val ROUTE_WITH_ARGUMENT = "$ROUTE/{$ARGUMENT}"
    fun route(myCourseType: MyCourseType) = "$ROUTE/${myCourseType.name}"
}
