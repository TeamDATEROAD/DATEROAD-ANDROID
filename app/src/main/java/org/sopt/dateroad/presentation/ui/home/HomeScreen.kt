package org.sopt.dateroad.presentation.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.dateroad.presentation.type.EnrollType
import org.sopt.dateroad.presentation.util.modifier.noRippleClickable
import org.sopt.dateroad.ui.theme.DATEROADTheme

@Composable
fun HomeRoute(
    padding: PaddingValues,
    navigateToEnroll: (EnrollType) -> Unit
) {
    HomeScreen(
        padding = padding,
        navigateToEnroll
    )
}

@Composable
fun HomeScreen(
    padding: PaddingValues,
    navigateToPointHistory: (EnrollType) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize()
    ) {
        Text(
            modifier = Modifier.noRippleClickable(onClick = { navigateToPointHistory(EnrollType.TIMELINE) }),
            text = "HomeScreen",
            fontSize = 30.sp,
            fontWeight = Bold
        )
    }
}

@Preview()
@Composable
fun HomeScreenPreview() {
    DATEROADTheme {
        HomeScreen(padding = PaddingValues(0.dp), navigateToPointHistory = {})
    }
}
