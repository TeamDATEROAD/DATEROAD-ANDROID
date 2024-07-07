package org.sopt.dateroad.presentation.ui.component.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import org.sopt.dateroad.presentation.util.modifier.noRippleClickable

@Composable
fun DateRoadChip(
    modifier: Modifier = Modifier,
    selectedBackgroundColor: Color,
    unselectedBackgroundColor: Color,
    selectedTextColor: Color,
    unselectedTextColor: Color,
    textStyle: TextStyle,
    horizontalPadding: Dp,
    verticalPadding: Dp,
    cornerRadius: Dp,
    isSelected: Boolean = false,
    onSelectedChange: (Boolean) -> Unit = {},
    content: @Composable (Boolean) -> Unit
) {
    var selected by remember { mutableStateOf(isSelected) }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(cornerRadius))
            .background(color = if (selected) selectedBackgroundColor else unselectedBackgroundColor)
            .noRippleClickable {
                selected = !selected
                onSelectedChange(selected)
            }
            .padding(horizontal = horizontalPadding, vertical = verticalPadding),
        contentAlignment = Alignment.Center
    ) {
        content(selected)
    }
}
