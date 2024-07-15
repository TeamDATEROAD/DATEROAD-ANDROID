package org.sopt.dateroad.presentation.ui.component.button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.ui.theme.DATEROADTheme
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun DateRoadOutlinedButton(
    modifier: Modifier = Modifier,
    textContent: String,
    textStyle: TextStyle,
    backgroundColor: Color,
    contentColor: Color,
    borderWidth: Dp,
    cornerRadius: Dp,
    paddingHorizontal: Dp = 0.dp,
    paddingVertical: Dp = 0.dp,
    onClick: () -> Unit
) {
    DateRoadButton(
        modifier = modifier.fillMaxWidth(),
        backgroundColor = backgroundColor,
        borderColor = contentColor,
        borderWidth = borderWidth,
        cornerRadius = cornerRadius,
        paddingVertical = paddingVertical,
        paddingHorizontal = paddingHorizontal,
        onClick = onClick
    ) {
        Text(
            modifier = modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = textContent,
            style = textStyle,
            color = contentColor
        )
    }
}

@Preview
@Composable
fun DateRoadOutlinedButtonPreview() {
    DATEROADTheme {
        DateRoadOutlinedButton(
            textContent = "다음",
            onClick = {},
            textStyle = DateRoadTheme.typography.bodyBold15,
            contentColor = DateRoadTheme.colors.purple600,
            backgroundColor = DateRoadTheme.colors.white,
            cornerRadius = 29.dp,
            borderWidth = 1.dp,
            paddingVertical = 16.dp
        )
    }
}
