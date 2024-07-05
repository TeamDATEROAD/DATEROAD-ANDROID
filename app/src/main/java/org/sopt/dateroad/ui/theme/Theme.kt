package org.sopt.dateroad.ui.theme

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
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
fun ProvideDateRoadColorsAndTypography(
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
    backgroundColor: Color = defaultDateRoadColors.white,
    content: @Composable () -> Unit
) {
    ProvideDateRoadColorsAndTypography(colors = defaultDateRoadColors, typography = defaultDateRoadTypography) {
        val view = LocalView.current
        if (!view.isInEditMode) {
            SideEffect {
                val window = (view.context as Activity).window
                window.statusBarColor = Color.Transparent.toArgb()
                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = true
            }
        }

        androidx.compose.material3.Surface(
            modifier = Modifier.background(backgroundColor)
        ) {
            MaterialTheme(content = content)
        }
    }
}
