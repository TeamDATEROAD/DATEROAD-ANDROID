package org.sopt.dateroad.presentation.ui.timeline

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.dateroad.presentation.ui.component.button.DateRoadImageButton
import org.sopt.dateroad.presentation.ui.component.topbar.DateRoadCourseTopBar
import org.sopt.dateroad.ui.theme.DATEROADTheme
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun TimelineRoute(
    padding: PaddingValues,
) {
    TimelineScreen(
        padding,
    )
}

@Composable
fun TimelineScreen(
    padding: PaddingValues,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = DateRoadTheme.colors.white)
    ) {
        DateRoadCourseTopBar(
            title = "데이트 일정",
            buttonContent = {
                DateRoadImageButton(
                    isEnabled = true,
                    onClick = {},
                    cornerRadius = 14.dp,
                    paddingHorizontal = 16.dp,
                    paddingVertical = 8.dp
                )
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Text(
                text = "TimelineScreen",
                fontSize = 30.sp,
                fontWeight = Bold
            )
        }
    }
}

@Preview()
@Composable
fun TimelineScreenPreview() {
    DATEROADTheme {
        TimelineScreen(
            padding = PaddingValues(0.dp),
        )
    }
}
