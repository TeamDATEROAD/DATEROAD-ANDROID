package org.sopt.dateroad.presentation.ui.component.tag

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.ui.theme.DATEROADTheme
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun DateRoadPlaceCardNumberTag(
    modifier: Modifier = Modifier,
    text: String,
    backgroundColor: Color = DateRoadTheme.colors.deepPurple,
    contentColor: Color = DateRoadTheme.colors.white
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(50.dp))
            .background(backgroundColor)
            .padding(horizontal = 9.dp, vertical = 4.dp)
    ) {
        Text(
            text = text,
            style = DateRoadTheme.typography.bodyBold13,
            color = contentColor
        )
    }
}

@Preview
@Composable
fun DateRoadPlaceCardNumberTagPreview() {
    DATEROADTheme {
        DateRoadPlaceCardNumberTag(
            text = "1"
        )
    }
}
