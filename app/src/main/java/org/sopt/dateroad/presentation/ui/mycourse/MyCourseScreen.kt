package org.sopt.dateroad.presentation.ui.mycourse

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import org.sopt.dateroad.R
import org.sopt.dateroad.domain.model.Course
import org.sopt.dateroad.presentation.type.EmptyViewType
import org.sopt.dateroad.presentation.type.EnrollType
import org.sopt.dateroad.presentation.type.MyCourseType
import org.sopt.dateroad.presentation.ui.component.emptyview.DateRoadEmptyView
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
    navigateToEnroll: (EnrollType, Int?) -> Unit,
    myCourseType: MyCourseType
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(Unit) {
        viewModel.setEvent(
            MyCourseContract.MyCourseEvent.SetMyCourseType(myCourseType = myCourseType)
        )
    }

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { myCourseSideEffect ->
                when (myCourseSideEffect) {
                    is MyCourseContract.MyCourseSideEffect.PopBackStack -> popBackStack()
                }
            }
    }

    LaunchedEffect(uiState.myCourseType) {
        when (uiState.myCourseType) {
            MyCourseType.ENROLL -> viewModel.fetchMyCourseEnroll()
            MyCourseType.READ -> viewModel.fetchMyCourseRead()
        }
    }

    when (uiState.loadState) {
        LoadState.Success -> {
            MyCourseScreen(
                padding = padding,
                myCourseUiState = uiState,
                onIconClick = popBackStack,
                onCourseCardClick = navigateToEnroll
            )
        }

        else -> Unit
    }
}

@Composable
fun MyCourseScreen(
    padding: PaddingValues,
    myCourseUiState: MyCourseContract.MyCourseUiState = MyCourseContract.MyCourseUiState(),
    onIconClick: () -> Unit,
    onCourseCardClick: (EnrollType, Int?) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize()
            .background(color = DateRoadTheme.colors.white)
    ) {
        DateRoadBasicTopBar(
            title = stringResource(id = myCourseUiState.myCourseType.topBarTitleRes),
            iconLeftResource = R.drawable.ic_top_bar_back_white,
            backGroundColor = DateRoadTheme.colors.white,
            onIconClick = onIconClick
        )
        LazyColumn {
            if (myCourseUiState.courses.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier
                            .fillParentMaxSize()
                    ) {
                        DateRoadEmptyView(
                            emptyViewType = when (myCourseUiState.myCourseType) {
                                MyCourseType.ENROLL -> EmptyViewType.MY_COURSE_ENROLL
                                MyCourseType.READ -> EmptyViewType.MY_COURSE_READ
                            }
                        )
                    }
                }
            }
            items(myCourseUiState.courses) { course ->
                DateRoadCourseCard(
                    course = course,
                    onClick = {
                        when (myCourseUiState.myCourseType) {
                            MyCourseType.ENROLL -> {}
                            MyCourseType.READ -> onCourseCardClick(EnrollType.TIMELINE, course.courseId)
                        }
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun MyCourseScreenPreview() {
    DATEROADTheme {
        MyCourseScreen(
            padding = PaddingValues(0.dp),
            myCourseUiState = MyCourseContract.MyCourseUiState(
                loadState = LoadState.Success,
                myCourseType = MyCourseType.READ,
                courses = listOf(
                    Course(
                        courseId = 1,
                        thumbnail = "https://avatars.githubusercontent.com/u/103172971?v=4",
                        city = "건대/성수/왕십리",
                        title = "여기 야키니쿠 꼭 먹으러 가세요\n하지만 일본에 있습니다.",
                        cost = "10만원 초과",
                        duration = "10시간",
                        like = "99999"
                    ),
                    Course(
                        courseId = 2,
                        thumbnail = "https://avatars.githubusercontent.com/u/103172971?v=4",
                        city = "부천",
                        title = "여기 야키니쿠 꼭 먹으러 가세요.",
                        cost = "10만원 초과",
                        duration = "10시간",
                        like = "999"
                    ),
                    Course(
                        courseId = 3,
                        thumbnail = "https://avatars.githubusercontent.com/u/103172971?v=4",
                        city = "건대/성수/왕십리",
                        title = "여기 야키니쿠 꼭 먹으러 가세요\n하지만 일본에 있습니다.하지만 일본에 있습니다.하지만 일본에 있습니다.하지만 일본에 있습니다.하지만 일본에 있습니다.",
                        cost = "10만원 초과",
                        duration = "10시간",
                        like = "999"
                    ),
                    Course(
                        courseId = 4,
                        thumbnail = "https://avatars.githubusercontent.com/u/103172971?v=4",
                        city = "건대/성수/왕십리",
                        title = "여기 야키니쿠 꼭 먹으러 가세요\n하지만 일본에 있습니다.",
                        cost = "10만원 초과",
                        duration = "10시간",
                        like = "999"
                    )
                )
            ),
            onIconClick = {},
            onCourseCardClick = { _, _ -> }
        )
    }
}
