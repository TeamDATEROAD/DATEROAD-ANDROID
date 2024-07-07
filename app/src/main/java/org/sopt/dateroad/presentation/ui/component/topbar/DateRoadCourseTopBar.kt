package org.sopt.dateroad.presentation.ui.component.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.presentation.ui.component.button.DateRoadImageButton
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun DateRoadCourseTopBar(
    pointText: String,
    content: (@Composable () -> Unit)? = null,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .padding(vertical = 12.dp, horizontal = 16.dp)
    ) {
        Text(
            text = pointText,
            style = DateRoadTheme.typography.titleBold20,
            color = DateRoadTheme.colors.black,
            textAlign = TextAlign.Center,
        )
        if (content != null) {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
            ) {
                content()
            }
        }
    }
}

@Preview
@Composable
fun DateRoadCourseTopBarPreview() {
    Column {
        DateRoadCourseTopBar(
            pointText = "코스 둘러보기",
            content = {
                DateRoadImageButton(
                    isEnabled = true,
                    onClick = {},
                    cornerRadius = 14.dp,
                    paddingHorizontal = 16.dp,
                    paddingVertical = 8.dp
                )
            }
        )
        DateRoadCourseTopBar(
            pointText = "데이트 일정",
            content = {
                DateRoadImageButton(
                    isEnabled = true,
                    onClick = {},
                    cornerRadius = 14.dp,
                    paddingHorizontal = 16.dp,
                    paddingVertical = 8.dp
                )
            }
        )
    }
}
