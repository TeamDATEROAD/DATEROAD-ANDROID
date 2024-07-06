package org.sopt.dateroad.presentation.ui.component.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.R
import org.sopt.dateroad.presentation.util.modifier.noRippleClickable
import org.sopt.dateroad.ui.theme.DateRoadTheme
import org.sopt.dateroad.ui.theme.DeepPurple
import org.sopt.dateroad.ui.theme.Gray100
import org.sopt.dateroad.ui.theme.Gray400
import org.sopt.dateroad.ui.theme.White

@Composable
fun DateRoadAreaChip(
    textId: Int,
    isSelected: Boolean = false,
    onSelectedChange: (Boolean) -> Unit = {}
) {
    var selected by remember { mutableStateOf(isSelected) }

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(color = if (selected) DeepPurple else Gray100)
            .noRippleClickable {
                selected = !selected
                onSelectedChange(selected)
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = textId),
            style = DateRoadTheme.typography.bodyMed13,
            color = if (selected) White else Gray400,
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp)
        )
    }
}

@Preview
@Composable
fun DateRoadAreaChipPreview() {
    Column {
        DateRoadAreaChip(R.string.gyeonggi_area_anyang_gwacheon)
    }
}
