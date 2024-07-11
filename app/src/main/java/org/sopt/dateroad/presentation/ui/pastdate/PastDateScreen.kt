package org.sopt.dateroad.presentation.ui.pastdate

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.dateroad.R
import org.sopt.dateroad.presentation.ui.component.topbar.DateRoadBasicTopBar
import org.sopt.dateroad.ui.theme.DATEROADTheme
import org.sopt.dateroad.ui.theme.DateRoadTheme

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
            .background(color = DateRoadTheme.colors.white)
    ) {
        DateRoadBasicTopBar(
            title = "데이트 일정",
            iconLeftResource = R.drawable.ic_top_bar_back_white,
            buttonContent = {
                Icon(
                    painterResource(id = R.drawable.ic_top_bar_share),
                    contentDescription = null,
                    tint = DateRoadTheme.colors.black
                )
            }
        )
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