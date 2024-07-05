package org.sopt.dateroad.presentation.type

import androidx.compose.ui.graphics.Color
import org.sopt.dateroad.ui.theme.Black
import org.sopt.dateroad.ui.theme.DeepPurple
import org.sopt.dateroad.ui.theme.Gray400
import org.sopt.dateroad.ui.theme.White

enum class PointBoxType(
    backgroundColor: Color,
    titleTextColor: Color,
    pointTextColor: Color
) {
    POINT_HISTORY(
        backgroundColor = DeepPurple,
        titleTextColor = White,
        pointTextColor = White
    ),
    MY_PAGE(
        backgroundColor = White,
        titleTextColor = Gray400,
        pointTextColor = Black
    )
}
