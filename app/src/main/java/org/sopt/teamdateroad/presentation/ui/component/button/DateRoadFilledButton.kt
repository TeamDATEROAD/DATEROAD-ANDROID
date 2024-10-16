package org.sopt.teamdateroad.presentation.ui.component.button

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.sopt.teamdateroad.ui.theme.DATEROADTheme
import org.sopt.teamdateroad.ui.theme.DateRoadTheme

@Composable
fun DateRoadFilledButton(
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    textContent: String,
    textStyle: TextStyle,
    enabledBackgroundColor: Color,
    enabledTextColor: Color,
    disabledBackgroundColor: Color,
    disabledTextColor: Color,
    cornerRadius: Dp,
    paddingHorizontal: Dp,
    paddingVertical: Dp,
    onClick: () -> Unit
) {
    DateRoadButton(
        modifier = modifier,
        backgroundColor = if (isEnabled) enabledBackgroundColor else disabledBackgroundColor,
        cornerRadius = cornerRadius,
        paddingHorizontal = paddingHorizontal,
        paddingVertical = paddingVertical,
        onClick = { if (isEnabled) onClick() }
    ) {
        Text(
            text = textContent,
            style = textStyle,
            color = if (isEnabled) enabledTextColor else disabledTextColor,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun DateRoadFilledButtonPreview() {
    DATEROADTheme {
        Column {
            DateRoadFilledButton(
                isEnabled = true,
                textContent = "중복확인",
                onClick = {},
                textStyle = DateRoadTheme.typography.bodyMed13,
                enabledBackgroundColor = DateRoadTheme.colors.purple600,
                enabledTextColor = DateRoadTheme.colors.white,
                disabledBackgroundColor = DateRoadTheme.colors.gray200,
                disabledTextColor = DateRoadTheme.colors.gray400,
                cornerRadius = 10.dp,
                paddingHorizontal = 14.dp,
                paddingVertical = 6.dp
            )
            DateRoadFilledButton(
                isEnabled = false,
                textContent = "중복확인",
                onClick = {},
                textStyle = DateRoadTheme.typography.bodyMed13,
                enabledBackgroundColor = DateRoadTheme.colors.purple600,
                enabledTextColor = DateRoadTheme.colors.white,
                disabledBackgroundColor = DateRoadTheme.colors.gray200,
                disabledTextColor = DateRoadTheme.colors.gray400,
                cornerRadius = 10.dp,
                paddingHorizontal = 14.dp,
                paddingVertical = 6.dp
            )
            DateRoadFilledButton(
                isEnabled = true,
                textContent = "불러오기",
                onClick = {},
                textStyle = DateRoadTheme.typography.bodyMed13,
                enabledBackgroundColor = DateRoadTheme.colors.purple600,
                enabledTextColor = DateRoadTheme.colors.white,
                disabledBackgroundColor = DateRoadTheme.colors.gray200,
                disabledTextColor = DateRoadTheme.colors.gray400,
                cornerRadius = 20.dp,
                paddingHorizontal = 10.dp,
                paddingVertical = 5.dp
            )
            DateRoadFilledButton(
                isEnabled = true,
                textContent = "데이트 코스 받고 100P 받기",
                onClick = {},
                textStyle = DateRoadTheme.typography.bodyMed13,
                enabledBackgroundColor = DateRoadTheme.colors.purple600,
                enabledTextColor = DateRoadTheme.colors.white,
                disabledBackgroundColor = DateRoadTheme.colors.gray200,
                disabledTextColor = DateRoadTheme.colors.gray400,
                cornerRadius = 10.dp,
                paddingHorizontal = 20.dp,
                paddingVertical = 10.dp
            )
        }
    }
}
