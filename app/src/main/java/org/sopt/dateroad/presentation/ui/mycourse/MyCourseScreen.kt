package org.sopt.dateroad.presentation.ui.mycourse

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import org.sopt.dateroad.R
import org.sopt.dateroad.domain.model.Course
import org.sopt.dateroad.presentation.type.EmptyViewType
import org.sopt.dateroad.presentation.ui.component.item.DateRoadCourseCard
import org.sopt.dateroad.presentation.ui.component.topbar.DateRoadBasicTopBar
import org.sopt.dateroad.presentation.util.view.LoadState
import org.sopt.dateroad.ui.theme.DATEROADTheme
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun MyCourseRoute(
    padding: PaddingValues,
    viewModel: MyCourseViewModel = hiltViewModel(),
    popBackStack: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(Unit) {
        viewModel.setEvent(MyCourseContract.MyCourseEvent.FetchMyCourses)
    }

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { myCourseSideEffect ->
                when (myCourseSideEffect) {
                    is MyCourseContract.MyCourseSideEffect.ShowError -> {
                    }
                }
            }
    }

    when (uiState.loadState) {
        LoadState.Success -> {
            MyCourseScreen(
                padding = padding,
                topBarTitle = "내가 등록한 코스",
                emptyImage = R.drawable.ic_empty_past, // 적절한 리소스로 대체
                emptyContent = "등록한 코스가 없습니다.",
                courses = uiState.courses,
                onIconClick = popBackStack
            )
        }

        else -> Unit
    }
}

@Composable
fun MyCourseScreen(
    padding: PaddingValues = PaddingValues(0.dp),
    topBarTitle: String,
    @DrawableRes emptyImage: Int,
    emptyContent: String,
    courses: List<Course>?,
    onIconClick: () -> Unit,
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
            backGroundColor = DateRoadTheme.colors.white,
            onIconClick = onIconClick
        )
        if (courses.isNullOrEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(79.dp))
                Image(
                    painter = painterResource(id = emptyImage),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Text(
                    text = emptyContent,
                    style = DateRoadTheme.typography.titleBold18,
                    color = DateRoadTheme.colors.gray500,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    textAlign = TextAlign.Center
                )
            }
        } else {
            LazyColumn {
                items(courses) { course ->
                    DateRoadCourseCard(
                        course = course
                    )
                }
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
        )
    )

    DATEROADTheme {
        MyCourseScreen(
            topBarTitle = "내가 등록한 코스",
            emptyImage = EmptyViewType.MY_COURSE_ENROLL.imageRes,
            emptyContent = "등록한 코스가 없습니다.",
            courses = sampleCourses,
            onIconClick = {}
        )
    }
}
