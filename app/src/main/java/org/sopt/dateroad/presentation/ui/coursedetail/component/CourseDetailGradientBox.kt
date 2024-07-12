package org.sopt.dateroad.presentation.ui.coursedetail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun GradientBoxWithText(text: String) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = text,
            style = DateRoadTheme.typography.bodyMed13Context,
            color = DateRoadTheme.colors.black,
            maxLines = 3,
            modifier = Modifier.fillMaxWidth()
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.White.copy(alpha = 0.6f),
                            Color.White.copy(alpha = 1f)

                        )
                    )
                )
                .matchParentSize()
        )
    }
}