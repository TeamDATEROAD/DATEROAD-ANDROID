package org.sopt.dateroad.presentation.ui.component.button

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.ui.theme.DATEROADTheme
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun DateRoadBasicButton(
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    textContent: String,
    onClick: () -> Unit = {}
) {
    DateRoadFilledButton(
        modifier = modifier.fillMaxWidth(),
        isEnabled = isEnabled,
        textContent = textContent,
        textStyle = DateRoadTheme.typography.bodyBold15,
        enabledBackgroundColor = DateRoadTheme.colors.deepPurple,
        enabledTextColor = DateRoadTheme.colors.white,
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
