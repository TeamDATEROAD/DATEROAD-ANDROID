package org.sopt.dateroad.presentation.ui.timeline

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import org.sopt.dateroad.R
import org.sopt.dateroad.domain.model.Date
import org.sopt.dateroad.presentation.type.DateTagType
import org.sopt.dateroad.presentation.type.DateType
import org.sopt.dateroad.presentation.type.EmptyViewType
import org.sopt.dateroad.presentation.type.EnrollType
import org.sopt.dateroad.presentation.type.OnboardingType
import org.sopt.dateroad.presentation.type.OneButtonDialogWithDescriptionType
import org.sopt.dateroad.presentation.ui.component.button.DateRoadFilledButton
import org.sopt.dateroad.presentation.ui.component.button.DateRoadImageButton
import org.sopt.dateroad.presentation.ui.component.dialog.DateRoadOneButtonDialogWithDescription
import org.sopt.dateroad.presentation.ui.component.dotsindicator.DotsIndicator
import org.sopt.dateroad.presentation.ui.component.emptyview.DateRoadEmptyView
import org.sopt.dateroad.presentation.ui.component.topbar.DateRoadLeftTitleTopBar
import org.sopt.dateroad.presentation.ui.timeline.component.TimelineCard
import org.sopt.dateroad.presentation.util.view.LoadState
import org.sopt.dateroad.ui.theme.DATEROADTheme
import org.sopt.dateroad.ui.theme.DateRoadTheme

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TimelineRoute(
    padding: PaddingValues,
    viewModel: TimelineViewModel = hiltViewModel(),
    navigateToTimelineDetail: (DateType, Int) -> Unit,
    navigateToEnroll: (EnrollType) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val pagerState = rememberPagerState()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(Unit) {
        viewModel.fetchTimeline()
    }

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is TimelineContract.TimelineSideEffect.NavigateToEnroll -> navigateToEnroll(EnrollType.TIMELINE)
                is TimelineContract.TimelineSideEffect.NavigateToTimelineDetail -> navigateToTimelineDetail(sideEffect.dateType, sideEffect.dateId)
            }
        }
    }

    LaunchedEffect(pagerState.currentPage) {
        viewModel.setEvent(TimelineContract.TimelineEvent.PageChanged(pagerState.currentPage))
    }

    when (uiState.loadState) {
        LoadState.Success -> {
            TimelineScreen(
                padding = padding,
                uiState = uiState,
                pagerState = pagerState,
                onAddDateCardClicked = { viewModel.setEvent(TimelineContract.TimelineEvent.AddDateCardClicked) },
                onDismissMaxDateCardDialog = { viewModel.setState { copy(showMaxDateCardModal = false) } },
                navigateToTimelineDetail = navigateToTimelineDetail
            )
        }

        else -> {}
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TimelineScreen(
    padding: PaddingValues,
    uiState: TimelineContract.TimelineUiState,
    pagerState: PagerState,
    navigateToTimelineDetail: (DateType, Int) -> Unit,
    onAddDateCardClicked: () -> Unit,
    onDismissMaxDateCardDialog: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize()
            .background(color = DateRoadTheme.colors.white)
    ) {
        DateRoadLeftTitleTopBar(
            title = stringResource(id = R.string.top_bar_title_timeline),
            buttonContent = {
                DateRoadImageButton(
                    isEnabled = true,
                    onClick = onAddDateCardClicked,
                    cornerRadius = 14.dp,
                    paddingHorizontal = 16.dp,
                    paddingVertical = 8.dp
                )
            }
        )
        Spacer(modifier = Modifier.height(52.dp))

        if (uiState.dates.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                DateRoadEmptyView(
                    emptyViewType = EmptyViewType.TIMELINE
                )
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            ) {
                HorizontalPager(
                    count = uiState.dates.size,
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = 35.dp)
                ) { page ->
                    val date = uiState.dates[page]
                    val dateType = getDateTypeByPosition(page)
                    TimelineCard(
                        dateCard = date,
                        dateType = dateType,
                        onClick = { navigateToTimelineDetail(dateType, date.dateId) },
                        modifier = Modifier
                            .padding(end = 16.dp)
                    )
                }

                Spacer(modifier = Modifier.height(30.dp))
                DotsIndicator(
                    totalDots = OnboardingType.entries.size,
                    selectedIndex = pagerState.currentPage,
                    indicatorSize = 8.dp,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(horizontal = 158.dp)
                )
            }
        }
        Column(
            modifier = Modifier
                .padding(bottom = 40.dp)
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DateRoadFilledButton(
                isEnabled = false,
                textContent = stringResource(id = R.string.button_past_date),
                onClick = {},
                textStyle = DateRoadTheme.typography.bodyBold15,
                enabledBackgroundColor = DateRoadTheme.colors.purple600,
                enabledTextColor = DateRoadTheme.colors.white,
                disabledBackgroundColor = DateRoadTheme.colors.gray100,
                disabledTextColor = DateRoadTheme.colors.black,
                cornerRadius = 10.dp,
                paddingHorizontal = 29.dp,
                paddingVertical = 11.dp
            )
        }
    }

    if (uiState.showMaxDateCardModal) {
        DateRoadOneButtonDialogWithDescription(
            oneButtonDialogWithDescriptionType = OneButtonDialogWithDescriptionType.CANNOT_ENROLL_COURSE,
            onDismissRequest = onDismissMaxDateCardDialog,
            onClickConfirm = onDismissMaxDateCardDialog
        )
    }
}

fun getDateTypeByPosition(position: Int): DateType {
    return when (position % 3) {
        0 -> DateType.PINK
        1 -> DateType.PURPLE
        else -> DateType.LIME
    }
}

@OptIn(ExperimentalPagerApi::class)
@Preview
@Composable
fun TimelineScreenPreview() {
    DATEROADTheme {
        TimelineScreen(
            padding = PaddingValues(0.dp),
            uiState = TimelineContract.TimelineUiState(
                loadState = LoadState.Success,
                dates = listOf(
                    Date(
                        dateId = 1,
                        dDay = "1",
                        title = "데이트 일정 1",
                        date = "JUNE.23",
                        city = "서울",
                        tags = listOf(DateTagType.SHOPPING)
                    ),
                    Date(
                        dateId = 2,
                        dDay = "2",
                        title = "데이트 일정 2",
                        date = "JUNE.23",
                        city = "부산",
                        tags = listOf(DateTagType.SHOPPING, DateTagType.EXHIBITION_POP_UP)
                    ),
                    Date(
                        dateId = 3,
                        dDay = "2",
                        title = "데이트 일정 2",
                        date = "JUNE.23",
                        city = "부산",
                        tags = listOf(DateTagType.SHOPPING, DateTagType.DRIVE, DateTagType.EXHIBITION_POP_UP)
                    )
                )
            ),
            pagerState = rememberPagerState(),
            navigateToTimelineDetail = { _, _ -> },
            onAddDateCardClicked = {},
            onDismissMaxDateCardDialog = {}
        )
    }
}
