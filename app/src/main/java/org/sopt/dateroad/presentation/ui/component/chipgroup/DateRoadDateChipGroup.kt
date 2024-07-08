package org.sopt.dateroad.presentation.ui.component.chipgroup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.presentation.type.ChipType
import org.sopt.dateroad.presentation.type.DateChipGroupType
import org.sopt.dateroad.presentation.type.DateTagType
import org.sopt.dateroad.presentation.ui.component.chip.DateRoadTextChip
import org.sopt.dateroad.ui.theme.DATEROADTheme
import org.sopt.dateroad.ui.theme.DateRoadTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DateRoadDateChipGroup(
    modifier: Modifier = Modifier,
    dateChipGroupType: DateChipGroupType,
    selectedDateTags: List<DateTagType>,
    onSelectedDateTagsChanged: (DateTagType) -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = dateChipGroupType.titleRes, selectedDateTags.size, dateChipGroupType.maxSize),
            color = DateRoadTheme.colors.black,
            style = dateChipGroupType.titleTextStyle
        )
        Spacer(modifier = Modifier.height(10.dp))
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(7.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            DateTagType.entries.forEach { dateTagType: DateTagType ->
                DateRoadTextChip(
                    textId = dateTagType.titleRes,
                    chipType = ChipType.ENROLL_COURSE,
                    isSelected = selectedDateTags.contains(dateTagType),
                    onSelectedChange = {
                        onSelectedDateTagsChanged(dateTagType)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun DateRoadDateChipGroupPreview() {
    DATEROADTheme {
        var selectedDateTags by rememberSaveable { mutableStateOf<List<DateTagType>>(emptyList()) }

        DateRoadDateChipGroup(
            dateChipGroupType = DateChipGroupType.PROFILE,
            selectedDateTags = selectedDateTags,
            onSelectedDateTagsChanged = { selectedDateTag ->
                when {
                    selectedDateTags.contains(selectedDateTag) -> selectedDateTags -= selectedDateTag
                    selectedDateTags.size < DateChipGroupType.PROFILE.maxSize -> selectedDateTags += selectedDateTag
                }
            }
        )
    }
}
