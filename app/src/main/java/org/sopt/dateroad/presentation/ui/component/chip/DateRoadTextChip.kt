package org.sopt.dateroad.presentation.ui.component.chip

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.R
import org.sopt.dateroad.presentation.type.ChipType

@Composable
fun DateRoadTextChip(
    modifier: Modifier = Modifier,
    @StringRes textId: Int,
    chipType: ChipType = ChipType.ENROLL_COURSE,
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
            text = stringResource(id = textId),
            style = chipType.textStyle,
            color = if (selected) chipType.selectedTextColor else chipType.unselectedTextColor
        )
    }
}

@Preview
@Composable
fun DateRoadChipPreview() {
    Column {
        DateRoadTextChip(textId = R.string.money_tag_less_than_100000, chipType = ChipType.MONEY)
        Spacer(modifier = Modifier.height(10.dp))
        DateRoadTextChip(textId = R.string.date_tag_epicurism, chipType = ChipType.MY_PROFILE)
        Spacer(modifier = Modifier.height(10.dp))
        DateRoadTextChip(textId = R.string.date_tag_epicurism, chipType = ChipType.ENROLL_COURSE)
        Spacer(modifier = Modifier.height(10.dp))
        DateRoadTextChip(modifier = Modifier.fillMaxWidth(), textId = R.string.region_Seoul, chipType = ChipType.REGION)
        Spacer(modifier = Modifier.height(10.dp))
        DateRoadTextChip(modifier = Modifier.fillMaxWidth(), textId = R.string.gyeonggi_area_suwon, chipType = ChipType.AREA)
    }
}
