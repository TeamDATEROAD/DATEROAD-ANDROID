package org.sopt.dateroad.presentation.ui.timeline

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.R
import org.sopt.dateroad.domain.model.Course
import org.sopt.dateroad.presentation.ui.component.topbar.DateRoadBasicTopBar
import org.sopt.dateroad.ui.theme.DATEROADTheme
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun TimelineRoute(
    padding: PaddingValues
) {
    TimelineScreen()
}

@Composable
fun TimelineScreen(
    padding: PaddingValues = PaddingValues(0.dp)
) {
    Column(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize()
            .background(color = DateRoadTheme.colors.white)
    ) {
        DateRoadBasicTopBar(
            title = "topBarTitle",
            iconLeftResource = R.drawable.ic_top_bar_back_white,
            backGroundColor = DateRoadTheme.colors.white
        )
    }
}

@Preview()
@Composable
fun TimelineScreenPreview() {
    val sampleCourses = listOf(
        Course(
            id = 1,
            url = "https://avatars.githubusercontent.com/u/103172971?v=4",
            city = "건대/성수/왕십리",
            title = "여기 야키니쿠 꼭 먹으러 가세요\n하지만 일본에 있습니다.",
            cost = "10만원 초과",
            duration = "10시간",
            like = "999"
        )
    )
    DATEROADTheme {
        TimelineScreen()
    }
}
