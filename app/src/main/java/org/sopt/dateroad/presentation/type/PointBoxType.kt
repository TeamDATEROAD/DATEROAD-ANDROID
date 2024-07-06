package org.sopt.dateroad.presentation.type

import androidx.compose.ui.graphics.Color
import org.sopt.dateroad.ui.theme.defaultDateRoadColors

enum class PointBoxType(
    backgroundColor: Color,
    titleTextColor: Color,
    pointTextColor: Color
) {
    POINT_HISTORY(
        backgroundColor = defaultDateRoadColors.deepPurple,
        titleTextColor = defaultDateRoadColors.white,
        pointTextColor = defaultDateRoadColors.white
    ),
    MY_PAGE(
        backgroundColor = defaultDateRoadColors.white,
        titleTextColor = defaultDateRoadColors.gray400,
        pointTextColor = defaultDateRoadColors.black
    )
}
