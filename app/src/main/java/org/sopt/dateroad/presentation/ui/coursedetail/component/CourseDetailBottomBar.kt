package org.sopt.dateroad.presentation.ui.coursedetail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.R
import org.sopt.dateroad.presentation.ui.component.button.DateRoadBasicButton
import org.sopt.dateroad.presentation.ui.component.button.DateRoadImageButton
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun CourseDetailBottomBar(
    modifier: Modifier = Modifier,
    isUserLiked: Boolean,
    onLikeButtonClicked: () -> Unit,
    onEnrollButtonClicked: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = DateRoadTheme.colors.white)
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        DateRoadImageButton(
            iconResId = R.drawable.ic_coures_detail_heart_default,
            enabledContentColor = DateRoadTheme.colors.purple600,
            disabledContentColor = DateRoadTheme.colors.gray200,
            enabledBackgroundColor = DateRoadTheme.colors.gray100,
            disabledBackgroundColor = DateRoadTheme.colors.gray100,
            isEnabled = isUserLiked,
            onClick = onLikeButtonClicked,
            cornerRadius = 14.dp,
            paddingHorizontal = 23.dp,
            paddingVertical = 18.dp
        )
        Spacer(modifier = Modifier.width(12.dp))
        DateRoadBasicButton(
            modifier = Modifier.weight(1f),
            textContent = stringResource(id = R.string.course_detail_get_course),
            onClick = onEnrollButtonClicked
        )
    }
}
