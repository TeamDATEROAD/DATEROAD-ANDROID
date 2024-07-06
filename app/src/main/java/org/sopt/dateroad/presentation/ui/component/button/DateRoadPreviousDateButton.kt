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
import org.sopt.dateroad.ui.theme.Black
import org.sopt.dateroad.ui.theme.DATEROADTheme
import org.sopt.dateroad.ui.theme.DateRoadTheme
import org.sopt.dateroad.ui.theme.Gray100

@Composable
fun DateRoadPreviousDateButton(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Gray100,
    contentColor: Color = Black
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .background(backgroundColor)
            .padding(vertical = 11.dp, horizontal = 29.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "지난 데이트 보기",
            style = DateRoadTheme.typography.bodyBold15,
            color = contentColor,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@Preview
@Composable
fun DateRoadPreviousDateButtonPreview() {
    DATEROADTheme {
        DateRoadPreviousDateButton()
    }
}
