package org.sopt.dateroad.presentation.ui.component.chip

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
fun DateRoadEnrollCourseTagChip(
    textId: Int,
    chipType: ChipType = ChipType.ENROLL_COURSE,
    isSelected: Boolean = false,
    onSelectedChange: (Boolean) -> Unit = {}
) {
    DateRoadChip(
        modifier = Modifier,
        selectedBackgroundColor = chipType.selectedBackgroundColor,
        unselectedBackgroundColor = chipType.unselectedBackgroundColor,
        selectedTextColor = chipType.selectedTextColor,
        unselectedTextColor = chipType.unselectedTextColor,
        textStyle = chipType.textStyle,
        horizontalPadding = chipType.horizontalPadding,
        verticalPadding = chipType.verticalPadding,
        cornerRadius = chipType.cornerRadius,
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
fun DateRoadEnrollCourseTagChipPreview() {
    Column {
        DateRoadEnrollCourseTagChip(R.string.date_tag_drive)
        Spacer(modifier = Modifier.height(10.dp))
        DateRoadEnrollCourseTagChip(R.string.date_tag_alcohol)
        Spacer(modifier = Modifier.height(10.dp))
        DateRoadEnrollCourseTagChip(R.string.date_tag_epicurism)
    }
}
