package org.sopt.dateroad.presentation.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.sopt.dateroad.R
import org.sopt.dateroad.domain.model.MainDate
import org.sopt.dateroad.domain.type.SortByType
import org.sopt.dateroad.presentation.type.CourseDetailType
import org.sopt.dateroad.presentation.type.EnrollType
import org.sopt.dateroad.presentation.type.TagType
import org.sopt.dateroad.presentation.ui.component.button.DateRoadImageButton
import org.sopt.dateroad.presentation.ui.component.button.DateRoadTextButton
import org.sopt.dateroad.presentation.ui.component.card.DateRoadCourseCard
import org.sopt.dateroad.presentation.ui.component.partialcolortext.PartialColorText
import org.sopt.dateroad.presentation.ui.component.tag.DateRoadTextTag
import org.sopt.dateroad.presentation.ui.component.topbar.DateRoadHomeTopBar
import org.sopt.dateroad.presentation.ui.component.view.DateRoadErrorView
import org.sopt.dateroad.presentation.ui.component.view.DateRoadIdleView
import org.sopt.dateroad.presentation.ui.component.view.DateRoadLoadingView
import org.sopt.dateroad.presentation.ui.home.component.HomeAdvertisement
import org.sopt.dateroad.presentation.ui.home.component.HomeHotCourseCard
import org.sopt.dateroad.presentation.ui.home.component.HomeTimeLineCard
import org.sopt.dateroad.presentation.util.view.LoadState
import org.sopt.dateroad.ui.theme.DateRoadTheme

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeRoute(
    padding: PaddingValues,
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToPointHistory: () -> Unit,
    navigateToLook: () -> Unit,
    navigateToTimeline: () -> Unit,
    navigateToEnroll: (EnrollType, Int?) -> Unit,
    navigateToCourseDetail: (CourseDetailType, Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState()

    LaunchedEffect(Unit) {
        viewModel.fetchAdvertisements()
        viewModel.fetchSortedCourses(SortByType.POPULAR)
        viewModel.fetchSortedCourses(SortByType.LATEST)
        viewModel.fetchNearestDate()
        viewModel.fetchUserPoint()

        while (true) {
            delay(4000)
            coroutineScope.launch {
                if (uiState.advertisements.isNotEmpty()) {
                    val nextPage = (pagerState.currentPage + 1) % uiState.advertisements.size
                    pagerState.animateScrollToPage(nextPage)
                }
            }
        }
    }

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { homeSideEffect ->
                when (homeSideEffect) {
                    is HomeContract.HomeSideEffect.NavigateToPointHistory -> navigateToPointHistory()
                    is HomeContract.HomeSideEffect.NavigateToLook -> navigateToLook()
                    is HomeContract.HomeSideEffect.NavigateToTimeline -> navigateToTimeline()
                    is HomeContract.HomeSideEffect.NavigateToEnroll -> navigateToEnroll(homeSideEffect.enrollType, homeSideEffect.id)
                    is HomeContract.HomeSideEffect.NavigateToCourseDetail -> navigateToCourseDetail(homeSideEffect.courseDetailType, homeSideEffect.id)
                }
            }
    }

    when (uiState.loadState) {
        LoadState.Idle -> DateRoadIdleView()

        LoadState.Loading -> DateRoadLoadingView()

        LoadState.Success -> {
            HomeScreen(
                padding = padding,
                uiState = uiState,
                pagerState = pagerState,
                navigateToEnroll = { viewModel.setSideEffect(HomeContract.HomeSideEffect.NavigateToEnroll(EnrollType.TIMELINE, null)) },
                navigateToPointHistory = { viewModel.setSideEffect(HomeContract.HomeSideEffect.NavigateToPointHistory) },
                navigateToLook = { viewModel.setSideEffect(HomeContract.HomeSideEffect.NavigateToLook) },
                navigateToTimeline = { viewModel.setSideEffect(HomeContract.HomeSideEffect.NavigateToTimeline) },
                onFabClick = { viewModel.setSideEffect(HomeContract.HomeSideEffect.NavigateToEnroll(EnrollType.COURSE, null)) },
                navigateToCourseDetail = { courseDetailType: CourseDetailType, id: Int -> viewModel.setSideEffect(HomeContract.HomeSideEffect.NavigateToCourseDetail(courseDetailType, id)) }
            )
        }

        LoadState.Error -> DateRoadErrorView()
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen(
    padding: PaddingValues,
    uiState: HomeContract.HomeUiState,
    pagerState: PagerState,
    navigateToEnroll: () -> Unit,
    navigateToPointHistory: () -> Unit,
    navigateToLook: () -> Unit,
    navigateToTimeline: () -> Unit,
    navigateToCourseDetail: (CourseDetailType, Int) -> Unit,
    onFabClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize()
            .background(DateRoadTheme.colors.purple600)
            .verticalScroll(rememberScrollState())
    ) {
        DateRoadHomeTopBar(
            title = uiState.userPoint.point,
            profileImage = uiState.profileImageUrl,
            onClick = navigateToPointHistory
        )
        Row(
            modifier = Modifier.padding(start = 17.dp, end = 17.dp, top = 10.dp, bottom = 15.dp)
        ) {
            HomeTimeLineCard(
                mainDate = uiState.mainDate,
                onClick = if (uiState.mainDate == MainDate()) {
                    navigateToEnroll
                } else {
                    navigateToTimeline
                }
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                .background(color = DateRoadTheme.colors.white)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.height(17.dp))
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = PartialColorText(
                        stringResource(id = R.string.home_hot_date_course_title, uiState.userPoint.name),
                        keywords = listOf("오늘은", "이런 데이트 코스 어떠세요?"),
                        color = DateRoadTheme.colors.black
                    ),
                    color = DateRoadTheme.colors.purple600,
                    style = DateRoadTheme.typography.titleExtra24
                )
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.home_hot_date_course_description),
                        style = DateRoadTheme.typography.bodyMed13,
                        color = DateRoadTheme.colors.gray400
                    )
                    DateRoadTextButton(
                        textContent = stringResource(id = R.string.button_more),
                        textStyle = DateRoadTheme.typography.bodyBold13,
                        textColor = DateRoadTheme.colors.purple600,
                        paddingHorizontal = 20.dp,
                        paddingVertical = 8.dp,
                        onClick = navigateToLook
                    )
                }
                Spacer(modifier = Modifier.height(13.dp))
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(uiState.topLikedCourses) { topLikedCourses ->
                        HomeHotCourseCard(
                            course = topLikedCourses,
                            onClick = { navigateToCourseDetail(CourseDetailType.COURSE, topLikedCourses.courseId) }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    HorizontalPager(
                        count = uiState.advertisements.size,
                        state = pagerState,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) { page ->
                        HomeAdvertisement(
                            advertisement = uiState.advertisements[page],
                            onClick = { navigateToCourseDetail(CourseDetailType.ADVERTISEMENT, uiState.advertisements[page].advertisementId) }
                        )
                    }
                    DateRoadTextTag(
                        textContent = stringResource(
                            id = R.string.home_advertisement_number,
                            pagerState.currentPage + 1,
                            uiState.advertisements.size
                        ),
                        tagContentType = TagType.ADVERTISEMENT_PAGE_NUMBER,
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(end = 23.dp, bottom = 4.dp)
                    )
                }
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = stringResource(id = R.string.home_new_date_course_title),
                    style = DateRoadTheme.typography.titleExtra20,
                    color = DateRoadTheme.colors.black,
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .fillMaxWidth()
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.home_new_date_course_description),
                        style = DateRoadTheme.typography.bodyMed13,
                        color = DateRoadTheme.colors.gray400,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    DateRoadTextButton(
                        textContent = stringResource(id = R.string.button_more),
                        textStyle = DateRoadTheme.typography.bodyBold13,
                        textColor = DateRoadTheme.colors.purple600,
                        paddingHorizontal = 20.dp,
                        paddingVertical = 8.dp,
                        onClick = navigateToLook
                    )
                }

                uiState.latestCourses.forEach { latestCourses ->
                    DateRoadCourseCard(
                        course = latestCourses,
                        onClick = { navigateToCourseDetail(CourseDetailType.COURSE, latestCourses.courseId) }
                    )
                }
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        Alignment.BottomEnd
    ) {
        DateRoadImageButton(
            isEnabled = true,
            onClick = { onFabClick() },
            cornerRadius = 44.dp,
            paddingHorizontal = 16.dp,
            paddingVertical = 16.dp,
            modifier = Modifier
                .padding(16.dp)
        )
    }
}
