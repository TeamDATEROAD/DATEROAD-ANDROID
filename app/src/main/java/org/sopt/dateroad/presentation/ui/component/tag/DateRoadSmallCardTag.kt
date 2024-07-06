package org.sopt.dateroad.presentation.ui.component.tag

import androidx.compose.foundation.background
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
fun DateRoadSmallCardTag(
    modifier: Modifier = Modifier,
    text: String,
    backgroundColor: Color = DateRoadTheme.colors.gray200,
    contentColor: Color = DateRoadTheme.colors.black
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(backgroundColor)
            .padding(horizontal = 14.dp, vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            style = DateRoadTheme.typography.bodyMed13,
            color = contentColor
        )
    }
}

@Preview
@Composable
fun DateRoadSmallCardTagPreview() {
    DATEROADTheme {
        DateRoadSmallCardTag(
            text = "2시간"
        )
    }
}
