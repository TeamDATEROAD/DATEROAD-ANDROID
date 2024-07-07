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
fun DateRoadTextButton(
    modifier: Modifier = Modifier,
    textContent: String,
    textStyle: TextStyle,
    textColor: Color,
    paddingHorizontal: Dp,
    paddingVertical: Dp,
    onClick: () -> Unit
) {
    DateRoadButton(
        modifier = modifier,
        backgroundColor = Color.Transparent,
        paddingHorizontal = paddingHorizontal,
        paddingVertical = paddingVertical,
        onClick = onClick
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
fun DateRoadTextButtonPreview() {
    DATEROADTheme {
        Column {
            DateRoadTextButton(
                textContent = "편집",
                textStyle = DateRoadTheme.typography.bodyMed13,
                textColor = DateRoadTheme.colors.gray400,
                paddingHorizontal = 18.dp,
                paddingVertical = 6.dp,
                onClick = {}
            )
            DateRoadTextButton(
                textContent = "완료",
                textStyle = DateRoadTheme.typography.bodyMed13,
                textColor = DateRoadTheme.colors.deepPurple,
                paddingHorizontal = 18.dp,
                paddingVertical = 6.dp,
                onClick = {}
            )
            DateRoadTextButton(
                textContent = "더보기",
                textStyle = DateRoadTheme.typography.bodyMed13,
                textColor = DateRoadTheme.colors.deepPurple,
                paddingHorizontal = 20.dp,
                paddingVertical = 8.dp,
                onClick = {}
            )
            DateRoadTextButton(
                textContent = "삭제",
                textStyle = DateRoadTheme.typography.bodyMed13,
                textColor = DateRoadTheme.colors.gray400,
                paddingHorizontal = 18.dp,
                paddingVertical = 6.dp,
                onClick = {}
            )
        }
    }
}
