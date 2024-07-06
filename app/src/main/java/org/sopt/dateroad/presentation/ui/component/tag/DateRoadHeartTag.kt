package org.sopt.dateroad.presentation.ui.component.tag

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.R
import org.sopt.dateroad.ui.theme.DATEROADTheme
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun DateRoadHeartTag(
    modifier: Modifier = Modifier,
    text: String,
    backgroundColor: Color = DateRoadTheme.colors.deepPurple,
    contentColor: Color = DateRoadTheme.colors.white
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(backgroundColor)
            .padding(horizontal = 10.dp, vertical = 2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_tag_heart),
            contentDescription = null
        )
        Spacer(modifier = Modifier.size(5.dp))
        Text(
            text = text,
            style = DateRoadTheme.typography.bodyBold13,
            color = contentColor,
            maxLines = 1
        )
    }
}

@Preview
@Composable
fun DateRoadHeartTagPreview() {
    DATEROADTheme {
        DateRoadHeartTag(
            text = "5"
        )
    }
}
