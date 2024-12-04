package org.sopt.teamdateroad.presentation.type

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import org.sopt.teamdateroad.ui.theme.defaultDateRoadColors
import org.sopt.teamdateroad.ui.theme.defaultDateRoadTypography

enum class TagType(
    val backgroundColor: Color,
    val contentColor: Color,
    val paddingHorizontal: Int,
    val paddingVertical: Int,
    val textStyle: TextStyle,
    val roundedCornerShape: Int
) {
    ADVERTISEMENT_TITLE(
        backgroundColor = defaultDateRoadColors.purple500,
        contentColor = defaultDateRoadColors.white,
        paddingHorizontal = 10,
        paddingVertical = 2,
        textStyle = defaultDateRoadTypography.bodySemi13,
        roundedCornerShape = 12
    ),
    COURSE_DETAIL_PHOTO_NUMBER(
        backgroundColor = defaultDateRoadColors.gray400,
        contentColor = defaultDateRoadColors.white,
        paddingHorizontal = 14,
        paddingVertical = 2,
        textStyle = defaultDateRoadTypography.bodyMed13,
        roundedCornerShape = 12
    ),
    TIMELINE_D_DAY(
        backgroundColor = defaultDateRoadColors.purple600,
        contentColor = defaultDateRoadColors.white,
        paddingHorizontal = 10,
        paddingVertical = 2,
        textStyle = defaultDateRoadTypography.capBold11,
        roundedCornerShape = 20
    ),
    ENROLL_PHOTO_NUMBER(
        backgroundColor = defaultDateRoadColors.gray400,
        contentColor = defaultDateRoadColors.white,
        paddingHorizontal = 10,
        paddingVertical = 4,
        textStyle = defaultDateRoadTypography.capReg11,
        roundedCornerShape = 20
    ),
    HEART(
        backgroundColor = defaultDateRoadColors.purple600,
        contentColor = defaultDateRoadColors.white,
        paddingHorizontal = 10,
        paddingVertical = 2,
        textStyle = defaultDateRoadTypography.bodyBold13,
        roundedCornerShape = 12
    ),
    TIME(
        backgroundColor = defaultDateRoadColors.gray100,
        contentColor = defaultDateRoadColors.gray400,
        paddingHorizontal = 10,
        paddingVertical = 4,
        textStyle = defaultDateRoadTypography.bodyMed13,
        roundedCornerShape = 20
    ),
    MONEY(
        backgroundColor = defaultDateRoadColors.gray100,
        contentColor = defaultDateRoadColors.gray400,
        paddingHorizontal = 10,
        paddingVertical = 4,
        textStyle = defaultDateRoadTypography.bodyMed13,
        roundedCornerShape = 20
    ),
    MY_PAGE_DATE(
        backgroundColor = defaultDateRoadColors.white,
        contentColor = defaultDateRoadColors.black,
        paddingHorizontal = 14,
        paddingVertical = 6,
        textStyle = defaultDateRoadTypography.bodyMed13,
        roundedCornerShape = 20
    ),
    ADVERTISEMENT_PAGE_NUMBER(
        backgroundColor = defaultDateRoadColors.gray400,
        contentColor = defaultDateRoadColors.white,
        paddingHorizontal = 9,
        paddingVertical = 1,
        textStyle = defaultDateRoadTypography.capReg11,
        roundedCornerShape = 20
    ),
    PAST_DATE(
        backgroundColor = defaultDateRoadColors.pink100,
        contentColor = defaultDateRoadColors.black,
        paddingHorizontal = 14,
        paddingVertical = 6,
        textStyle = defaultDateRoadTypography.bodyMed13,
        roundedCornerShape = 20
    ),
    PLACE_CARD_NUMBER(
        backgroundColor = defaultDateRoadColors.purple600,
        contentColor = defaultDateRoadColors.white,
        paddingHorizontal = 9,
        paddingVertical = 4,
        textStyle = defaultDateRoadTypography.bodyBold13,
        roundedCornerShape = 50
    ),
    PLACE_CARD_TIME(
        backgroundColor = defaultDateRoadColors.gray200,
        contentColor = defaultDateRoadColors.black,
        paddingHorizontal = 14,
        paddingVertical = 5,
        textStyle = defaultDateRoadTypography.bodyMed13,
        roundedCornerShape = 10
    ),
    TIMELINE_DATE_LIME(
        backgroundColor = defaultDateRoadColors.lime100,
        contentColor = defaultDateRoadColors.black,
        paddingHorizontal = 10,
        paddingVertical = 4,
        textStyle = defaultDateRoadTypography.bodyMed13,
        roundedCornerShape = 20
    ),
    TIMELINE_DATE_PINK(
        backgroundColor = defaultDateRoadColors.pink100,
        contentColor = defaultDateRoadColors.black,
        paddingHorizontal = 10,
        paddingVertical = 4,
        textStyle = defaultDateRoadTypography.bodyMed13,
        roundedCornerShape = 20
    ),
    TIMELINE_DATE_PURPLE(
        backgroundColor = defaultDateRoadColors.purple100,
        contentColor = defaultDateRoadColors.black,
        paddingHorizontal = 10,
        paddingVertical = 4,
        textStyle = defaultDateRoadTypography.bodyMed13,
        roundedCornerShape = 20
    )
}
