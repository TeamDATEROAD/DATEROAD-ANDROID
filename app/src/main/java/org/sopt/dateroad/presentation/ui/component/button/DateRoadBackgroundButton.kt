package org.sopt.dateroad.presentation.ui.component.button

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.ui.theme.DATEROADTheme
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun DateRoadBackgroundButton(
    modifier: Modifier = Modifier,
    isEnabled: Boolean = false,
    textContent: String,
    textStyle: TextStyle,
    enabledBackgroundColor: Color,
    enabledTextColor: Color,
    disabledBackgroundColor: Color,
    disabledTextColor: Color,
    cornerRadius: Dp,
    paddingHorizontal: Dp,
    paddingVertical: Dp,
    onClick: () -> Unit,
) {
    val backgroundColor = if (isEnabled) enabledBackgroundColor else disabledBackgroundColor
    val textColor = if (isEnabled) enabledTextColor else disabledTextColor

    DateRoadButton(
        modifier = modifier,
        backgroundColor = backgroundColor,
        cornerRadius = cornerRadius,
        paddingHorizontal = paddingHorizontal,
        paddingVertical = paddingVertical,
        onClick = onClick,
    ) {
        Text(
            text = textContent,
            style = textStyle,
            color = textColor
        )
    }
}

@Preview
@Composable
fun DateRoadBackgroundButtonPreview() {
    DATEROADTheme {
        Column {
            DateRoadBackgroundButton(
                isEnabled = true,
                textContent = "프로필 등록하기",
                onClick = {},
                textStyle = DateRoadTheme.typography.bodyBold15,
                enabledBackgroundColor = DateRoadTheme.colors.deepPurple,
                enabledTextColor = DateRoadTheme.colors.white,
                disabledBackgroundColor = DateRoadTheme.colors.gray200,
                disabledTextColor = DateRoadTheme.colors.gray400,
                cornerRadius = 14.dp,
                paddingHorizontal = 16.dp,
                paddingVertical = 8.dp
            )
        }
    }
}