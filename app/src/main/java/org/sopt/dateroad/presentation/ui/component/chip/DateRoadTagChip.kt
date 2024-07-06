package org.sopt.dateroad.presentation.ui.component.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
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
import org.sopt.dateroad.ui.theme.Black
import org.sopt.dateroad.ui.theme.DateRoadTheme
import org.sopt.dateroad.ui.theme.DeepPurple
import org.sopt.dateroad.ui.theme.Gray100
import org.sopt.dateroad.ui.theme.White

@Composable
fun DateRoadTagChip(
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
            style = DateRoadTheme.typography.bodySemi13,
            color = if (selected) White else Black,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
        )
    }
}

@Preview
@Composable
fun DateRoadTagChipPreview() {
    Column {
        DateRoadTagChip(R.string.date_tag_drive)
        Spacer(modifier = Modifier.height(10.dp))
        DateRoadTagChip(R.string.date_tag_alcohol)
        Spacer(modifier = Modifier.height(10.dp))
        DateRoadTagChip(R.string.date_tag_epicurism)
    }
}
