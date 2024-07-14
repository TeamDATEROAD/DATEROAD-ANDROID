package org.sopt.dateroad.presentation.ui.coursedetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import org.sopt.dateroad.R
import org.sopt.dateroad.presentation.type.ChipType
import org.sopt.dateroad.presentation.type.PlaceCardType
import org.sopt.dateroad.presentation.type.TagType
import org.sopt.dateroad.presentation.type.TwoButtonDialogWithDescriptionType
import org.sopt.dateroad.presentation.ui.component.bottomsheet.DateRoadBasicBottomSheet
import org.sopt.dateroad.presentation.ui.component.button.DateRoadBasicButton
import org.sopt.dateroad.presentation.ui.component.button.DateRoadFilledButton
import org.sopt.dateroad.presentation.ui.component.button.DateRoadImageButton
import org.sopt.dateroad.presentation.ui.component.chip.DateRoadImageChip
import org.sopt.dateroad.presentation.ui.component.dialog.DateRoadTwoButtonDialogWithDescription
import org.sopt.dateroad.presentation.ui.component.placecard.DateRoadPlaceCard
import org.sopt.dateroad.presentation.ui.component.tag.DateRoadImageTag
import org.sopt.dateroad.presentation.ui.component.tag.DateRoadTextTag
import org.sopt.dateroad.presentation.ui.coursedetail.component.CourseDetailInfoBar
import org.sopt.dateroad.presentation.ui.coursedetail.component.GradientBoxWithText
import org.sopt.dateroad.presentation.util.context.mapTagsToDateTagType
import org.sopt.dateroad.presentation.util.view.LoadState
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun CourseDetailRoute(
    viewModel: CourseDetailViewModel = hiltViewModel(),
    popBackStack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    when (uiState.loadState) {
        LoadState.Success -> {
            CourseDetailScreen(
                courseDetailUiState = uiState,
                onDialogPointLack = { viewModel.setEvent(CourseDetailContract.CourseDetailEvent.OnDialogPointLack) },
                dismissDialogPointLack = { viewModel.setEvent(CourseDetailContract.CourseDetailEvent.DismissDialogPointLack) },
                onDialogLookedForFree = { viewModel.setEvent(CourseDetailContract.CourseDetailEvent.OnDialogLookedForFree) },
                dismissDialogLookedForFree = { viewModel.setEvent(CourseDetailContract.CourseDetailEvent.DismissDialogLookedForFree) },
                onDialogLookedByPoint = { viewModel.setEvent(CourseDetailContract.CourseDetailEvent.OnDialogLookedByPoint) },
                dismissDialogLookedByPoint = { viewModel.setEvent(CourseDetailContract.CourseDetailEvent.DismissDialogLookedByPoint) },
                onLikeButtonClicked = { viewModel.setEvent(CourseDetailContract.CourseDetailEvent.OnLikeButtonClicked) },
                onDeleteButtonClicked = { viewModel.setEvent(CourseDetailContract.CourseDetailEvent.OnDeleteButtonClicked) },
                onEditBottomSheet = { viewModel.setEvent(CourseDetailContract.CourseDetailEvent.OnEditBottomSheet) },
                dismissEditBottomSheet = { viewModel.setEvent(CourseDetailContract.CourseDetailEvent.DismissEditBottomSheet) },
                enrollSchedule = { viewModel.setEvent(CourseDetailContract.CourseDetailEvent.EnrollSchedule) },
                onTopBarIconClicked = popBackStack,
                openCourseDetail = { viewModel.setEvent(CourseDetailContract.CourseDetailEvent.OpenCourse) }

            )
        }

        else -> Unit
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CourseDetailScreen(
    courseDetailUiState: CourseDetailContract.CourseDetailUiState,
    onDialogPointLack: () -> Unit,
    dismissDialogPointLack: () -> Unit,
    onDialogLookedForFree: () -> Unit,
    dismissDialogLookedForFree: () -> Unit,
    onDialogLookedByPoint: () -> Unit,
    dismissDialogLookedByPoint: () -> Unit,
    onLikeButtonClicked: () -> Unit,
    onDeleteButtonClicked: () -> Unit,
    onEditBottomSheet: () -> Unit,
    dismissEditBottomSheet: () -> Unit,
    enrollSchedule: () -> Unit,
    onTopBarIconClicked: () -> Unit,
    openCourseDetail: () -> Unit

) {
    val context = LocalContext.current
    val buttonText =
        if (courseDetailUiState.courseDetail.free > 0) {
            stringResource(id = R.string.course_detail_free_read_button, courseDetailUiState.courseDetail.free)
        } else {
            stringResource(id = R.string.course_detail_point_read_button)
        }
    val buttonDescription =
        if (courseDetailUiState.courseDetail.free > 0) {
            stringResource(id = R.string.course_detail_free_read_button_description)
        } else {
            stringResource(id = R.string.course_detail_point_read_button_description)
        }

    val pagerState = rememberPagerState()

    val mappedTags = context.mapTagsToDateTagType(courseDetailUiState.courseDetail.tags)

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .background(DateRoadTheme.colors.white),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            userScrollEnabled = true
        ) {
            item {
                Box(modifier = Modifier.fillMaxWidth()) {
                    HorizontalPager(
                        count = courseDetailUiState.courseDetail.imageList.size,
                        state = pagerState,
                        modifier = Modifier
                            .fillMaxWidth(),
                        userScrollEnabled = courseDetailUiState.courseDetail.isAccess
                    ) { page ->
                        Image(
                            painter = painterResource(id = courseDetailUiState.courseDetail.imageList[page]),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f)

                        )
                    }

                    Image(
                        painter = painterResource(id = R.drawable.ic_top_bar_back_white),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 11.dp)
                            .clickable { onTopBarIconClicked() }
                            .padding(top = 5.dp)
                    )

                    if (courseDetailUiState.courseDetail.isMine) {
                        Image(
                            painter = painterResource(id = R.drawable.btn_course_detail_more_white),
                            contentDescription = null,
                            modifier = Modifier
                                .clickable { onEditBottomSheet() }
                                .padding(top = 5.dp)
                                .align(Alignment.TopEnd)
                        )
                    }

                    DateRoadImageTag(
                        textContent = courseDetailUiState.courseDetail.like.toString(),
                        imageContent = R.drawable.ic_tag_heart,
                        tagContentType = TagType.HEART,
                        modifier = Modifier
                            .padding(start = 10.dp, bottom = 10.dp)
                            .align(Alignment.BottomStart)
                    )

                    DateRoadTextTag(
                        textContent = "${pagerState.currentPage + 1}/${courseDetailUiState.courseDetail.imageList.size}",
                        tagContentType = TagType.COURSE_DETAIL_PHOTO_NUMBER,
                        modifier = Modifier
                            .padding(end = 10.dp, bottom = 10.dp)
                            .align(Alignment.BottomEnd)
                    )
                }
            }

            item {
                Column(
                    modifier = Modifier
                        .background(DateRoadTheme.colors.white)
                        .padding(horizontal = 16.dp)
                        .padding(top = 18.dp)

                ) {
                    Text(
                        text = courseDetailUiState.courseDetail.date,
                        style = DateRoadTheme.typography.bodySemi15,
                        color = DateRoadTheme.colors.gray400
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = courseDetailUiState.courseDetail.title,
                        style = DateRoadTheme.typography.titleExtra24,
                        color = DateRoadTheme.colors.black
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    CourseDetailInfoBar(
                        totalTime = courseDetailUiState.courseDetail.totalTime,
                        totalCost = courseDetailUiState.courseDetail.totalCost,
                        city = courseDetailUiState.courseDetail.city

                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    if (courseDetailUiState.courseDetail.isAccess) {
                        Text(
                            text = courseDetailUiState.courseDetail.description,
                            style = DateRoadTheme.typography.bodyMed13Context,
                            color = DateRoadTheme.colors.black
                        )
                    } else {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            GradientBoxWithText(text = courseDetailUiState.courseDetail.description)
                            Column {
                                Spacer(modifier = Modifier.height(8.dp))
                                Image(
                                    painter = painterResource(id = R.drawable.ic_course_detail_is_not_access),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .align(Alignment.CenterHorizontally)
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = stringResource(id = R.string.course_detail_unopened_title),
                                    style = DateRoadTheme.typography.bodyBold17,
                                    color = DateRoadTheme.colors.black,
                                    modifier = Modifier.align(Alignment.CenterHorizontally)
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = buttonDescription,
                                    style = DateRoadTheme.typography.bodySemi15,
                                    color = DateRoadTheme.colors.deepPurple,
                                    modifier = Modifier.align(Alignment.CenterHorizontally)
                                )
                                Spacer(modifier = Modifier.height(24.dp))
                                DateRoadFilledButton(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 52.dp)
                                        .align(Alignment.CenterHorizontally),
                                    isEnabled = true,
                                    textContent = buttonText,
                                    onClick = {
                                        if (courseDetailUiState.courseDetail.free > 0) {
                                            onDialogLookedForFree()
                                        } else {
                                            onDialogLookedByPoint()
                                        }
                                    },
                                    textStyle = DateRoadTheme.typography.bodyBold15,
                                    enabledBackgroundColor = DateRoadTheme.colors.deepPurple,
                                    enabledTextColor = DateRoadTheme.colors.white,
                                    disabledBackgroundColor = DateRoadTheme.colors.gray200,
                                    disabledTextColor = DateRoadTheme.colors.gray400,
                                    cornerRadius = 14.dp,
                                    paddingHorizontal = 0.dp,
                                    paddingVertical = 16.dp
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                            }
                        }
                    }
                }
            }
            if (courseDetailUiState.courseDetail.isAccess) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        Spacer(modifier = Modifier.height(30.dp))
                        Text(
                            text = stringResource(id = R.string.course_detail_timeline_title),
                            style = DateRoadTheme.typography.titleBold18,
                            color = DateRoadTheme.colors.black
                        )
                    }
                }

                items(courseDetailUiState.courseDetail.places) { place ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        DateRoadPlaceCard(
                            placeCardType = PlaceCardType.COURSE_NORMAL,
                            place = place
                        )
                    }
                }

                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = stringResource(id = R.string.course_total_cost_string),
                            style = DateRoadTheme.typography.titleBold18,
                            color = DateRoadTheme.colors.gray100
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = courseDetailUiState.courseDetail.totalCost,
                            style = DateRoadTheme.typography.titleBold18,
                            color = DateRoadTheme.colors.black,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(10.dp))
                                .background(DateRoadTheme.colors.gray100)
                                .padding(start = 20.dp, top = 15.dp, end = 5.dp, bottom = 17.dp)
                        )
                        Spacer(modifier = Modifier.height(30.dp))
                        Text(
                            text = stringResource(id = R.string.course_detail_tag),
                            style = DateRoadTheme.typography.titleBold18,
                            color = DateRoadTheme.colors.black
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        Row(horizontalArrangement = Arrangement.spacedBy(7.dp)) {
                            mappedTags.forEach { tag ->
                                DateRoadImageChip(
                                    textId = tag.titleRes,
                                    imageRes = tag.imageRes,
                                    chipType = ChipType.DATE,
                                    isSelected = false
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(38.dp))
                        if (!courseDetailUiState.courseDetail.isMine) {
                            Spacer(modifier = Modifier.height(80.dp))
                        }
                    }
                }
            }
        }

        if (!courseDetailUiState.courseDetail.isMine && courseDetailUiState.courseDetail.isAccess) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 16.dp, vertical = 16.dp)
            ) {
                Row {
                    DateRoadImageButton(
                        iconResId = R.drawable.ic_coures_detail_heart_default,
                        enabledContentColor = DateRoadTheme.colors.deepPurple,
                        disabledContentColor = DateRoadTheme.colors.gray200,
                        enabledBackgroundColor = DateRoadTheme.colors.gray100,
                        disabledBackgroundColor = DateRoadTheme.colors.gray100,
                        isEnabled = courseDetailUiState.isLikedButtonChecked,
                        onClick = { onLikeButtonClicked() },
                        cornerRadius = 14.dp,
                        paddingHorizontal = 23.dp,
                        paddingVertical = 18.dp
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    DateRoadBasicButton(
                        modifier = Modifier.weight(1f),
                        isEnabled = true,
                        textContent = stringResource(id = R.string.course_detail_get_course),
                        onClick = { enrollSchedule() }
                    )
                }
            }
        }
    }

    if (courseDetailUiState.isPointReadDialogOpen) {
        DateRoadTwoButtonDialogWithDescription(
            twoButtonDialogWithDescriptionType = TwoButtonDialogWithDescriptionType.READ_COURSE,
            onDismissRequest = { dismissDialogLookedByPoint() },
            onClickConfirm = {
                onDialogLookedByPoint()
                if (courseDetailUiState.courseDetail.totalPoint < 50) {
                    onDialogPointLack()
                } else {
                    openCourseDetail()
                }
            },
            onClickDismiss = { dismissDialogLookedByPoint() }
        )
    }

    if (courseDetailUiState.isPointLackDialogOpen) {
        DateRoadTwoButtonDialogWithDescription(
            twoButtonDialogWithDescriptionType = TwoButtonDialogWithDescriptionType.POINT_LACK,
            onDismissRequest = { dismissDialogPointLack() },
            onClickConfirm = { dismissDialogPointLack() },
            onClickDismiss = { dismissDialogPointLack() }
        )
    }

    if (courseDetailUiState.isFreeReadDialogOpen) {
        DateRoadTwoButtonDialogWithDescription(
            twoButtonDialogWithDescriptionType = TwoButtonDialogWithDescriptionType.FREE_READ,
            onDismissRequest = { dismissDialogLookedForFree() },
            onClickConfirm = {
                dismissDialogLookedForFree()
                openCourseDetail()
            },
            onClickDismiss = { dismissDialogLookedForFree() }
        )
    }

    DateRoadBasicBottomSheet(
        isBottomSheetOpen = courseDetailUiState.isEditBottomSheetOpen,
        title = stringResource(id = R.string.course_detail_bottom_sheet_title),
        isButtonEnabled = false,
        buttonText = stringResource(id = R.string.course_detail_bottom_sheet_delete),
        itemList = listOf(
            stringResource(id = R.string.course_detail_bottom_sheet_confirm) to { }
        ),
        onDismissRequest = { dismissEditBottomSheet() },
        onButtonClick = { onDeleteButtonClicked() }
    )
}
