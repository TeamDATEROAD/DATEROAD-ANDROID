package org.sopt.dateroad.presentation.ui.component.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.ui.theme.DATEROADTheme
import org.sopt.dateroad.ui.theme.DateRoadTheme
import org.sopt.dateroad.ui.theme.DeepPurple
import org.sopt.dateroad.ui.theme.Gray400

@Composable
fun DateRoadEditButton(
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    enabledText: String = "편집",
    enabledContentColor: Color = DateRoadTheme.colors.gray400,
    disabledText: String = "완료",
    disabledContentColor: Color = DateRoadTheme.colors.deepPurple
) {
    val contentColor = if (isEnabled) enabledContentColor else disabledContentColor
    val contentText = if (isEnabled) enabledText else disabledText

    Row(
        modifier = modifier
            .padding(vertical = 6.dp, horizontal = 18.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = contentText,
            style = DateRoadTheme.typography.bodyMed13,
            color = contentColor,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@Preview
@Composable
fun DateRoadEditButtonPreview() {
    DATEROADTheme {
        DateRoadEditButton(
            isEnabled = true
        )
    }
}
