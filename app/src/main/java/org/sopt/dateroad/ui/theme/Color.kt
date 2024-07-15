package org.sopt.dateroad.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

// Main
val Purple100 = Color(0xFFD3D0FF)
val Purple200 = Color(0xFFBDB8FF)
val Purple300 = Color(0xFFB3ACFF)
val Purple400 = Color(0xFFA8A0FF)
val Purple500 = Color(0xFF6E61FF)
val Purple600 = Color(0xFF4A3CEF)
val Purple700 = Color(0xFF4322BB)

// Sub
val Pink100 = Color(0xFFEFD6F8)
val Pink200 = Color(0xFFE1C2F9)
val Pink300 = Color(0xFFE1B7F0)
val Pink400 = Color(0xFFE3A3F9)
val Lime100 = Color(0xFFEFFDB7)
val Lime200 = Color(0xFFDFF37C)
val Lime300 = Color(0xFFD3EB77)

// GrayScale
val Black = Color(0xFF090909)
val Gray100 = Color(0xFFEBEBF3)
val Gray200 = Color(0xFFD5D5DE)
val Gray300 = Color(0xFFAEAFBC)
val Gray400 = Color(0xFF7B7C87)
val Gray500 = Color(0xFF53525B)
val Gray600 = Color(0xFF080909)
val White = Color(0xFFFFFFFF)

// Notif
val AlertRed = Color(0xFFFF0000)

// Kakao
val KakaoYellow = Color(0xFFFEE500)

@Immutable
data class DateRoadColors(
    // Main
    val purple100: Color,
    val purple200: Color,
    val purple300: Color,
    val purple400: Color,
    val purple500: Color,
    val purple600: Color,
    val purple700: Color,
    val pink100: Color,
    val pink200: Color,
    val pink300: Color,
    val pink400: Color,
    val lime100: Color,
    val lime200: Color,
    val lime300: Color,

    // GrayScale
    val black: Color,
    val gray100: Color,
    val gray200: Color,
    val gray300: Color,
    val gray400: Color,
    val gray500: Color,
    val gray600: Color,
    val white: Color,

    // Notif
    val alertRed: Color,

    // Kakao
    val kakaoYellow: Color,
)

val defaultDateRoadColors = DateRoadColors(
    // Main
    purple100 = Purple100,
    purple200 = Purple200,
    purple300 = Purple300,
    purple400 = Purple400,
    purple500 = Purple500,
    purple600 = Purple600,
    purple700 = Purple700,
    pink100 = Pink100,
    pink200 = Pink200,
    pink300 = Pink300,
    pink400 = Pink400,
    lime100 = Lime100,
    lime200 = Lime200,
    lime300 = Lime300,

    // GrayScale
    black = Black,
    gray100 = Gray100,
    gray200 = Gray200,
    gray300 = Gray300,
    gray400 = Gray400,
    gray500 = Gray500,
    gray600 = Gray600,
    white = White,

    // Notif
    alertRed = AlertRed,

    // Kakao
    kakaoYellow = KakaoYellow,
)

val LocalDateRoadColors = staticCompositionLocalOf { defaultDateRoadColors }
