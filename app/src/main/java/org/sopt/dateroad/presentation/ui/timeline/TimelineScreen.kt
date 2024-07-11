package org.sopt.dateroad.presentation.ui.timeline

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import org.sopt.dateroad.R
import org.sopt.dateroad.domain.model.Date
import org.sopt.dateroad.presentation.type.DateTagType
import org.sopt.dateroad.presentation.type.DateType
import org.sopt.dateroad.presentation.type.EmptyViewType
import org.sopt.dateroad.presentation.type.OneButtonDialogWithDescriptionType
import org.sopt.dateroad.presentation.ui.component.button.DateRoadFilledButton
import org.sopt.dateroad.presentation.ui.component.button.DateRoadImageButton
import org.sopt.dateroad.presentation.ui.component.dialog.DateRoadOneButtonDialogWithDescription
import org.sopt.dateroad.presentation.ui.component.emptyview.DateRoadEmptyView
import org.sopt.dateroad.presentation.ui.component.topbar.DateRoadLeftTitleTopBar
import org.sopt.dateroad.presentation.ui.timeline.component.TimelineCard
import org.sopt.dateroad.presentation.util.view.LoadState
import org.sopt.dateroad.ui.theme.DATEROADTheme
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun TimelineRoute(
    padding: PaddingValues,
    viewModel: TimelineViewModel = hiltViewModel(),
    navigateToPastDate: () -> Unit,
    navigateToEnroll: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(Unit) {
        viewModel.setEvent(TimelineContract.TimelineEvent.FetchTimeline)
    }

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is TimelineContract.TimelineSideEffect.MoveToEnroll -> navigateToPastDate()
                is TimelineContract.TimelineSideEffect.ShowMaxItemsModal -> {
                    viewModel.updateState { copy(showModal = true) }
                }
            }
        }
    }

    if (uiState.showModal) {
        DateRoadOneButtonDialogWithDescription(
            oneButtonDialogWithDescriptionType = OneButtonDialogWithDescriptionType.CANNOT_ENROLL_COURSE,
            onDismissRequest = { viewModel.updateState { copy(showModal = false) } },
            onClickConfirm = { viewModel.updateState { copy(showModal = false) } }
        )
    }

    when (uiState.loadState) {
        LoadState.Success -> {
            TimelineScreen(
                padding = padding,
                uiState = uiState,
                navigateToPastDate = navigateToPastDate,
                viewModel = viewModel,
                onAddDateCardClicked = { viewModel.setEvent(TimelineContract.TimelineEvent.AddDateCardClicked) }
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
    navigateToPastDate: () -> Unit,
    viewModel: TimelineViewModel,
    onAddDateCardClicked: () -> Unit
) {
    val pagerState = rememberPagerState()
    val configuration = LocalConfiguration.current
    val screenHeightDp = configuration.screenHeightDp.dp

    LaunchedEffect(pagerState.currentPage) {
        viewModel.setEvent(TimelineContract.TimelineEvent.PageChanged(pagerState.currentPage))
    }

    Column(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize()
            .background(color = DateRoadTheme.colors.white)
    ) {
        DateRoadLeftTitleTopBar(
            title = "데이트 일정",
            buttonContent = {
                DateRoadImageButton(
                    isEnabled = true,
                    onClick = { onAddDateCardClicked() },
                    cornerRadius = 14.dp,
                    paddingHorizontal = 16.dp,
                    paddingVertical = 8.dp
                )
            }
        )
        Spacer(modifier = Modifier.height(((screenHeightDp / 780) * 52)))

        if (uiState.dates.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f)
            ) {
                DateRoadEmptyView(
                    emptyViewType = EmptyViewType.TIMELINE
                )
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f)
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
                        modifier = Modifier
                            .padding(end = 16.dp)
                    )
                }

                Spacer(modifier = Modifier.height(((screenHeightDp / 780) * 30)))
                DotsIndicator(
                    totalDots = uiState.dates.size,
                    selectedIndex = pagerState.currentPage,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(((screenHeightDp / 780) * 20)))
            }
        }

        Column(
            modifier = Modifier
                .padding(start = 100.dp, end = 100.dp, bottom = ((screenHeightDp / 780) * 40))
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DateRoadFilledButton(
                isEnabled = false,
                textContent = stringResource(id = R.string.button_past_date),
                onClick = { navigateToPastDate() },
                textStyle = DateRoadTheme.typography.bodyBold15,
                enabledBackgroundColor = DateRoadTheme.colors.deepPurple,
                enabledTextColor = DateRoadTheme.colors.white,
                disabledBackgroundColor = DateRoadTheme.colors.gray100,
                disabledTextColor = DateRoadTheme.colors.black,
                cornerRadius = 10.dp,
                paddingHorizontal = 29.dp,
                paddingVertical = 11.dp
            )
        }
    }
}

@Composable
fun DotsIndicator(totalDots: Int, selectedIndex: Int, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        for (i in 0 until totalDots) {
            val color = if (i == selectedIndex) DateRoadTheme.colors.deepPurple else DateRoadTheme.colors.gray200
            Box(
                modifier = Modifier
                    .size(if (i == selectedIndex) 8.dp else 8.dp)
                    .clip(CircleShape)
                    .background(color)
            )
        }
    }
}

@Preview
@Composable
fun TimelineScreenPreview() {
    DATEROADTheme {
        TimelineScreen(
            padding = PaddingValues(0.dp),
            viewModel = TimelineViewModel(),
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
            navigateToPastDate = {},
            onAddDateCardClicked = {}
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
