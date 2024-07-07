package org.sopt.dateroad.presentation.ui.component.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.R
import org.sopt.dateroad.presentation.ui.component.button.DateRoadFilledButton
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun DateRoadBasicTopBar(
    pointText: String,
    backGroundColor: Color = Color.Transparent,
    iconLeftResource: Int? = null,
    content: (@Composable () -> Unit)? = null,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(backGroundColor)
            .padding(vertical = 15.dp, horizontal = 16.dp)
    ) {
        Icon(
            painter = iconLeftResource?.let {
                painterResource(id = it)
            } ?: painterResource(id = R.drawable.ic_top_bar_back_white),
            contentDescription = null,
            tint = DateRoadTheme.colors.black,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .graphicsLayer {
                    alpha = if (iconLeftResource == null) 0f else 1f
                }
        )

        Text(
            text = pointText,
            style = DateRoadTheme.typography.titleBold18,
            color = DateRoadTheme.colors.black,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
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
fun DateRoadBasicTopBarPreview() {
    Column {
        DateRoadBasicTopBar(
            pointText = "포인트 내역",
            iconLeftResource = R.drawable.ic_top_bar_back_white,
            backGroundColor = DateRoadTheme.colors.white,
        )
        DateRoadBasicTopBar(
            pointText = "내 프로필",
            backGroundColor = DateRoadTheme.colors.white,
        )
        DateRoadBasicTopBar(
            pointText = "데이트 일정",
            iconLeftResource = R.drawable.ic_top_bar_back_white,
            content = {
                Icon(
                    painterResource(id = R.drawable.ic_top_bar_share),
                    contentDescription = null,
                    tint = DateRoadTheme.colors.black
                )
            }
        )
        DateRoadBasicTopBar(
            pointText = "일정 등록하기",
            iconLeftResource = R.drawable.ic_top_bar_back_white,
            content = {
                DateRoadFilledButton(
                    isEnabled = true,
                    textContent = "불러오기",
                    onClick = {},
                    textStyle = DateRoadTheme.typography.bodyMed13,
                    enabledBackgroundColor = DateRoadTheme.colors.deepPurple,
                    enabledTextColor = DateRoadTheme.colors.white,
                    disabledBackgroundColor = DateRoadTheme.colors.gray200,
                    disabledTextColor = DateRoadTheme.colors.gray400,
                    cornerRadius = 20.dp,
                    paddingHorizontal = 10.dp,
                    paddingVertical = 5.dp
                )
            }
        )
    }
}
