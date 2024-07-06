package org.sopt.dateroad.presentation.ui.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
import org.sopt.dateroad.ui.theme.DeepPurple
import org.sopt.dateroad.ui.theme.Gray200
import org.sopt.dateroad.ui.theme.Gray400
import org.sopt.dateroad.ui.theme.White

@Composable
fun DateRoadSameCheckButton(
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    enabledBackgroundColor: Color = DeepPurple,
    enabledContentColor: Color = White,
    disabledBackgroundColor: Color = Gray200,
    disabledContentColor: Color = Gray400
) {
    val backgroundColor = if (isEnabled) enabledBackgroundColor else disabledBackgroundColor
    val contentColor = if (isEnabled) enabledContentColor else disabledContentColor

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(backgroundColor)
            .padding(vertical = 6.dp, horizontal = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "중복확인",
            style = DateRoadTheme.typography.bodyMed13,
            color = contentColor,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@Preview
@Composable
fun DateRoadSameCheckButtonPreview() {
    DATEROADTheme {
        DateRoadSameCheckButton(
            isEnabled = true
        )
    }
}

@Preview
@Composable
fun DateRoadSameCheckButtonDisabledPreview() {
    DATEROADTheme {
        DateRoadSameCheckButton(
            isEnabled = false
        )
    }
}
