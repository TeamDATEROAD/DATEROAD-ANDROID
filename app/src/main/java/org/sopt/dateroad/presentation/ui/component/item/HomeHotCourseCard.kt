package org.sopt.dateroad.presentation.ui.component.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import org.sopt.dateroad.R
import org.sopt.dateroad.domain.model.Course
import org.sopt.dateroad.presentation.type.TagType
import org.sopt.dateroad.presentation.ui.component.tag.DateRoadImageTag
import org.sopt.dateroad.presentation.util.modifier.noRippleClickable
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun HomeHotCourseCard(
    course: Course,
    onClick: (Int) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .padding(start = 16.dp)
            .background(DateRoadTheme.colors.white)
            .width(230.dp)
            .noRippleClickable(onClick = { onClick(course.courseId) })
    ) {
        Text(
            text = course.city,
            style = DateRoadTheme.typography.bodyMed13,
            color = DateRoadTheme.colors.white,
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                .background(DateRoadTheme.colors.purple500)
                .padding(vertical = 4.dp, horizontal = 13.dp)
        )
        Box(
            modifier = Modifier
                .aspectRatio(1f)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(course.thumbnail)
                    .crossfade(true)
                    .build(),
                placeholder = null,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .matchParentSize()
                    .clip(RoundedCornerShape(topEnd = 14.dp, bottomEnd = 14.dp, bottomStart = 14.dp))
            )
            DateRoadImageTag(
                textContent = course.like,
                imageContent = R.drawable.ic_tag_heart,
                tagContentType = TagType.HEART,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 5.dp, bottom = 5.dp)
            )
        }
        Spacer(modifier = Modifier.size(6.dp))
        Text(
            text = course.title,
            style = DateRoadTheme.typography.bodyBold17,
            color = DateRoadTheme.colors.black,
            modifier = Modifier
                .fillMaxWidth(),
            maxLines = 2,
            minLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.size(8.dp))
        Row {
            DateRoadImageTag(
                textContent = course.cost,
                imageContent = R.drawable.ic_all_money_12,
                tagContentType = TagType.MONEY
            )
            Spacer(modifier = Modifier.size(6.dp))
            DateRoadImageTag(
                textContent = course.duration,
                imageContent = R.drawable.ic_all_clock_12,
                tagContentType = TagType.TIME
            )
        }
    }
}

@Preview
@Composable
fun HomeHotCourseCardPreview() {
    Column {
        HomeHotCourseCard(
            course = Course(
                courseId = 1,
                thumbnail = "https://avatars.githubusercontent.com/u/103172971?v=4",
                city = "건대/성수/왕십리",
                title = "여기 야키니쿠 꼭 먹으러 가세요\n하지만 일본에 있습니다에 있습니다.",
                cost = "10만원 이상",
                duration = "10시간",
                like = "999"
            )
        )
    }
}
