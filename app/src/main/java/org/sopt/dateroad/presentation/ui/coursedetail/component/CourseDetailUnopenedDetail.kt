package org.sopt.dateroad.presentation.ui.coursedetail.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.R
import org.sopt.dateroad.presentation.type.CourseDetailUnopenedDetailType
import org.sopt.dateroad.presentation.ui.component.button.DateRoadFilledButton
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun CourseDetailUnopenedDetail(
    text: String,
    free: Int,
    courseDetailUnopenedDetailType: CourseDetailUnopenedDetailType,
    onButtonClicked: () -> Unit
) {
    Column {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = text,
                    style = DateRoadTheme.typography.bodyMed13Context,
                    color = DateRoadTheme.colors.black,
                    maxLines = 3,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.White.copy(alpha = 0.6f),
                                Color.White.copy(alpha = 1f)
                            )
                        )
                    )
                    .matchParentSize()
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_course_detail_is_not_access),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(id = R.string.course_detail_unopened_title),
            style = DateRoadTheme.typography.bodyBold17,
            color = DateRoadTheme.colors.black,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = stringResource(id = courseDetailUnopenedDetailType.descriptionStringRes),
            style = DateRoadTheme.typography.bodySemi15,
            color = DateRoadTheme.colors.purple600,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(24.dp))
        DateRoadFilledButton(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            isEnabled = true,
            textContent = when(courseDetailUnopenedDetailType) {
                CourseDetailUnopenedDetailType.FREE -> stringResource(id = courseDetailUnopenedDetailType.buttonTextStringRes, free)
                CourseDetailUnopenedDetailType.POINT -> stringResource(id = courseDetailUnopenedDetailType.buttonTextStringRes)
            },
            onClick = onButtonClicked,
            textStyle = DateRoadTheme.typography.bodyBold15,
            enabledBackgroundColor = DateRoadTheme.colors.purple600,
            enabledTextColor = DateRoadTheme.colors.white,
            disabledBackgroundColor = DateRoadTheme.colors.gray200,
            disabledTextColor = DateRoadTheme.colors.gray400,
            cornerRadius = 14.dp,
            paddingHorizontal = 52.dp,
            paddingVertical = 16.dp
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}
