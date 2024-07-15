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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import org.sopt.dateroad.R
import org.sopt.dateroad.domain.model.Advertisement
import org.sopt.dateroad.domain.model.Course
import org.sopt.dateroad.domain.model.MainDate
import org.sopt.dateroad.presentation.type.CourseDetailType
import org.sopt.dateroad.presentation.type.EnrollType
import org.sopt.dateroad.presentation.type.TagType
import org.sopt.dateroad.presentation.ui.component.button.DateRoadImageButton
import org.sopt.dateroad.presentation.ui.component.button.DateRoadTextButton
import org.sopt.dateroad.presentation.ui.component.item.DateRoadCourseCard
import org.sopt.dateroad.presentation.ui.component.item.HomeAdvertisement
import org.sopt.dateroad.presentation.ui.component.item.HomeHotCourseCard
import org.sopt.dateroad.presentation.ui.component.item.HomeTimeLineCard
import org.sopt.dateroad.presentation.ui.component.partialcolortext.PartialColorText
import org.sopt.dateroad.presentation.ui.component.tag.DateRoadTextTag
import org.sopt.dateroad.presentation.ui.component.topbar.DateRoadHomeTopBar
import org.sopt.dateroad.presentation.util.view.LoadState
import org.sopt.dateroad.ui.theme.DATEROADTheme
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun HomeRoute(
    padding: PaddingValues,
    navigateToPointHistory: () -> Unit,
    navigateToLook: () -> Unit,
    navigateToTimeline: () -> Unit,
    navigateToEnroll: (EnrollType, Int?) -> Unit,
    navigateToCourseDetail: (CourseDetailType, Int) -> Unit
) {
    val viewModel: HomeViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(Unit) {
        viewModel.fetchProfile()
        viewModel.fetchAdvertisements()
        viewModel.fetchLatestCourses()
        viewModel.fetchRemainingPoints()
        viewModel.fetchTopLikedCourses()
        viewModel.fetchMainDate()
        viewModel.fetchUserName()
    }

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is HomeContract.HomeSideEffect.NavigateToCourseListPage -> TODO()
                    is HomeContract.HomeSideEffect.NavigateToCourseDetailPage -> TODO()
                    is HomeContract.HomeSideEffect.NavigateToDateDetailPage -> TODO()
                    is HomeContract.HomeSideEffect.NavigateToTimeline -> TODO()
                    is HomeContract.HomeSideEffect.NavigateToEditorPickPage -> TODO()
                    is HomeContract.HomeSideEffect.NavigateToPointHistoryPage -> TODO()
                }
            }
    }

    LaunchedEffect(Unit) {
        while (true) {
            delay(4000)
            val nextPage = (uiState.currentBannerPage + 1) % uiState.advertisements.size
            viewModel.changeBannerPage(nextPage)
        }
    }

    when (uiState.loadState) {
        LoadState.Success -> {
            HomeScreen(
                padding = padding,
                uiState = uiState,
                navigateToPointHistory = navigateToPointHistory,
                navigateToLook = navigateToLook,
                navigateToTimeline = navigateToTimeline,
                onFabClick = navigateToEnroll,
                navigateToCourseDetail = { courseDetailType: CourseDetailType, id: Int -> navigateToCourseDetail(courseDetailType, id) }
            )
        }

        else -> Unit
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen(
    padding: PaddingValues,
    uiState: HomeContract.HomeUiState,
    onMainDateClick: () -> Unit = {},
    onEnrollClick: () -> Unit = {},
    onDateCourseClick: (Int) -> Unit = {},
    onAdvertisementClick: (Int) -> Unit = {},
    navigateToPointHistory: () -> Unit,
    navigateToLook: () -> Unit,
    navigateToTimeline: () -> Unit,
    onFabClick: () -> Unit = {}
) {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(uiState.currentBannerPage) {
        pagerState.scrollToPage(uiState.currentBannerPage)
    }

    Column(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize()
            .background(DateRoadTheme.colors.purple600)
            .verticalScroll(rememberScrollState())
    ) {
        DateRoadHomeTopBar(
            title = stringResource(id = R.string.home_main_date_point, uiState.remainingPoints),
            onClick = navigateToPointHistory
        )
        Row(
            modifier = Modifier.padding(start = 17.dp, end = 17.dp, top = 10.dp, bottom = 15.dp)
        ) {
            HomeTimeLineCard(
                mainDate = uiState.mainDate,
                onClick = if (uiState.mainDate == null) {
                    onEnrollClick
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
                Column(
                    modifier = Modifier
                        .padding(start = 16.dp)

                ) {
                    Spacer(modifier = Modifier.height(17.dp))
                    Text(
                        text = PartialColorText(
                            stringResource(id = R.string.home_hot_date_course_title, uiState.userName),
                            keywords = listOf("오늘은", "이런 데이트 코스 어떠세요?"),
                            color = DateRoadTheme.colors.black
                        ),
                        color = DateRoadTheme.colors.purple600,
                        style = DateRoadTheme.typography.titleExtra24
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
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
                            textStyle = DateRoadTheme.typography.bodyMed13,
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
                                onClick = { onDateCourseClick(topLikedCourses.id) }
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(30.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 16.dp)
                    ) {
                        HorizontalPager(
                            count = uiState.advertisements.size,
                            state = pagerState,
                            modifier = Modifier.fillMaxWidth()
                        ) { page ->
                            HomeAdvertisement(
                                advertisement = uiState.advertisements[page],
                                onClick = { onAdvertisementClick(uiState.advertisements[page].advertisementId) }
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
                                .padding(6.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(32.dp))
                    Text(
                        text = stringResource(id = R.string.home_new_date_course_title),
                        style = DateRoadTheme.typography.titleExtra20,
                        color = DateRoadTheme.colors.black,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(id = R.string.home_new_date_course_description),
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
                }
                uiState.latestCourses.forEach { latestCourses ->
                    DateRoadCourseCard(
                        course = latestCourses,
                        onClick = { onDateCourseClick(latestCourses.id) }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
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
                onClick = onFabClick,
                cornerRadius = 44.dp,
                paddingHorizontal = 16.dp,
                paddingVertical = 16.dp,
                modifier = Modifier
                    .padding(16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    DATEROADTheme {
        HomeScreen(
            padding = PaddingValues(0.dp),
            navigateToPointHistory = {},
            navigateToLook = {},
            navigateToTimeline = {},
            navigateToCourseDetail = { _, _ -> },
            uiState = HomeContract.HomeUiState(
                loadState = LoadState.Success,
                mainDate = MainDate(
                    dateId = 1,
                    dDay = "3",
                    dateName = "부천 데이트",
                    month = 6,
                    day = 23,
                    startAt = "14:00 PM"
                ),
                topLikedCourses = listOf(
                    Course(
                        id = 1,
                        url = "https://avatars.githubusercontent.com/u/103172971?v=4",
                        city = "Seoul",
                        title = "Beautiful Seoul Tour",
                        cost = "$100",
                        duration = "4 hours",
                        like = "150"
                    ),
                    Course(
                        id = 2,
                        url = "https://avatars.githubusercontent.com/u/103172971?v=4",
                        city = "Busan",
                        title = "Amazing Busan Trip",
                        cost = "$120",
                        duration = "6 hours",
                        like = "200"
                    )
                ),
                latestCourses = listOf(
                    Course(
                        id = 3,
                        url = "https://i.namu.wiki/i/gA_FoJIHIwSsBvHRiiR-k11sjIVKV_tibI5c7o4NAGTOS4KHLpJ9sMwm93qc5eH5cL7Vm0j6XQFT_ZdOZgZ_zJ86fAqfqk24VZivOZMTBUOiO_Tk3oa45R3AQzIYSXOrbvkAMcukVFInmo4d8MvCdA.webp",
                        city = "Incheon",
                        title = "Incheon Day Tour",
                        cost = "$80",
                        duration = "5 hours",
                        like = "100"
                    ),
                    Course(
                        id = 4,
                        url = "https://i.namu.wiki/i/gA_FoJIHIwSsBvHRiiR-k11sjIVKV_tibI5c7o4NAGTOS4KHLpJ9sMwm93qc5eH5cL7Vm0j6XQFT_ZdOZgZ_zJ86fAqfqk24VZivOZMTBUOiO_Tk3oa45R3AQzIYSXOrbvkAMcukVFInmo4d8MvCdA.webp",
                        city = "Jeju",
                        title = "Jeju Island Adventure",
                        cost = "$150",
                        duration = "8 hours",
                        like = "300"
                    ),
                    Course(
                        id = 4,
                        url = "https://i.namu.wiki/i/gA_FoJIHIwSsBvHRiiR-k11sjIVKV_tibI5c7o4NAGTOS4KHLpJ9sMwm93qc5eH5cL7Vm0j6XQFT_ZdOZgZ_zJ86fAqfqk24VZivOZMTBUOiO_Tk3oa45R3AQzIYSXOrbvkAMcukVFInmo4d8MvCdA.webp",
                        city = "Jeju",
                        title = "Jeju Island Adventure",
                        cost = "$150",
                        duration = "8 hours",
                        like = "300"
                    )
                ),
                advertisements = listOf(
                    Advertisement(
                        advertisementId = 1,
                        imageUrl = "https://i.namu.wiki/i/wXGU6DZbHowc6IB0GYPJpcmdDkLO3TW3MHzjg63jcTJvIzaBKhYqR0l9toBMHTv2OSU4eFKfPOlfrSQpymDJlA.webp"
                    ),
                    Advertisement(
                        advertisementId = 2,
                        imageUrl = "https://i.namu.wiki/i/wXGU6DZbHowc6IB0GYPJpcmdDkLO3TW3MHzjg63jcTJvIzaBKhYqR0l9toBMHTv2OSU4eFKfPOlfrSQpymDJlA.webp"
                    )
                ),
                userName = "현진",
                remainingPoints = 100,
                currentBannerPage = 0
            )
        )
    }
}
