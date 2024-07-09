package org.sopt.dateroad.presentation.ui.component.look

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import org.sopt.dateroad.R
import org.sopt.dateroad.domain.model.Course
import org.sopt.dateroad.presentation.type.MoneyTagType
import org.sopt.dateroad.presentation.type.TagType
import org.sopt.dateroad.presentation.ui.component.tag.DateRoadImageTag
import org.sopt.dateroad.presentation.util.modifier.noRippleClickable
import org.sopt.dateroad.ui.theme.DATEROADTheme
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun LookCourseCard(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    course: Course,
    onClick: (Int) -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .noRippleClickable(onClick = { onClick(course.id) })
    ) {
        Box {
            AsyncImage(
                model = ImageRequest.Builder(context = context)
                    .data(course.url)
                    .crossfade(true)
                    .build(),
                placeholder = null,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(158f / 140f)
                    .clip(RoundedCornerShape(14.dp))
            )
            DateRoadImageTag(
                textContent = course.like,
                imageContent = R.drawable.ic_tag_heart,
                tagContentType = TagType.HEART,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 6.dp, bottom = 5.dp)
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = course.city,
            color = DateRoadTheme.colors.gray400,
            style = DateRoadTheme.typography.bodyMed13
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = course.title,
            color = DateRoadTheme.colors.black,
            style = DateRoadTheme.typography.bodyBold15Course,
            maxLines = 2,
            minLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(5.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_all_money_12),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(id = course.cost.titleRes),
                color = DateRoadTheme.colors.gray400,
                style = DateRoadTheme.typography.capReg11
            )
            Spacer(modifier = Modifier.width(14.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_all_clock_12),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                modifier = Modifier.weight(1f),
                text = course.duration,
                color = DateRoadTheme.colors.gray400,
                style = DateRoadTheme.typography.capReg11
            )
        }
    }
}

@Preview
@Composable
fun LookCourseCardPreview() {
    DATEROADTheme {
        val courses = listOf(
            Course(
                id = 1,
                url = "https://avatars.githubusercontent.com/u/103172971?v=4",
                city = "건대/성수/왕십리",
                title = "성수동 당일치기 데이트 코스 둘러보러 가실까요?",
                cost = MoneyTagType.LESS_THAN_50000,
                duration = "10시간",
                like = "999"
            ),
            Course(
                id = 1,
                url = "https://avatars.githubusercontent.com/u/103172971?v=4",
                city = "홍대",
                title = "데로 파이띵 !",
                cost = MoneyTagType.LESS_THAN_100000,
                duration = "1시간",
                like = "3"
            )
        )

        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 16.dp), // 그리드의 외부 간격 설정
            horizontalArrangement = Arrangement.spacedBy(12.dp), // 열 간격 설정
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(courses.size) { index ->
                LookCourseCard(course = courses[index])
            }
        }
    }
}
