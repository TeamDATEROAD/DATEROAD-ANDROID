package org.sopt.dateroad.presentation.ui.coursedetail.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.R
import org.sopt.dateroad.domain.model.Course
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun CourseDetailInfoBar(course: Course) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_all_money_14),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = course.cost,
            color = DateRoadTheme.colors.gray400,
            style = DateRoadTheme.typography.bodySemi15
        )
        Spacer(modifier = Modifier.width(25.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_all_clock_14),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = course.duration,
            color = DateRoadTheme.colors.gray400,
            style = DateRoadTheme.typography.bodySemi15
        )
        Spacer(modifier = Modifier.width(25.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_all_location_14),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = course.city,
            color = DateRoadTheme.colors.gray400,
            style = DateRoadTheme.typography.bodySemi15
        )
    }
}

@Preview
@Composable
fun CourseDetailInfoBarPreview() {
    CourseDetailInfoBar(
        course = Course(
            id = 1,
            url = "https://avatars.githubusercontent.com/u/103172971?v=4",
            city = "건대/성수/왕십리",
            title = "성수동 당일치기 데이트 코스 둘러보러 가실까요?",
            cost = "5만원 이하",
            duration = "10시간",
            like = "999"
        )
    )
}
