package org.sopt.teamdateroad.presentation.ui.component.button

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.teamdateroad.ui.theme.DATEROADTheme
import org.sopt.teamdateroad.ui.theme.DateRoadTheme

@Composable
fun DateRoadBasicButton(
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    enabledBackgroundColor: Color = DateRoadTheme.colors.purple600,
    enabledTextColor: Color = DateRoadTheme.colors.white,
    textContent: String,
    onClick: () -> Unit = {}
) {
    DateRoadFilledButton(
        modifier = modifier.fillMaxWidth(),
        isEnabled = isEnabled,
        textContent = textContent,
        textStyle = DateRoadTheme.typography.bodyBold15,
        enabledBackgroundColor = enabledBackgroundColor,
        enabledTextColor = enabledTextColor,
        disabledBackgroundColor = DateRoadTheme.colors.gray200,
        disabledTextColor = DateRoadTheme.colors.gray400,
        cornerRadius = 14.dp,
        paddingHorizontal = 0.dp,
        paddingVertical = 16.dp,
        onClick = onClick
    )
}

@Preview
@Composable
fun DateRoadBasicButtonPreview() {
    DATEROADTheme {
        Column {
            DateRoadBasicButton(
                isEnabled = true,
                textContent = "프로필 등록하기"
            )
            DateRoadBasicButton(
                isEnabled = false,
                textContent = "프로필 등록하기"
            )
        }
    }
}
