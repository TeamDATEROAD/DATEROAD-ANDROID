package org.sopt.dateroad.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

object DateRoadTheme {
    val colors: DateRoadColors
        @Composable
        @ReadOnlyComposable
        get() = LocalDateRoadColors.current

    val typography: DateRoadTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalDateRoadTypography.current
}

@Composable
fun provideDateRoadColorsAndTypography(
    colors: DateRoadColors,
    typography: DateRoadTypography,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalDateRoadColors provides colors,
        LocalDateRoadTypography provides typography,
        content = content
    )
}

@Composable
fun DATEROADTheme(
    content: @Composable () -> Unit
) {
    provideDateRoadColorsAndTypography(colors = defaultDateRoadColors, typography = defaultDateRoadTypography) {
        val view = LocalView.current
        if (!view.isInEditMode) {
            SideEffect {
                val window = (view.context as Activity).window
                window.statusBarColor = Color.Transparent.toArgb()
                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = true
            }
        }
        MaterialTheme(content = content)
    }
}
