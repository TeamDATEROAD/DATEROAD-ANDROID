package org.sopt.dateroad.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

// Main
val DeepPurple = Color(0xFF4A3CEF)
val MediumPurple = Color(0xFF6E61FF)
val LightPurple = Color(0xFFA8A0FF)
val Lilac = Color(0xFFD5B5EE)
val LightPink = Color(0xFFEFD6F8)
val Pink = Color(0xFFE3A3F9)
val Lime = Color(0xFFDFF37C)
val LightLime = Color(0xFFE5F3AE)

// GrayScale
val Black = Color(0xFF090909)
val Gray600 = Color(0xFF080909)
val Gray500 = Color(0xFF53525B)
val Gray400 = Color(0xFF7B7C87)
val Gray300 = Color(0xFFAEAFBC)
val Gray200 = Color(0xFFD5D5DE)
val Gray100 = Color(0xFFEBEBF3)
val White = Color(0xFFFFFFFF)

// Notif
val AlertRed = Color(0xFFFF0000)

// kakao
val Kakao = Color(0xFFFEE500)

@Immutable
data class DateRoadColors(
    // Main
    val deepPurple: Color,
    val mediumPurple: Color,
    val lightPurple: Color,
    val lilac: Color,
    val lightPink: Color,
    val pink: Color,
    val lime: Color,
    val lightLime: Color,

    // GrayScale
    val black: Color,
    val gray600: Color,
    val gray500: Color,
    val gray400: Color,
    val gray300: Color,
    val gray200: Color,
    val gray100: Color,
    val white: Color,

    // Notif
    val alertRed: Color,
    val kakao: Color
)

val defaultDateRoadColors = DateRoadColors(
    // Main
    deepPurple = DeepPurple,
    mediumPurple = MediumPurple,
    lightPurple = LightPurple,
    lilac = Lilac,
    lightPink = LightPink,
    pink = Pink,
    lime = Lime,
    lightLime = LightLime,

    // GrayScale
    black = Black,
    gray600 = Gray600,
    gray500 = Gray500,
    gray400 = Gray400,
    gray300 = Gray300,
    gray200 = Gray200,
    gray100 = Gray100,
    white = White,

    // Notif
    alertRed = AlertRed,
    kakao = Kakao
)

val LocalDateRoadColors = staticCompositionLocalOf { defaultDateRoadColors }
