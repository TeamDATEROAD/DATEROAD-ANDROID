package org.sopt.dateroad.presentation.type

import androidx.compose.ui.graphics.Color
import org.sopt.dateroad.ui.theme.Black
import org.sopt.dateroad.ui.theme.DateRoadColors
import org.sopt.dateroad.ui.theme.DateRoadTheme
import org.sopt.dateroad.ui.theme.DeepPurple
import org.sopt.dateroad.ui.theme.Gray400
import org.sopt.dateroad.ui.theme.White
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
