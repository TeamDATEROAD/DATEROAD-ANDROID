package org.sopt.teamdateroad.presentation.type

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.sopt.teamdateroad.ui.theme.Black
import org.sopt.teamdateroad.ui.theme.Gray100
import org.sopt.teamdateroad.ui.theme.Gray400
import org.sopt.teamdateroad.ui.theme.Purple600
import org.sopt.teamdateroad.ui.theme.White
import org.sopt.teamdateroad.ui.theme.defaultDateRoadTypography

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
    DATE(
        selectedBackgroundColor = Purple600,
        unselectedBackgroundColor = Gray100,
        selectedTextColor = White,
        unselectedTextColor = Black,
        horizontalPadding = 10.dp,
        verticalPadding = 5.dp,
        cornerRadius = 20.dp,
        textStyle = defaultDateRoadTypography.bodySemi13
    ),
    MONEY(
        selectedBackgroundColor = Purple600,
        unselectedBackgroundColor = Gray100,
        selectedTextColor = White,
        unselectedTextColor = Gray400,
        horizontalPadding = 8.dp,
        verticalPadding = 6.dp,
        cornerRadius = 20.dp,
        textStyle = defaultDateRoadTypography.bodyMed13
    ),
    AREA(
        selectedBackgroundColor = Purple600,
        unselectedBackgroundColor = Gray100,
        selectedTextColor = White,
        unselectedTextColor = Gray400,
        horizontalPadding = 14.dp,
        verticalPadding = 6.dp,
        cornerRadius = 10.dp,
        textStyle = defaultDateRoadTypography.bodyMed13
    ),
    REGION(
        selectedBackgroundColor = Purple600,
        unselectedBackgroundColor = Gray100,
        selectedTextColor = White,
        unselectedTextColor = Gray400,
        horizontalPadding = 11.dp,
        verticalPadding = 6.dp,
        cornerRadius = 10.dp,
        textStyle = defaultDateRoadTypography.bodySemi15
    )
}
