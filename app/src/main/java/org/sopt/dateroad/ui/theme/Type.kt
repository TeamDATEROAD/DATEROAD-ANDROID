package org.sopt.dateroad.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.sopt.dateroad.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

// Title
val titleExtra24 = TextStyle(
    fontFamily = FontFamily(Font(R.font.suit_extrabold)),
    fontSize = 24.sp,
    lineHeight = 24.sp * 1.3
)

val titleExtra20 = TextStyle(
    fontFamily = FontFamily(Font(R.font.suit_extrabold)),
    fontSize = 20.sp,
    lineHeight = 20.sp * 1.4
)

val titleBold20 = TextStyle(
    fontFamily = FontFamily(Font(R.font.suit_bold)),
    fontSize = 20.sp,
    lineHeight = 20.sp * 1.4
)

val titleBold18 = TextStyle(
    fontFamily = FontFamily(Font(R.font.suit_bold)),
    fontSize = 18.sp,
    lineHeight = 18.sp * 1.4
)

val titleMed18 = TextStyle(
    fontFamily = FontFamily(Font(R.font.suit_medium)),
    fontSize = 18.sp,
    lineHeight = 18.sp * 1.4
)

// Body
val bodyBold17 = TextStyle(
    fontFamily = FontFamily(Font(R.font.suit_bold)),
    fontSize = 17.sp,
    lineHeight = 17.sp * 1.4
)

val bodySemi17 = TextStyle(
    fontFamily = FontFamily(Font(R.font.suit_semibold)),
    fontSize = 17.sp,
    lineHeight = 17.sp * 1.4
)

val bodyMed17 = TextStyle(
    fontFamily = FontFamily(Font(R.font.suit_medium)),
    fontSize = 17.sp,
    lineHeight = 17.sp * 1.4
)

val bodyBold15 = TextStyle(
    fontFamily = FontFamily(Font(R.font.suit_bold)),
    fontSize = 15.sp,
    lineHeight = 15.sp * 1.4
)

val bodyBold15Course = TextStyle(
    fontFamily = FontFamily(Font(R.font.suit_bold)),
    fontSize = 15.sp,
    lineHeight = 15.sp * 1.3
)

val bodySemi15 = TextStyle(
    fontFamily = FontFamily(Font(R.font.suit_semibold)),
    fontSize = 15.sp,
    lineHeight = 15.sp * 1.4
)

val bodyMed15 = TextStyle(
    fontFamily = FontFamily(Font(R.font.suit_medium)),
    fontSize = 15.sp,
    lineHeight = 15.sp * 1.4
)

val bodyBold13 = TextStyle(
    fontFamily = FontFamily(Font(R.font.suit_bold)),
    fontSize = 13.sp,
    lineHeight = 13.sp * 1.4
)

val bodySemi13 = TextStyle(
    fontFamily = FontFamily(Font(R.font.suit_semibold)),
    fontSize = 13.sp,
    lineHeight = 13.sp * 1.4
)

val bodyMed13 = TextStyle(
    fontFamily = FontFamily(Font(R.font.suit_medium)),
    fontSize = 13.sp,
    lineHeight = 13.sp * 1.4
)

val bodyMed13Context = TextStyle(
    fontFamily = FontFamily(Font(R.font.suit_medium)),
    fontSize = 13.sp,
    lineHeight = 13.sp * 1.5
)

// Caption
val capBold11 = TextStyle(
    fontFamily = FontFamily(Font(R.font.suit_bold)),
    fontSize = 11.sp,
    lineHeight = 11.sp * 1.4
)

val capReg11 = TextStyle(
    fontFamily = FontFamily(Font(R.font.suit_regular)),
    fontSize = 11.sp,
    lineHeight = 11.sp * 1.4
)
