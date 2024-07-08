package org.sopt.dateroad.presentation.ui.component.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import org.sopt.dateroad.R
import org.sopt.dateroad.domain.model.Course
import org.sopt.dateroad.presentation.type.TagType
import org.sopt.dateroad.presentation.ui.component.tag.DateRoadImageTag
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun DateRoadCourseCard(
    course: Course
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(DateRoadTheme.colors.white)
            .padding(vertical = 10.dp, horizontal = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .size(110.dp)
        ) {
            AsyncImage(
                model = course.url,
                contentDescription = null,
                modifier = Modifier
                    .matchParentSize()
                    .clip(RoundedCornerShape(14.dp))
            )
            DateRoadImageTag(
                textContent = course.like.toString(),
                imageContent = R.drawable.ic_tag_heart,
                tagContentType = TagType.HEART,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 5.dp, bottom = 5.dp, end = 5.dp)
            )
        }
        Column(
            modifier = Modifier
                .height(110.dp)
        ) {
            Text(
                text = course.city,
                style = DateRoadTheme.typography.bodyMed13,
                color = DateRoadTheme.colors.gray400,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, start = 14.dp, bottom = 5.dp)
            )
            Text(
                text = course.title,
                style = DateRoadTheme.typography.bodyMed13,
                color = DateRoadTheme.colors.black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, start = 14.dp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier
                    .padding(bottom = 4.dp, start = 14.dp)
            ) {
                DateRoadImageTag(
                    textContent = "${course.cost}만원 초과",
                    imageContent = R.drawable.ic_all_money_12,
                    tagContentType = TagType.MONEY
                )
                Spacer(modifier = Modifier.size(6.dp))
                DateRoadImageTag(
                    textContent = "${course.duration}시간",
                    imageContent = R.drawable.ic_all_clock_12,
                    tagContentType = TagType.TIME
                )
            }
        }
    }
}

@Preview
@Composable
fun DateRoadCourseCardPreview() {
    Column {
        DateRoadCourseCard(
            course = Course(
                id = 1,
                url = "https://thumb.mt.co.kr/06/2009/04/2009042416153570569_3.jpg",
                openedAt = "2023-01-01",
                city = "건대/성수/왕십리",
                title = "여기 야키니쿠 꼭 먹으러 가세요\n하지만 일본에 있습니다.",
                cost = 10,
                duration = 10,
                like = 999
            )
        )
    }
}
