package org.sopt.dateroad.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import org.sopt.dateroad.R

val SuitBold = FontFamily(Font(R.font.suit_bold))
val SuitExtraBold = FontFamily(Font(R.font.suit_extrabold))
val SuitExtraLight = FontFamily(Font(R.font.suit_extralight))
val SuitMedium = FontFamily(Font(R.font.suit_medium))
val SuitRegular = FontFamily(Font(R.font.suit_regular))
val SuitSemiBold = FontFamily(Font(R.font.suit_semibold))

@Immutable
data class DateRoadTypography(
    // Title
    val titleExtra24: TextStyle,
    val titleExtra20: TextStyle,
    val titleBold20: TextStyle,
    val titleBold18: TextStyle,
    val titleMed18: TextStyle,

    // Body
    val bodyBold17: TextStyle,
    val bodySemi17: TextStyle,
    val bodyMed17: TextStyle,
    val bodyBold15: TextStyle,
    val bodyBold15Course: TextStyle,
    val bodySemi15: TextStyle,
    val bodyMed15: TextStyle,
    val bodyBold13: TextStyle,
    val bodySemi13: TextStyle,
    val bodyMed13: TextStyle,
    val bodyMed13Context: TextStyle,

    // Caption
    val capBold11: TextStyle,
    val capReg11: TextStyle,
)

val defaultDateRoadTypography = DateRoadTypography(
    // Title
    titleExtra24 = TextStyle(
        fontFamily = SuitExtraBold,
        fontSize = 24.sp,
        lineHeight = 24.sp * 1.3
    ),
    titleExtra20 = TextStyle(
        fontFamily = SuitExtraBold,
        fontSize = 20.sp,
        lineHeight = 20.sp * 1.4
    ),
    titleBold20 = TextStyle(
        fontFamily = SuitBold,
        fontSize = 20.sp,
        lineHeight = 20.sp * 1.4
    ),
    titleBold18 = TextStyle(
        fontFamily = SuitBold,
        fontSize = 18.sp,
        lineHeight = 18.sp * 1.4
    ),
    titleMed18 = TextStyle(
        fontFamily = SuitMedium,
        fontSize = 18.sp,
        lineHeight = 18.sp * 1.4
    ),

    // Body
    bodyBold17 = TextStyle(
        fontFamily = SuitBold,
        fontSize = 17.sp,
        lineHeight = 17.sp * 1.4
    ),
    bodySemi17 = TextStyle(
        fontFamily = SuitSemiBold,
        fontSize = 17.sp,
        lineHeight = 17.sp * 1.4
    ),
    bodyMed17 = TextStyle(
        fontFamily = SuitMedium,
        fontSize = 17.sp,
        lineHeight = 17.sp * 1.4
    ),
    bodyBold15 = TextStyle(
        fontFamily = SuitBold,
        fontSize = 15.sp,
        lineHeight = 15.sp * 1.4
    ),
    bodyBold15Course = TextStyle(
        fontFamily = SuitBold,
        fontSize = 15.sp,
        lineHeight = 15.sp * 1.3
    ),
    bodySemi15 = TextStyle(
        fontFamily = SuitSemiBold,
        fontSize = 15.sp,
        lineHeight = 15.sp * 1.4
    ),
    bodyMed15 = TextStyle(
        fontFamily = SuitMedium,
        fontSize = 15.sp,
        lineHeight = 15.sp * 1.4
    ),
    bodyBold13 = TextStyle(
        fontFamily = SuitBold,
        fontSize = 13.sp,
        lineHeight = 13.sp * 1.4
    ),
    bodySemi13 = TextStyle(
        fontFamily = SuitSemiBold,
        fontSize = 13.sp,
        lineHeight = 13.sp * 1.4
    ),
    bodyMed13 = TextStyle(
        fontFamily = SuitMedium,
        fontSize = 13.sp,
        lineHeight = 13.sp * 1.4
    ),
    bodyMed13Context = TextStyle(
        fontFamily = SuitMedium,
        fontSize = 13.sp,
        lineHeight = 13.sp * 1.5
    ),

    // Caption
    capBold11 = TextStyle(
        fontFamily = SuitBold,
        fontSize = 11.sp,
        lineHeight = 11.sp * 1.4
    ),
    capReg11 = TextStyle(
        fontFamily = SuitRegular,
        fontSize = 11.sp,
        lineHeight = 11.sp * 1.4
    )
)

val localDateRoadTypography = staticCompositionLocalOf { defaultDateRoadTypography }
