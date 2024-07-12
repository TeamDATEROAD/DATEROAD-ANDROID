package org.sopt.dateroad.presentation.ui.component.bottomsheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.R
import org.sopt.dateroad.presentation.type.ChipType
import org.sopt.dateroad.presentation.type.GyeonggiAreaType
import org.sopt.dateroad.presentation.type.IncheonAreaType
import org.sopt.dateroad.presentation.type.RegionType
import org.sopt.dateroad.presentation.type.SeoulAreaType
import org.sopt.dateroad.presentation.ui.component.chip.DateRoadTextChip
import org.sopt.dateroad.presentation.util.modifier.noRippleClickable
import org.sopt.dateroad.ui.theme.DateRoadTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun DateRoadRegionBottomSheet(
    isBottomSheetOpen: Boolean,
    isButtonEnabled: Boolean,
    titleText: String = stringResource(id = R.string.region_bottom_sheet_title),
    buttonText: String = stringResource(id = R.string.apply),
    selectedRegion: RegionType? = null,
    onSelectedRegionChanged: (RegionType) -> Unit = {},
    selectedArea: Any? = null,
    onSelectedAreaChanged: (Any) -> Unit = {},
    onButtonClick: (RegionType?, Any?) -> Unit = { _, _ -> },
    onDismissRequest: () -> Unit = {}
) {
    var contentHeight by remember { mutableStateOf(0) }
    val scrollState = rememberScrollState()

    DateRoadBottomSheet(
        modifier = Modifier.padding(top = 15.dp, start = 16.dp, end = 6.dp, bottom = 16.dp),
        isBottomSheetOpen = isBottomSheetOpen,
        isButtonEnabled = isButtonEnabled,
        buttonText = buttonText,
        onButtonClick = { onButtonClick(selectedRegion, selectedArea) },
        onDismissRequest = onDismissRequest
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = titleText,
                    color = DateRoadTheme.colors.black,
                    style = DateRoadTheme.typography.bodyBold17
                )
                Image(
                    modifier = Modifier
                        .padding(15.dp)
                        .noRippleClickable(onClick = onDismissRequest),
                    painter = painterResource(id = R.drawable.ic_bottom_sheet_close),
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.height(29.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 10.dp)
            ) {
                RegionType.entries.forEachIndexed { index, regionType ->
                    val isSelected = selectedRegion == regionType
                    DateRoadTextChip(
                        modifier = Modifier.weight(1f),
                        textId = regionType.nameRes,
                        chipType = ChipType.REGION,
                        isSelected = isSelected,
                        onSelectedChange = {
                            onSelectedRegionChanged(regionType)
                        }
                    )
                    if (index != RegionType.entries.size - 1) {
                        Spacer(modifier = Modifier.width(10.dp))
                    }
                }
            }
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 18.dp, start = 2.dp, end = 2.dp, bottom = 21.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 180.dp, min = 180.dp)
                    .padding(end = 10.dp)
                    .let { if (contentHeight > 180) it.verticalScroll(scrollState) else it }
                    .onGloballyPositioned { coordinates ->
                        contentHeight = coordinates.size.height
                    }
            ) {
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(9.dp),
                    verticalArrangement = Arrangement.spacedBy(11.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    when (selectedRegion) {
                        RegionType.INCHEON -> IncheonAreaType.entries.forEach { areaType ->
                            DateRoadTextChip(
                                textId = areaType.nameRes,
                                chipType = ChipType.AREA,
                                isSelected = selectedArea == areaType,
                                onSelectedChange = {
                                    onSelectedAreaChanged(areaType)
                                }
                            )
                        }

                        RegionType.GYEONGGI -> GyeonggiAreaType.entries.forEach { areaType ->
                            DateRoadTextChip(
                                textId = areaType.nameRes,
                                chipType = ChipType.AREA,
                                isSelected = selectedArea == areaType,
                                onSelectedChange = {
                                    onSelectedAreaChanged(areaType)
                                }
                            )
                        }

                        else -> SeoulAreaType.entries.forEach { areaType ->
                            DateRoadTextChip(
                                textId = areaType.nameRes,
                                chipType = ChipType.AREA,
                                isSelected = selectedArea == areaType,
                                onSelectedChange = {
                                    onSelectedAreaChanged(areaType)
                                }
                            )
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Preview
@Composable
fun DateRoadRegionBottomSheetPreview() {
    var isBottomSheetOpen by rememberSaveable { mutableStateOf(false) }
    var selectedRegion by rememberSaveable { mutableStateOf<RegionType?>(null) }
    var selectedArea by rememberSaveable { mutableStateOf<Any?>(null) }

    Button(onClick = { isBottomSheetOpen = true }) {
        Text(
            text = "RegionBottomSheet",
            color = DateRoadTheme.colors.black,
            style = DateRoadTheme.typography.titleExtra24
        )
    }

    DateRoadRegionBottomSheet(
        isBottomSheetOpen = isBottomSheetOpen,
        isButtonEnabled = selectedRegion != null && selectedArea != null,
        selectedRegion = selectedRegion,
        selectedArea = selectedArea,
        onSelectedRegionChanged = { newSelectedRegion ->
            selectedRegion = newSelectedRegion
            selectedArea = null
        },
        onSelectedAreaChanged = { newSelectedArea ->
            selectedArea = newSelectedArea
        },
        onDismissRequest = { isBottomSheetOpen = !isBottomSheetOpen }
    )
}
