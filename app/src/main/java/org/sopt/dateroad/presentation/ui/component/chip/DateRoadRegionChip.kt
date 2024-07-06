package org.sopt.dateroad.presentation.ui.component.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.R
import org.sopt.dateroad.ui.theme.DateRoadTheme
import org.sopt.dateroad.ui.theme.DeepPurple
import org.sopt.dateroad.ui.theme.Gray100
import org.sopt.dateroad.ui.theme.Gray400
import org.sopt.dateroad.ui.theme.White

@Composable
fun DateRoadRegionChip(
    textId: Int, 
    isSelected: Boolean = false,
    onSelectedChange: (Boolean) -> Unit = {}
) {
    var selected by remember { mutableStateOf(isSelected) }

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .background(color = if (selected) DeepPurple else Gray100)
            .clickable {
                selected = !selected
                onSelectedChange(selected)
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = textId),
            style = DateRoadTheme.typography.bodyMed13,
            color = if (selected) White else Gray400,
            modifier = Modifier.padding(vertical = 6.dp)
        )
    }
}

@Preview
@Composable
fun DateRoadRegionChipPreview() {
    Column {
        DateRoadRegionChip(R.string.region_Seoul)
    }
}
