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
import org.sopt.dateroad.presentation.type.ChipType
import org.sopt.dateroad.presentation.util.modifier.noRippleClickable

@Composable
fun DateRoadChip(
    modifier: Modifier = Modifier,
    chipType: ChipType = ChipType.ENROLL_COURSE,
    isSelected: Boolean = false,
    onSelectedChange: (Boolean) -> Unit = {},
    content: @Composable (Boolean) -> Unit
) {
    var selected by remember { mutableStateOf(isSelected) }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(chipType.cornerRadius))
            .background(color = if (selected) chipType.selectedBackgroundColor else chipType.unselectedBackgroundColor)
            .noRippleClickable {
                selected = !selected
                onSelectedChange(selected)
            }
            .padding(horizontal = chipType.horizontalPadding, vertical = chipType.verticalPadding),
        contentAlignment = Alignment.Center
    ) {
        content(selected)
    }
}
