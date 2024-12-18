package org.sopt.teamdateroad.presentation.ui.component.dotsindicator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.sopt.teamdateroad.ui.theme.DateRoadTheme

@Composable
fun DotsIndicator(totalDots: Int, selectedIndex: Int, modifier: Modifier = Modifier, indicatorSize: Dp) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        for (i in 0 until totalDots) {
            val color = if (i == selectedIndex) DateRoadTheme.colors.purple600 else DateRoadTheme.colors.gray200
            Box(
                modifier = Modifier
                    .size(indicatorSize)
                    .background(color, CircleShape)
            )
        }
    }
}
