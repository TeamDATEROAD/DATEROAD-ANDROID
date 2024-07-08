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
import org.sopt.dateroad.ui.theme.DATEROADTheme

@Composable
fun LookRoute(
    padding: PaddingValues
) {
    LookScreen(padding)
}

@Composable
fun LookScreen(
    padding: PaddingValues
) {
    Column(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize()
    ) {
        Text(
            text = "LookScreen",
            fontSize = 30.sp,
            fontWeight = Bold
        )
    }
}

@Preview()
@Composable
fun LookScreenPreview() {
    DATEROADTheme {
        LookScreen(padding = PaddingValues(0.dp))
    }
}
