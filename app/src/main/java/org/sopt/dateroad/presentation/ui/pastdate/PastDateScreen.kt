package org.sopt.dateroad.presentation.ui.read

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
fun PastDateRoute(
    padding: PaddingValues
) {
    PastDateScreen(padding)
}

@Composable
fun PastDateScreen(
    padding: PaddingValues
) {
    Column(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize()
    ) {
        Text(
            text = "PastDateScreen",
            fontSize = 30.sp,
            fontWeight = Bold
        )
    }
}

@Preview()
@Composable
fun PastDateScreenPreview() {
    DATEROADTheme {
        PastDateScreen(padding = PaddingValues(0.dp))
    }
}
