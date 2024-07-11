package org.sopt.dateroad.presentation.ui.enroll

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.R
import org.sopt.dateroad.presentation.type.DateChipGroupType
import org.sopt.dateroad.presentation.type.DateTagType
import org.sopt.dateroad.presentation.type.GyeonggiAreaType
import org.sopt.dateroad.presentation.type.IncheonAreaType
import org.sopt.dateroad.presentation.type.RegionType
import org.sopt.dateroad.presentation.type.SeoulAreaType
import org.sopt.dateroad.presentation.ui.component.chipgroup.DateRoadDateChipGroup
import org.sopt.dateroad.presentation.ui.component.textfield.DateRoadBasicTextField
import org.sopt.dateroad.presentation.util.view.LoadState
import org.sopt.dateroad.ui.theme.DATEROADTheme

@Composable
fun EnrollFirstScreen(
    enrollUiState: EnrollContract.EnrollUiState = EnrollContract.EnrollUiState(),
    onTitleValueChange: (String) -> Unit,
    onDatePickerBottomSheetButtonClicked: (String) -> Unit,
    onTimePickerBottomSheetButtonClicked: (String) -> Unit,
    onDateChipClicked: (DateTagType) -> Unit,
    onRegionBottomSheetRegionChipClicked: (RegionType) -> Unit,
    onRegionBottomSheetAreaChipClicked: (Any?) -> Unit,
) {
    Column {
        DateRoadBasicTextField(
            placeholder = stringResource(id = R.string.enroll_title_placeholder),
            value = enrollUiState.title,
            onValueChange = onTitleValueChange
        )
        Spacer(modifier = Modifier.height(18.dp))
        DateRoadBasicTextField(
            placeholder = stringResource(id = R.string.enroll_date_placeholder),
            value = enrollUiState.date,
            readOnly = true,
            iconResourceId = R.drawable.ic_enroll_calendar,
        )
        Spacer(modifier = Modifier.height(18.dp))
        DateRoadBasicTextField(
            placeholder = stringResource(id = R.string.enroll_date_start_at),
            value = enrollUiState.startAt,
            readOnly = true,
            iconResourceId = R.drawable.ic_enroll_time
        )
        Spacer(modifier = Modifier.height(20.dp))
        DateRoadDateChipGroup(
            dateChipGroupType = DateChipGroupType.ENROLL,
            selectedDateTags = enrollUiState.tags,
            onSelectedDateTagsChanged = onDateChipClicked
        )
        Spacer(modifier = Modifier.height(20.dp))
        DateRoadBasicTextField(
            placeholder = stringResource(id = R.string.enroll_city_placeholder),
            value = when (enrollUiState.city) {
                is SeoulAreaType -> stringResource(id = enrollUiState.city.nameRes)
                is GyeonggiAreaType -> stringResource(id = enrollUiState.city.nameRes)
                is IncheonAreaType -> stringResource(id = enrollUiState.city.nameRes)
                else -> ""
            }
        )
    }
}

@Preview
@Composable
fun EnrollFirstScreenPreview() {
    DATEROADTheme {
        EnrollFirstScreen(
            enrollUiState = EnrollContract.EnrollUiState(
                loadState = LoadState.Success
            ),
            onTitleValueChange = {},
            onDatePickerBottomSheetButtonClicked = {},
            onTimePickerBottomSheetButtonClicked = {},
            onDateChipClicked = {},
            onRegionBottomSheetRegionChipClicked = {},
            onRegionBottomSheetAreaChipClicked = {},
        )
    }
}