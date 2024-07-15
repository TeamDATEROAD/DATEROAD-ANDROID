package org.sopt.dateroad.presentation.ui.component.tag

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.R
import org.sopt.dateroad.presentation.util.modifier.noRippleClickable
import org.sopt.dateroad.ui.theme.DATEROADTheme
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun DateRoadPointTag(
    modifier: Modifier = Modifier,
    text: String,
    profileImage: Painter,
    backgroundColor: Color = DateRoadTheme.colors.purple500,
    contentColor: Color = DateRoadTheme.colors.white,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(backgroundColor)
            .noRippleClickable(onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            style = DateRoadTheme.typography.bodyBold13,
            color = contentColor,
            maxLines = 1,
            modifier = modifier
                .padding(start = 14.dp, end = 7.dp)
        )
        Image(
            painter = profileImage,
            contentDescription = null,
            modifier = modifier
                .clip(CircleShape)
        )
    }
}

@Preview
@Composable
fun DateRoadPointTagPreview() {
    DATEROADTheme {
        DateRoadPointTag(
            text = "5000 P",
            profileImage = painterResource(id = R.drawable.img_top_bar_profile)
        )
    }
}
