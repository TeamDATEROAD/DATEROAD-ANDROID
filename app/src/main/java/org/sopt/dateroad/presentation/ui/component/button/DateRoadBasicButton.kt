package org.sopt.dateroad.presentation.ui.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.ui.theme.DATEROADTheme
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun DateRoadBasicButton(
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    enabledBackgroundColor: Color = DateRoadTheme.colors.deepPurple,
    enabledContentColor: Color = DateRoadTheme.colors.white,
    disabledBackgroundColor: Color = DateRoadTheme.colors.gray200,
    disabledContentColor: Color = DateRoadTheme.colors.gray400
) {
    val backgroundColor = if (isEnabled) enabledBackgroundColor else disabledBackgroundColor
    val contentColor = if (isEnabled) enabledContentColor else disabledContentColor

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(backgroundColor)
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "프로필 등록하기",
            style = DateRoadTheme.typography.bodyBold15,
            color = contentColor,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@Preview
@Composable
fun DateRoadBasicButtonPreview() {
    DATEROADTheme {
        DateRoadBasicButton(
            isEnabled = true
        )
    }
}