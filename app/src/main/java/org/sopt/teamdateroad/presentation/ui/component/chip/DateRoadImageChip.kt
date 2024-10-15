package org.sopt.teamdateroad.presentation.ui.component.chip

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.sopt.teamdateroad.presentation.type.ChipType
import org.sopt.teamdateroad.presentation.type.DateTagType

@Composable
fun DateRoadImageChip(
    modifier: Modifier = Modifier,
    @StringRes textId: Int,
    @DrawableRes imageRes: Int,
    chipType: ChipType,
    spaceValue: Dp = 2.dp,
    isSelected: Boolean = false,
    onSelectedChange: (Boolean) -> Unit = {}
) {
    DateRoadChip(
        modifier = modifier,
        chipType = chipType,
        isSelected = isSelected,
        onSelectedChange = onSelectedChange
    ) { selected ->
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null
            )
            Spacer(modifier = Modifier.size(spaceValue))
            Text(
                text = stringResource(id = textId),
                style = chipType.textStyle,
                color = if (selected) chipType.selectedTextColor else chipType.unselectedTextColor
            )
        }
    }
}

@Preview
@Composable
fun DateRoadImageChipPreview() {
    DateRoadImageChip(
        textId = DateTagType.EXHIBITION_POPUP.titleRes,
        imageRes = DateTagType.EXHIBITION_POPUP.imageRes,
        chipType = ChipType.DATE
    )
}
