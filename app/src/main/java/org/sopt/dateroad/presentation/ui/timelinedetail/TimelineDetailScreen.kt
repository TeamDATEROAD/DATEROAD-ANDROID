package org.sopt.dateroad.presentation.ui.timelinedetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import org.sopt.dateroad.R
import org.sopt.dateroad.domain.model.Place
import org.sopt.dateroad.domain.model.TimelineDetail
import org.sopt.dateroad.presentation.type.DateTagType.Companion.getDateTagTypeByName
import org.sopt.dateroad.presentation.type.EnrollType
import org.sopt.dateroad.presentation.type.PlaceCardType
import org.sopt.dateroad.presentation.type.TagType
import org.sopt.dateroad.presentation.type.TimelineType
import org.sopt.dateroad.presentation.type.TwoButtonDialogType
import org.sopt.dateroad.presentation.type.TwoButtonDialogWithDescriptionType
import org.sopt.dateroad.presentation.ui.component.bottomsheet.DateRoadBasicBottomSheet
import org.sopt.dateroad.presentation.ui.component.card.DateRoadPlaceCard
import org.sopt.dateroad.presentation.ui.component.dialog.DateRoadTwoButtonDialog
import org.sopt.dateroad.presentation.ui.component.dialog.DateRoadTwoButtonDialogWithDescription
import org.sopt.dateroad.presentation.ui.component.tag.DateRoadImageTag
import org.sopt.dateroad.presentation.ui.component.tag.DateRoadTextTag
import org.sopt.dateroad.presentation.ui.component.topbar.DateRoadBasicTopBar
import org.sopt.dateroad.presentation.ui.component.view.DateRoadErrorView
import org.sopt.dateroad.presentation.ui.component.view.DateRoadIdleView
import org.sopt.dateroad.presentation.ui.component.view.DateRoadLoadingView
import org.sopt.dateroad.presentation.util.modifier.noRippleClickable
import org.sopt.dateroad.presentation.util.view.LoadState
import org.sopt.dateroad.ui.theme.DATEROADTheme
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun TimelineDetailRoute(
    popBackStack: () -> Unit,
    navigateToEnroll: (EnrollType, Int) -> Unit,
    timelineId: Int,
    timelineType: TimelineType
) {
    val viewModel: TimelineDetailViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.fetchTimelineDetail(timelineId = timelineId)
    }

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is TimelineDetailContract.TimelineDetailSideEffect.PopBackStack -> popBackStack()
                    is TimelineDetailContract.TimelineDetailSideEffect.NavigateToEnroll -> navigateToEnroll(EnrollType.COURSE, sideEffect.id)
                }
            }
    }

    when (uiState.loadState) {
        LoadState.Idle -> DateRoadIdleView()

        LoadState.Loading -> DateRoadLoadingView()

        LoadState.Success -> {
            TimelineDetailScreen(
                uiState = uiState,
                timelineType = timelineType,
                onTopBarItemClick = popBackStack,
                onButtonClick = { viewModel.setEvent(TimelineDetailContract.TimelineDetailEvent.SetShowDeleteBottomSheet(true)) },
                showKakaoClicked = { viewModel.setEvent(TimelineDetailContract.TimelineDetailEvent.SetShowKakaoDialog(true)) },
                setShowKakaoDialog = { showKakaoDialog -> viewModel.setEvent(TimelineDetailContract.TimelineDetailEvent.SetShowKakaoDialog(showKakaoDialog)) },
                setShowDeleteBottomSheet = { showDeleteBottomSheet -> viewModel.setEvent(TimelineDetailContract.TimelineDetailEvent.SetShowDeleteBottomSheet(showDeleteBottomSheet)) },
                setShowDeleteDialog = { showDeleteDialog -> viewModel.setEvent(TimelineDetailContract.TimelineDetailEvent.SetShowDeleteDialog(showDeleteDialog)) },
                onDeleteConfirm = { viewModel.deleteTimeline(timelineId = timelineId) },
                onEnrollButtonClick = { id -> viewModel.setSideEffect(TimelineDetailContract.TimelineDetailSideEffect.NavigateToEnroll(id = id)) },
                onKakaoShareConfirm = { viewModel.setEvent(TimelineDetailContract.TimelineDetailEvent.ShareKakao(context, uiState.timelineDetail)) }
            )
        }

        LoadState.Error -> DateRoadErrorView()
    }

    when (uiState.deleteLoadState) {
        LoadState.Success -> popBackStack()
        else -> Unit
    }
}

@Composable
fun TimelineDetailScreen(
    uiState: TimelineDetailContract.TimelineDetailUiState,
    timelineType: TimelineType,
    onTopBarItemClick: () -> Unit = {},
    onButtonClick: () -> Unit = {},
    showKakaoClicked: () -> Unit = {},
    setShowKakaoDialog: (Boolean) -> Unit,
    setShowDeleteBottomSheet: (Boolean) -> Unit,
    setShowDeleteDialog: (Boolean) -> Unit,
    onDeleteConfirm: () -> Unit,
    onKakaoShareConfirm: () -> Unit,
    onEnrollButtonClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = timelineType.backgroundColor)
    ) {
        DateRoadBasicTopBar(
            title = stringResource(id = R.string.top_bar_title_timeline),
            leftIconResource = R.drawable.ic_top_bar_back_white,
            buttonContent = {
                Icon(
                    painterResource(id = R.drawable.btn_course_detail_more_black),
                    contentDescription = null,
                    modifier = Modifier.noRippleClickable(onClick = onButtonClick)
                )
            },
            onLeftIconClick = onTopBarItemClick
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(timelineType.backgroundColor)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.bg_past_card),
                contentDescription = null,
                tint = timelineType.lineColor,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 11.dp, start = 16.dp, end = 16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = uiState.timelineDetail.date,
                        style = DateRoadTheme.typography.bodyMed15,
                        color = DateRoadTheme.colors.black
                    )
                    if (uiState.timelineDetail.dDay != "") {
                        DateRoadTextTag(
                            textContent = uiState.timelineDetail.dDay,
                            tagContentType = TagType.TIMELINE_D_DAY
                        )
                    }
                }
                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = uiState.timelineDetail.title,
                    style = DateRoadTheme.typography.titleExtra24,
                    color = DateRoadTheme.colors.black,
                    minLines = 2,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = uiState.timelineDetail.city,
                    style = DateRoadTheme.typography.bodySemi15,
                    color = DateRoadTheme.colors.gray500
                )
                LazyRow(
                    modifier = Modifier
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.spacedBy(7.dp)
                ) {
                    items(uiState.timelineDetail.tags) { tag ->
                        tag.getDateTagTypeByName()?.let { tagType ->
                            DateRoadImageTag(
                                textContent = stringResource(id = tagType.titleRes),
                                imageContent = tagType.imageRes,
                                tagContentType = timelineType.tagType
                            )
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(18.dp))
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .fillMaxSize()
                .background(color = DateRoadTheme.colors.white)
        ) {
            Column(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 14.dp, bottom = 90.dp)
            ) {
                Text(
                    text = uiState.timelineDetail.startAt,
                    style = DateRoadTheme.typography.bodySemi15,
                    color = DateRoadTheme.colors.black
                )
                Spacer(modifier = Modifier.height(14.dp))
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(uiState.timelineDetail.places.size) { index ->
                        DateRoadPlaceCard(
                            placeCardType = PlaceCardType.COURSE_NORMAL,
                            sequence = index,
                            place = uiState.timelineDetail.places[index]
                        )
                    }
                }
            }

            if (uiState.timelineDetail.dDay.isNotEmpty()) {
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(vertical = 16.dp, horizontal = 70.dp)
                        .background(DateRoadTheme.colors.purple600, CircleShape)
                        .noRippleClickable(onClick = showKakaoClicked)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(top = 14.dp, bottom = 14.dp, start = 24.dp)
                                .background(DateRoadTheme.colors.kakaoYellow, CircleShape)
                                .clip(CircleShape)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_kakao_logo),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(6.dp)
                                    .background(DateRoadTheme.colors.kakaoYellow)
                                    .clip(CircleShape)
                            )
                        }
                        Text(
                            text = stringResource(id = R.string.one_button_dialog_with_description_share_kakao),
                            style = DateRoadTheme.typography.bodyBold15,
                            color = DateRoadTheme.colors.white,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 24.dp)
                        )
                    }
                }
            } else {
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(vertical = 16.dp, horizontal = 70.dp)
                        .background(DateRoadTheme.colors.purple600, CircleShape)
                        .noRippleClickable(onClick = { onEnrollButtonClick(uiState.timelineDetail.timelineId) })
                ) {
                    Text(
                        text = stringResource(id = R.string.timeline_detail_point),
                        style = DateRoadTheme.typography.bodyBold15,
                        color = DateRoadTheme.colors.white,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp, horizontal = 24.dp)
                    )
                }
            }
        }
    }
    if (uiState.showKakaoDialog) {
        DateRoadTwoButtonDialog(
            twoButtonDialogType = TwoButtonDialogType.OPEN_KAKAOTALK,
            onDismissRequest = { setShowKakaoDialog(false) },
            onClickConfirm = {
                setShowKakaoDialog(false)
                onKakaoShareConfirm()
            },
            onClickDismiss = { setShowKakaoDialog(false) }
        )
    }

    if (uiState.showDeleteBottomSheet) {
        DateRoadBasicBottomSheet(
            isBottomSheetOpen = true,
            title = stringResource(id = R.string.timeline_detail_bottom_sheet_title),
            isButtonEnabled = false,
            buttonText = stringResource(id = R.string.dialog_cancel),
            onButtonClick = { setShowDeleteBottomSheet(false) },
            itemList = listOf(
                stringResource(id = R.string.timeline_detail_delete) to { setShowDeleteDialog(true) }
            ),
            onDismissRequest = { setShowDeleteBottomSheet(false) }
        )
    }

    if (uiState.showDeleteDialog) {
        DateRoadTwoButtonDialogWithDescription(
            twoButtonDialogWithDescriptionType = TwoButtonDialogWithDescriptionType.DELETE_TIMELINE,
            onDismissRequest = { setShowDeleteDialog(false) },
            onClickConfirm = onDeleteConfirm,
            onClickDismiss = { setShowDeleteDialog(false) }
        )
    }
}

@Preview
@Composable
fun TimelineDetailScreenPreview() {
    DATEROADTheme {
        TimelineDetailScreen(
            uiState = TimelineDetailContract.TimelineDetailUiState(
                loadState = LoadState.Success,
                timelineDetail = TimelineDetail(
                    date = "2024-08-17",
                    dDay = "D-3",
                    title = "Seoul City Tour",
                    city = "Seoul",
                    startAt = "10:00 AM",
                    places = listOf(
                        Place(
                            title = "1번 데이트",
                            duration = "1.5"
                        ),
                        Place(
                            title = "2번 데이트",
                            duration = "2.5"
                        )
                    ),
                    tags = listOf("History", "Culture"),
                    timelineId = 123
                ),
                showKakaoDialog = false,
                showDeleteBottomSheet = false,
                showDeleteDialog = false,
                deleteLoadState = LoadState.Idle
            ),
            timelineType = TimelineType.getTimelineTypeByIndex(1),
            onTopBarItemClick = {},
            onButtonClick = {},
            showKakaoClicked = {},
            setShowKakaoDialog = {},
            setShowDeleteBottomSheet = {},
            setShowDeleteDialog = {},
            onDeleteConfirm = {},
            onKakaoShareConfirm = {},
            onEnrollButtonClick = {}
        )
    }
}
