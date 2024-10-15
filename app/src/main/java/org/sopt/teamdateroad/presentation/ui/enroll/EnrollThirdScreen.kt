package org.sopt.teamdateroad.presentation.ui.enroll

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.teamdateroad.R
import org.sopt.teamdateroad.presentation.ui.component.textfield.DateRoadBasicTextField
import org.sopt.teamdateroad.presentation.ui.component.textfield.DateRoadTextArea
import org.sopt.teamdateroad.presentation.util.view.LoadState
import org.sopt.teamdateroad.ui.theme.DATEROADTheme

@Composable
fun EnrollThirdScreen(
    enrollUiState: EnrollContract.EnrollUiState = EnrollContract.EnrollUiState(),
    onDescriptionValueChange: (String) -> Unit,
    onCostValueChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(11.dp))
        DateRoadTextArea(
            title = stringResource(id = R.string.enroll_description_title),
            placeholder = stringResource(id = R.string.enroll_description_placeholder),
            value = enrollUiState.enroll.description,
            onValueChange = onDescriptionValueChange
        )
        Spacer(modifier = Modifier.height(15.dp))
        DateRoadBasicTextField(
            title = stringResource(id = R.string.enroll_cost_title),
            placeholder = stringResource(id = R.string.enroll_cost_placeholder),
            value = enrollUiState.enroll.cost,
            onValueChange = { newValue ->
                if (newValue.all { it.isDigit() }) onCostValueChange(newValue)
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(6.dp))
    }
}

@Preview
@Composable
fun EnrollSecondThirdPreview() {
    DATEROADTheme {
        EnrollThirdScreen(
            enrollUiState = EnrollContract.EnrollUiState(
                loadState = LoadState.Success
            ),
            onDescriptionValueChange = {},
            onCostValueChange = {}
        )
    }
}
