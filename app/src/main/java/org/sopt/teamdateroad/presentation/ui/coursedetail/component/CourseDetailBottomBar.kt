package org.sopt.teamdateroad.presentation.ui.coursedetail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.teamdateroad.R
import org.sopt.teamdateroad.presentation.ui.component.button.DateRoadBasicButton
import org.sopt.teamdateroad.presentation.ui.component.button.DateRoadImageButton
import org.sopt.teamdateroad.ui.theme.DateRoadTheme

@Composable
fun CourseDetailBottomBar(
    modifier: Modifier = Modifier,
    isUserLiked: Boolean,
    onLikeButtonClicked: () -> Unit,
    onEnrollButtonClicked: () -> Unit
) {
    var buttonHeight by remember { mutableStateOf(0.dp) }
    val density = LocalDensity.current
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = DateRoadTheme.colors.white)
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        DateRoadImageButton(
            modifier = Modifier.height(buttonHeight),
            iconResId = R.drawable.ic_coures_detail_heart_default,
            enabledContentColor = DateRoadTheme.colors.purple600,
            disabledContentColor = DateRoadTheme.colors.gray200,
            enabledBackgroundColor = DateRoadTheme.colors.gray100,
            disabledBackgroundColor = DateRoadTheme.colors.gray100,
            isEnabled = isUserLiked,
            onClick = onLikeButtonClicked,
            cornerRadius = 14.dp,
            paddingHorizontal = 23.dp,
            paddingVertical = 0.dp
        )
        Spacer(modifier = Modifier.width(12.dp))
        DateRoadBasicButton(
            modifier = Modifier
                .weight(1f)
                .onSizeChanged { size ->
                    buttonHeight = with(density) { size.height.toDp() }
                },
            textContent = stringResource(id = R.string.course_detail_get_course),
            onClick = onEnrollButtonClicked
        )
    }
}

@Preview
@Composable
fun ButtonPreview(modifier: Modifier = Modifier) {
    Box(modifier = Modifier) {
        CourseDetailBottomBar(
            modifier = Modifier.align(Alignment.BottomCenter),
            isUserLiked = true,
            onLikeButtonClicked = { },
            onEnrollButtonClicked = { }
        )
    }
}
