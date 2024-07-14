package org.sopt.dateroad.presentation.ui.timelinedetail

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import org.sopt.dateroad.domain.model.DateDetail
import org.sopt.dateroad.domain.model.Place
import org.sopt.dateroad.presentation.type.DateType
import org.sopt.dateroad.presentation.type.PlaceCardType
import org.sopt.dateroad.presentation.type.TagType
import org.sopt.dateroad.presentation.type.TwoButtonDialogType
import org.sopt.dateroad.presentation.type.TwoButtonDialogWithDescriptionType
import org.sopt.dateroad.presentation.ui.component.bottomsheet.DateRoadBasicBottomSheet
import org.sopt.dateroad.presentation.ui.component.dialog.DateRoadTwoButtonDialog
import org.sopt.dateroad.presentation.ui.component.dialog.DateRoadTwoButtonDialogWithDescription
import org.sopt.dateroad.presentation.ui.component.placecard.DateRoadPlaceCard
import org.sopt.dateroad.presentation.ui.component.tag.DateRoadImageTag
import org.sopt.dateroad.presentation.ui.component.tag.DateRoadTextTag
import org.sopt.dateroad.presentation.ui.component.topbar.DateRoadBasicTopBar
import org.sopt.dateroad.presentation.util.modifier.noRippleClickable
import org.sopt.dateroad.presentation.util.view.LoadState
import org.sopt.dateroad.ui.theme.DATEROADTheme
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun TimelineDetailRoute(
    padding: PaddingValues,
    popBackStack: () -> Unit,
    dateId: Int,
    dateType: DateType,
    sourceScreen: Boolean,
) {
    val viewModel: TimelineDetailViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(Unit) {
        viewModel.fetchTimelineDetail(dateId)
        viewModel.setSourceScreen(sourceScreen)
    }

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is TimelineDetailContract.TimelineDetailSideEffect.PopBackStack -> popBackStack()
                }
            }
    }

    when (uiState.loadState) {
        LoadState.Success -> {
            TimelineDetailScreen(
                padding = padding,
                uiState = uiState,
                dateType = dateType,
                onTopBarItemClick = popBackStack,
                onButtonClick = { viewModel.setEvent(TimelineDetailContract.TimelineDetailEvent.SetShowDeleteBottomSheet(true)) },
                showKakaoClicked = { viewModel.setEvent(TimelineDetailContract.TimelineDetailEvent.SetShowKakaoDialog(true)) },
                setShowKakaoDialog = { showKakaoDialog -> viewModel.setEvent(TimelineDetailContract.TimelineDetailEvent.SetShowKakaoDialog(showKakaoDialog)) },
                setShowDeleteBottomSheet = { showDeleteBottomSheet -> viewModel.setEvent(TimelineDetailContract.TimelineDetailEvent.SetShowDeleteBottomSheet(showDeleteBottomSheet)) },
                setShowDeleteDialog = { showDeleteDialog -> viewModel.setEvent(TimelineDetailContract.TimelineDetailEvent.SetShowDeleteDialog(showDeleteDialog)) }
            )
        }

        else -> Unit
    }
}

@Composable
fun TimelineDetailScreen(
    padding: PaddingValues,
    uiState: TimelineDetailContract.TimelineDetailUiState,
    dateType: DateType,
    onTopBarItemClick: () -> Unit = {},
    onButtonClick: () -> Unit = {},
    showKakaoClicked: () -> Unit = {},
    setShowKakaoDialog: (Boolean) -> Unit,
    setShowDeleteBottomSheet: (Boolean) -> Unit,
    setShowDeleteDialog: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = dateType.backgroundColor)
    ) {
        DateRoadBasicTopBar(
            title = stringResource(id = R.string.top_bar_title_timeline),
            iconLeftResource = R.drawable.ic_top_bar_back_white,
            buttonContent = {
                Icon(
                    painterResource(id = R.drawable.btn_course_detail_more_black),
                    contentDescription = null,
                    modifier = Modifier.noRippleClickable(onClick = onButtonClick)
                )
            },
            onIconClick = onTopBarItemClick
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(dateType.backgroundColor)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.bg_past_card),
                contentDescription = null,
                tint = dateType.lineColor,
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
                        text = uiState.dateDetail.date,
                        style = DateRoadTheme.typography.bodyMed15,
                        color = DateRoadTheme.colors.black
                    )
                    if (uiState.sourceScreen) {
                        DateRoadTextTag(
                            textContent = stringResource(R.string.home_timeline_d_day, uiState.dateDetail.dDay),
                            tagContentType = TagType.TIMELINE_D_DAY
                        )
                    }
                }
                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = uiState.dateDetail.title,
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
                    text = uiState.dateDetail.city,
                    style = DateRoadTheme.typography.bodySemi15,
                    color = DateRoadTheme.colors.gray500
                )
                LazyRow(
                    modifier = Modifier
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.spacedBy(7.dp)
                ) {
                    items(uiState.dateDetail.tags) { tag ->
                        DateRoadImageTag(
                            textContent = stringResource(id = tag.titleRes),
                            imageContent = tag.imageRes,
                            tagContentType = dateType.tagType
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(18.dp))
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = DateRoadTheme.colors.white)
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 14.dp, bottom = 90.dp)
                    .align(Alignment.TopCenter),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(uiState.dateDetail.places) { place ->
                    DateRoadPlaceCard(
                        placeCardType = PlaceCardType.COURSE_NORMAL,
                        place = Place(sequence = place.sequence, title = place.title, duration = place.duration)
                    )
                }
            }

            if (uiState.sourceScreen) {
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(vertical = 16.dp, horizontal = 70.dp)
                        .background(DateRoadTheme.colors.deepPurple, CircleShape)
                        .noRippleClickable(onClick = showKakaoClicked)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(top = 14.dp, bottom = 14.dp, start = 24.dp)
                                .background(DateRoadTheme.colors.kakao, CircleShape)
                                .clip(CircleShape)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_kakao_logo),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(6.dp)
                                    .background(DateRoadTheme.colors.kakao)
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
                        .background(DateRoadTheme.colors.deepPurple, CircleShape)
                        // .noRippleClickable(onClick = { navigateToEnroll(uiState.dateDetail.dateId, EnrollType.COURSE) })
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
            onClickConfirm = { setShowKakaoDialog(false) },
            onClickDismiss = { setShowKakaoDialog(false) }
        )
    }

    if (uiState.showDeleteBottomSheet) {
        DateRoadBasicBottomSheet(
            isBottomSheetOpen = true,
            title = stringResource(id = R.string.region_bottom_sheet_set_timeline),
            isButtonEnabled = false,
            buttonText = stringResource(id = R.string.profile_bottom_sheet_button_text),
            itemList = listOf(
                stringResource(id = R.string.region_bottom_sheet_delete) to { setShowDeleteDialog(true) }
            ),
            onDismissRequest = { setShowDeleteBottomSheet(false) }
        )
    }

    if (uiState.showDeleteDialog) {
        DateRoadTwoButtonDialogWithDescription(
            twoButtonDialogWithDescriptionType = TwoButtonDialogWithDescriptionType.DELETE_TIMELINE,
            onDismissRequest = { setShowDeleteDialog(false) },
            onClickConfirm = { setShowDeleteDialog(false) },
            onClickDismiss = { setShowDeleteDialog(false) }
        )
    }
}

@Preview
@Composable
fun TimelineDetailScreenPreview() {
    DATEROADTheme {
        TimelineDetailScreen(
            padding = PaddingValues(0.dp),
            dateType = DateType.PINK,
            uiState = TimelineDetailContract.TimelineDetailUiState(
                loadState = LoadState.Success,
                dateDetail = DateDetail(
                    dateId = 0,
                    title = "",
                    startAt = "",
                    city = "",
                    dDay = "",
                    tags = emptyList(),
                    date = "",
                    places = emptyList()
                )
            ),
            onTopBarItemClick = {},
            onButtonClick = {},
            showKakaoClicked = {},
            setShowKakaoDialog = {},
            setShowDeleteBottomSheet = {},
            setShowDeleteDialog = {}
        )
    }
}
