package org.sopt.teamdateroad.presentation.ui.component.topbar

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
import org.sopt.teamdateroad.presentation.ui.component.button.DateRoadImageButton
import org.sopt.teamdateroad.ui.theme.DateRoadTheme

@Composable
fun DateRoadLeftTitleTopBar(
    title: String,
    buttonContent: (@Composable () -> Unit)? = null
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .padding(vertical = 13.dp, horizontal = 16.dp)
    ) {
        Text(
            text = title,
            style = DateRoadTheme.typography.titleBold20,
            color = DateRoadTheme.colors.black,
            textAlign = TextAlign.Center
        )
        if (buttonContent != null) {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
            ) {
                buttonContent()
            }
        }
    }
}

@Preview
@Composable
fun DateRoadLeftTitlePreview() {
    Column {
        DateRoadLeftTitleTopBar(
            title = "코스 둘러보기",
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
        DateRoadLeftTitleTopBar(
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
    }
}
