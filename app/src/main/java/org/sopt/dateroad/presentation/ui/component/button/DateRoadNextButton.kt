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

@Composable
fun DateRoadNextButton(
    modifier: Modifier = Modifier,
    backgroundColor: Color = DateRoadTheme.colors.deepPurple,
    contentColor: Color = DateRoadTheme.colors.white,
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(backgroundColor)
            .padding(vertical = 10.dp, horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "데이트 코스 올리고 50P 받기",
            style = DateRoadTheme.typography.bodyBold15,
            color = contentColor,
            modifier = modifier.align(Alignment.CenterVertically)
        )
    }
}

@Preview
@Composable
fun DateRoadNextButtonPreview() {
    DATEROADTheme {
        DateRoadNextButton()
    }
}
