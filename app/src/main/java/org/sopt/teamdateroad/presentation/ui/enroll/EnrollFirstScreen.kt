package org.sopt.teamdateroad.presentation.ui.enroll

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.teamdateroad.R
import org.sopt.teamdateroad.domain.type.GyeonggiAreaType
import org.sopt.teamdateroad.domain.type.IncheonAreaType
import org.sopt.teamdateroad.domain.type.SeoulAreaType
import org.sopt.teamdateroad.presentation.type.DateChipGroupType
import org.sopt.teamdateroad.presentation.type.DateTagType
import org.sopt.teamdateroad.presentation.type.DateTagType.Companion.getDateTagTypeByName
import org.sopt.teamdateroad.presentation.ui.component.chipgroup.DateRoadDateChipGroup
import org.sopt.teamdateroad.presentation.ui.component.textfield.DateRoadBasicTextField
import org.sopt.teamdateroad.presentation.util.view.LoadState
import org.sopt.teamdateroad.ui.theme.DATEROADTheme

@Composable
fun EnrollFirstScreen(
    enrollUiState: EnrollContract.EnrollUiState = EnrollContract.EnrollUiState(),
    onDateTextFieldClick: () -> Unit,
    onTimeTextFieldClick: () -> Unit,
    onRegionTextFieldClick: () -> Unit,
    onTitleValueChange: (String) -> Unit,
    onDateChipClicked: (DateTagType) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(14.dp))
        DateRoadBasicTextField(
            placeholder = stringResource(id = R.string.enroll_title_placeholder),
            validateState = enrollUiState.titleValidateState,
            errorDescription = stringResource(id = R.string.enroll_title_error_description),
            value = enrollUiState.enroll.title,
            onValueChange = onTitleValueChange
        )
        Spacer(modifier = Modifier.height(2.dp))
        DateRoadBasicTextField(
            placeholder = stringResource(id = R.string.enroll_date_placeholder),
            validateState = enrollUiState.dateValidateState,
            errorDescription = stringResource(id = R.string.enroll_date_error_description),
            value = enrollUiState.enroll.date,
            readOnly = true,
            iconResourceId = R.drawable.ic_enroll_calendar,
            onClick = onDateTextFieldClick
        )
        Spacer(modifier = Modifier.height(2.dp))
        DateRoadBasicTextField(
            placeholder = stringResource(id = R.string.enroll_date_start_at),
            value = enrollUiState.enroll.startAt,
            readOnly = true,
            iconResourceId = R.drawable.ic_enroll_time,
            onClick = onTimeTextFieldClick
        )
        Spacer(modifier = Modifier.height(20.dp))
        DateRoadDateChipGroup(
            dateChipGroupType = DateChipGroupType.ENROLL,
            selectedDateTags = enrollUiState.enroll.tags.mapNotNull { tag -> tag.getDateTagTypeByName() },
            onSelectedDateTagsChanged = onDateChipClicked
        )
        Spacer(modifier = Modifier.height(20.dp))
        DateRoadBasicTextField(
            placeholder = stringResource(id = R.string.enroll_city_placeholder),
            value = when (enrollUiState.enroll.city) {
                is SeoulAreaType -> enrollUiState.enroll.city.title
                is GyeonggiAreaType -> enrollUiState.enroll.city.title
                is IncheonAreaType -> enrollUiState.enroll.city.title
                else -> ""
            },
            readOnly = true,
            onClick = onRegionTextFieldClick
        )
        Spacer(modifier = Modifier.height(23.dp))
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
            onDateTextFieldClick = {},
            onTimeTextFieldClick = {},
            onRegionTextFieldClick = {},
            onTitleValueChange = {},
            onDateChipClicked = {}
        )
    }
}
