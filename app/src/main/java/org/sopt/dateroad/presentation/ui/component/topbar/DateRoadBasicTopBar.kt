package org.sopt.dateroad.presentation.ui.component.topbar

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.R
import org.sopt.dateroad.presentation.ui.component.button.DateRoadFilledButton
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun DateRoadBasicTopBar(
    title: String,
    backGroundColor: Color = Color.Transparent,
    @DrawableRes iconLeftResource: Int? = null,
    buttonContent: (@Composable () -> Unit)? = null
) {
    var iconWidth by remember { mutableStateOf(0) }
    var contentWidth by remember { mutableStateOf(0) }
    var paddingValue = maxOf(iconWidth, contentWidth)
    LaunchedEffect(iconWidth, contentWidth) {
        paddingValue = maxOf(iconWidth, contentWidth)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(backGroundColor)
            .padding(horizontal = 16.dp, vertical = 11.dp)
    ) {
        if (iconLeftResource != null) {
            Icon(
                painter = painterResource(id = iconLeftResource),
                contentDescription = null,
                tint = DateRoadTheme.colors.black,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .onGloballyPositioned { coordinates ->
                        iconWidth = coordinates.size.width
                    }
                    .padding(top = 16.dp, bottom = 16.dp, end = 16.dp)
            )
        }

        if (buttonContent != null) {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .onGloballyPositioned { coordinates ->
                        contentWidth = coordinates.size.width
                    }
            ) {
                buttonContent()
            }
        }

        Text(
            text = title,
            style = DateRoadTheme.typography.titleBold18,
            color = DateRoadTheme.colors.black,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .padding(horizontal = paddingValue.dp / 2)
        )
    }
}

@Preview
@Composable
fun DateRoadBasicTopBarPreview() {
    Column {
        DateRoadBasicTopBar(
            title = "포인트 내역포인트 내역포인트내역내역포인트포인트내역포인트 내역",
            iconLeftResource = R.drawable.ic_top_bar_back_white,
            backGroundColor = DateRoadTheme.colors.white
        )
        DateRoadBasicTopBar(
            title = "내 프로필",
            backGroundColor = DateRoadTheme.colors.white
        )
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
        DateRoadBasicTopBar(
            title = "데이트 일정데이트 일정데이트 일정데이트 일정일정데이트 일정데이트 일정",
            iconLeftResource = R.drawable.ic_top_bar_back_white,
            buttonContent = {
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
