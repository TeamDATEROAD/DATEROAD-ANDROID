package org.sopt.dateroad.presentation.ui.enroll

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.dateroad.ui.theme.DATEROADTheme

@Composable
fun EnrollSecondScreen(
    enrollUiState: EnrollContract.EnrollUiState = EnrollContract.EnrollUiState()
) {
}

@Preview
@Composable
fun EnrollSecondScreenPreview() {
    DATEROADTheme {
        EnrollSecondScreen()
    }
}