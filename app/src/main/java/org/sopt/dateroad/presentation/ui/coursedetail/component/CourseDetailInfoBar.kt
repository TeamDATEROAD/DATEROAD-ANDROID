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
import org.sopt.dateroad.domain.model.CourseDetail
import org.sopt.dateroad.domain.model.Place
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun CourseDetailInfoBar(courseDetail: CourseDetail) {
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
            text = courseDetail.totalCost.toString(),
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
            text = courseDetail.totalTime.toString(),
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
            text = courseDetail.city,
            color = DateRoadTheme.colors.gray400,
            style = DateRoadTheme.typography.bodySemi15
        )
    }
}

@Preview
@Composable
fun CourseDetailInfoBarPreview() {
    CourseDetailInfoBar(
        courseDetail = CourseDetail(
            courseId = 1,
            imageList = listOf(
                R.drawable.img_course_detail_dummy,
                R.drawable.img_course_detail_dummy,
                R.drawable.img_course_detail_dummy
            ),
            like = 999,
            totalTime = 10,
            date = "2023-07-12",
            city = "건대/성수/왕십리",
            title = "성수동 당일치기 데이트 코스 둘러보러 가실까요?",
            description = "A full day tour around Seongsu-dong, exploring various spots and enjoying local cuisine.",
            places = listOf(
                Place(sequence = 1, title = "성수미술관 성수점", duration = "2시간"),
                Place(sequence = 2, title = "성수미술관 성수점", duration = "2시간"),
                Place(sequence = 3, title = "성수미술관 성수점", duration = "2시간")
            ),
            totalCost = "50,000 원",
            tags = listOf("Seongsu-dong", "One-day trip", "Dating course"),
            isAccess = true,
            free = 1,
            isMine = false,
            totalPoint = 95
        )
    )
}
