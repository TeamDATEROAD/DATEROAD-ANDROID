package org.sopt.dateroad.presentation.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.R
import org.sopt.dateroad.domain.model.Course
import org.sopt.dateroad.presentation.type.MyCourseType
import org.sopt.dateroad.presentation.ui.component.item.DateRoadCourseCard
import org.sopt.dateroad.presentation.ui.component.topbar.DateRoadBasicTopBar
import org.sopt.dateroad.ui.theme.DATEROADTheme
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun MyCourseRoute(
    padding: PaddingValues,
    topBarTitle: String,
    courses: List<Course>
) {
    MyCourseScreen(padding, topBarTitle, courses)
}

@Composable
fun MyCourseScreen(
    padding: PaddingValues = PaddingValues(0.dp),
    topBarTitle: String,
    courses: List<Course>
) {
    Column(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize()
            .background(color = DateRoadTheme.colors.white)
    ) {
        DateRoadBasicTopBar(
            title = topBarTitle,
            iconLeftResource = R.drawable.ic_top_bar_back_white,
            backGroundColor = DateRoadTheme.colors.white
        )
        LazyColumn {
            items(courses.size) { index ->
                DateRoadCourseCard(
                    course = courses[index]
                )
            }
        }
    }
}

@Preview
@Composable
fun MyCourseScreenPreview() {
    val sampleCourses = listOf(
        Course(
            id = 1,
            url = "https://avatars.githubusercontent.com/u/103172971?v=4",
            city = "건대/성수/왕십리",
            title = "여기 야키니쿠 꼭 먹으러 가세요\n하지만 일본에 있습니다.",
            cost = "10만원 초과",
            duration = "10시간",
            like = "999"
        ),
        Course(
            id = 2,
            url = "https://avatars.githubusercontent.com/u/103172971?v=4",
            city = "홍대/신촌/이대",
            title = "여기는 꼭 가봐야 해요\n맛집 투어 코스!",
            cost = "5만원 초과",
            duration = "6시간",
            like = "520"
        ),
        Course(
            id = 3,
            url = "https://avatars.githubusercontent.com/u/103172971?v=4",
            city = "홍대/신촌/이대",
            title = "아~나도 데이트하고싶다~~ 데이트코스짜면 뭐하냐? 데이트할 시간이 없는데 내가 언제까지 이걸 해야하냐 ㅠㅠ",
            cost = "5만원 초과",
            duration = "6시간",
            like = "520"
        ),
        Course(
            id = 4,
            url = "https://avatars.githubusercontent.com/u/103172971?v=4",
            city = "홍대/신촌/이대",
            title = "여기는 꼭 가봐야 해요!",
            cost = "5만원 초과",
            duration = "6시간",
            like = "520"
        ),
        Course(
            id = 5,
            url = "https://avatars.githubusercontent.com/u/103172971?v=4",
            city = "홍대/신촌/이대",
            title = "여기는 꼭 가봐야 해요\n맛집 투어 코스!",
            cost = "5만원 초과",
            duration = "6시간",
            like = "520"
        ),
        Course(
            id = 6,
            url = "https://avatars.githubusercontent.com/u/103172971?v=4",
            city = "홍대/신촌/이대",
            title = "여기는 꼭 가봐야 해요\n맛집 투어 코스!",
            cost = "5만원 초과",
            duration = "6시간",
            like = "520"
        ),
        Course(
            id = 7,
            url = "https://avatars.githubusercontent.com/u/103172971?v=4",
            city = "홍대/신촌/이대",
            title = "여기는 꼭 가봐야 해요\n맛집 투어 코스!",
            cost = "5만원 초과",
            duration = "6시간",
            like = "520"
        ),
        Course(
            id = 8,
            url = "https://avatars.githubusercontent.com/u/103172971?v=4",
            city = "홍대/신촌/이대",
            title = "여기는 꼭 가봐야 해요\n맛집 투어 코스!",
            cost = "5만원 초과",
            duration = "6시간",
            like = "520"
        ),
        Course(
            id = 9,
            url = "https://avatars.githubusercontent.com/u/103172971?v=4",
            city = "홍대/신촌/이대",
            title = "그럼제가선배맘에 탕탕 후후루루루ㅜ루ㅜㅜ루",
            cost = "5만원 초과",
            duration = "6시간",
            like = "520"
        )
    )

    Column {
        DATEROADTheme {
            MyCourseScreen(
                topBarTitle = stringResource(id = MyCourseType.READ.topBarTitleRes),
                courses = sampleCourses
            )
        }

        DATEROADTheme {
            MyCourseScreen(
                topBarTitle = stringResource(id = MyCourseType.ENROLL.topBarTitleRes),
                courses = sampleCourses
            )
        }
    }
}
