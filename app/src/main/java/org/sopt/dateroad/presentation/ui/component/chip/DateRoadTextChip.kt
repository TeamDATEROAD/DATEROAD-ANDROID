package org.sopt.dateroad.presentation.ui.component.chip

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.presentation.type.ChipType

@Composable
fun DateRoadTextChip(
    modifier: Modifier = Modifier,
    text: String,
    chipType: ChipType,
    isSelected: Boolean = false,
    onSelectedChange: (Boolean) -> Unit = {}
) {
    DateRoadChip(
        modifier = modifier,
        chipType = chipType,
        isSelected = isSelected,
        onSelectedChange = onSelectedChange
    ) { selected ->
        Text(
            text = text,
            style = chipType.textStyle,
            color = if (selected) chipType.selectedTextColor else chipType.unselectedTextColor
        )
    }
}

@Preview
@Composable
fun DateRoadTextChipPreview() {
    Column {
        DateRoadTextChip(text = "10만원 이상", chipType = ChipType.MONEY)
        Spacer(modifier = Modifier.height(10.dp))
        DateRoadTextChip(text = "식도락", chipType = ChipType.DATE)
        Spacer(modifier = Modifier.height(10.dp))
        DateRoadTextChip(text = "식도락", chipType = ChipType.DATE)
        Spacer(modifier = Modifier.height(10.dp))
        DateRoadTextChip(modifier = Modifier.fillMaxWidth(), text = "서울", chipType = ChipType.REGION)
        Spacer(modifier = Modifier.height(10.dp))
        DateRoadTextChip(modifier = Modifier.fillMaxWidth(), text = "수원", chipType = ChipType.AREA)
    }
}
