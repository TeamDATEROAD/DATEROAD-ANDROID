package org.sopt.dateroad.presentation.ui.component.button

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.R
import org.sopt.dateroad.ui.theme.DATEROADTheme
import org.sopt.dateroad.ui.theme.DeepPurple
import org.sopt.dateroad.ui.theme.Gray100
import org.sopt.dateroad.ui.theme.Gray300
import org.sopt.dateroad.ui.theme.White

@Composable
fun DateRoadAddCourseButton(
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    enabledBackgroundColor: Color = DeepPurple,
    enabledContentColor: Color = White,
    disabledBackgroundColor: Color = Gray100,
    disabledContentColor: Color = Gray300
) {
    val backgroundColor = if (isEnabled) enabledBackgroundColor else disabledBackgroundColor
    val contentColor = if (isEnabled) enabledContentColor else disabledContentColor

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .background(backgroundColor)
            .padding(12.dp)
    ) {
        Image(
            colorFilter = ColorFilter.tint(contentColor),
            painter = painterResource(id = R.drawable.ic_all_plus_white),
            contentDescription = null
        )
    }
}

@Preview
@Composable
fun DateRoadAddCourseButtonPreview() {
    DATEROADTheme {
        DateRoadAddCourseButton(
            isEnabled = true
        )
    }
}

@Preview
@Composable
fun DateRoadAddCourseButtonDisabledPreview() {
    DATEROADTheme {
        DateRoadAddCourseButton(
            isEnabled = false
        )
    }
}
