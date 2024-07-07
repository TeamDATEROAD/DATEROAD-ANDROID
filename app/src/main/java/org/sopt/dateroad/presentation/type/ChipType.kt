package org.sopt.dateroad.presentation.type

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.ui.theme.Black
import org.sopt.dateroad.ui.theme.DeepPurple
import org.sopt.dateroad.ui.theme.Gray100
import org.sopt.dateroad.ui.theme.Gray400
import org.sopt.dateroad.ui.theme.White
import org.sopt.dateroad.ui.theme.DateRoadTheme
import org.sopt.dateroad.ui.theme.DateRoadTypography
import org.sopt.dateroad.ui.theme.defaultDateRoadTypography

enum class ChipType(
    val selectedBackgroundColor: Color,
    val unselectedBackgroundColor: Color,
    val selectedTextColor: Color,
    val unselectedTextColor: Color,
    val horizontalPadding: Dp,
    val verticalPadding: Dp,
    val cornerRadius: Dp,
    val textStyle: TextStyle
) {
    ENROLL_COURSE(
        selectedBackgroundColor = DeepPurple,
        unselectedBackgroundColor = Gray100,
        selectedTextColor = White,
        unselectedTextColor = Black,
        horizontalPadding = 14.dp,
        verticalPadding = 6.dp,
        cornerRadius = 20.dp,
        textStyle = defaultDateRoadTypography.bodySemi13
    ),

    MY_PROFILE(
        selectedBackgroundColor = DeepPurple,
        unselectedBackgroundColor = Gray100,
        selectedTextColor = White,
        unselectedTextColor = Black,
        horizontalPadding = 10.dp,
        verticalPadding = 5.dp,
        cornerRadius = 20.dp,
        textStyle = defaultDateRoadTypography.bodySemi13
    ),

    MONEY(
        selectedBackgroundColor = DeepPurple,
        unselectedBackgroundColor = Gray100,
        selectedTextColor = White,
        unselectedTextColor = Gray400,
        horizontalPadding = 8.dp,
        verticalPadding = 6.dp,
        cornerRadius = 20.dp,
        textStyle = defaultDateRoadTypography.bodyMed13
    ),

    AREA(
        selectedBackgroundColor = DeepPurple,
        unselectedBackgroundColor = Gray100,
        selectedTextColor = White,
        unselectedTextColor = Gray400,
        horizontalPadding = 14.dp,
        verticalPadding = 6.dp,
        cornerRadius = 10.dp,
        textStyle = defaultDateRoadTypography.bodyMed13
    ),

    REGION(
        selectedBackgroundColor = DeepPurple,
        unselectedBackgroundColor = Gray100,
        selectedTextColor = White,
        unselectedTextColor = Gray400,
        horizontalPadding = 11.dp,
        verticalPadding = 6.dp,
        cornerRadius = 10.dp,
        textStyle = defaultDateRoadTypography.bodyMed15
    )
}
