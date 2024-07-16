package org.sopt.dateroad.presentation.ui.coursedetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import org.sopt.dateroad.R
import org.sopt.dateroad.presentation.type.ChipType
import org.sopt.dateroad.presentation.type.CourseDetailType
import org.sopt.dateroad.presentation.type.DateTagType.Companion.getDateTagTypeByName
import org.sopt.dateroad.presentation.type.EnrollType
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
import org.sopt.dateroad.presentation.ui.component.topbar.DateRoadBasicTopBar
import org.sopt.dateroad.presentation.ui.coursedetail.component.CourseDetailInfoBar
import org.sopt.dateroad.presentation.ui.coursedetail.component.GradientBoxWithText
import org.sopt.dateroad.presentation.util.modifier.noRippleClickable
import org.sopt.dateroad.presentation.util.view.LoadState
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun CourseDetailRoute(
    viewModel: CourseDetailViewModel = hiltViewModel(),
    popBackStack: () -> Unit,
    navigateToEnroll: (EnrollType, Int) -> Unit,
    courseDetailType: CourseDetailType,
    id: Int
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(Unit) {
        viewModel.setEvent(CourseDetailContract.CourseDetailEvent.InitCourseDetail(id = id, courseDetailType = courseDetailType))
    }

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { courseDetailSideEffect ->
                when (courseDetailSideEffect) {
                    is CourseDetailContract.CourseDetailSideEffect.NavigateToEnroll -> navigateToEnroll(EnrollType.TIMELINE, courseDetailSideEffect.id)
                }
            }
    }

    LaunchedEffect(uiState.id) {
        if (uiState.id != 0) {
            when (uiState.courseDetailType) {
                CourseDetailType.COURSE -> viewModel.fetchCourseDetail(uiState.id)
                CourseDetailType.ADVERTISEMENT -> viewModel.fetchAdvertisementDetail()
            }
        }
    }

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
                onLikeButtonClicked = {
                    if (uiState.id != 0) {
                        when (uiState.courseDetail.isUserLiked) {
                            true -> viewModel.deleteCourseLike(uiState.id)
                            false -> viewModel.postCourseLike(uiState.id)
                        }
                    }
                },
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
    var imageHeight by remember { mutableIntStateOf(0) }

    val pagerState = rememberPagerState()
    val scrollState = rememberLazyListState()
    val isTopBarTransparent by remember {
        derivedStateOf {
            scrollState.firstVisibleItemIndex == 0 && scrollState.firstVisibleItemScrollOffset < imageHeight
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            LazyColumn(
                state = scrollState,
                modifier = Modifier
                    .fillMaxSize()
                    .background(DateRoadTheme.colors.white),
                userScrollEnabled = true
            ) {
                item {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        HorizontalPager(
                            count = if (courseDetailUiState.courseDetailType == CourseDetailType.COURSE) courseDetailUiState.courseDetail.images.size else courseDetailUiState.advertisementDetail.images.size,
                            state = pagerState,
                            modifier = Modifier
                                .fillMaxWidth(),
                            userScrollEnabled = courseDetailUiState.courseDetailType == CourseDetailType.ADVERTISEMENT || courseDetailUiState.courseDetail.isAccess || courseDetailUiState.courseDetail.isCourseMine
                        ) { page ->
                            AsyncImage(
                                model = ImageRequest.Builder(context = LocalContext.current)
                                    .data(if (courseDetailUiState.courseDetailType == CourseDetailType.COURSE) courseDetailUiState.courseDetail.images[page] else courseDetailUiState.advertisementDetail.images[page])
                                    .crossfade(true)
                                    .build(),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(1f)
                                    .onGloballyPositioned { coordinates ->
                                        imageHeight = coordinates.size.height
                                    },
                                contentScale = ContentScale.Crop
                            )
                        }

                        if (courseDetailUiState.courseDetailType == CourseDetailType.COURSE) {
                            DateRoadImageTag(
                                textContent = courseDetailUiState.courseDetail.like.toString(),
                                imageContent = R.drawable.ic_tag_heart,
                                tagContentType = TagType.HEART,
                                modifier = Modifier
                                    .padding(start = 10.dp, bottom = 10.dp)
                                    .align(Alignment.BottomStart)
                            )
                        }

                        DateRoadTextTag(
                            textContent = stringResource(id = R.string.fraction_format, pagerState.currentPage + 1, courseDetailUiState.courseDetail.images.size),
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
                        if (courseDetailUiState.courseDetailType == CourseDetailType.ADVERTISEMENT) {
                            DateRoadTextTag(
                                textContent = courseDetailUiState.advertisementDetail.tag,
                                tagContentType = TagType.ADVERTISEMENT_TITLE
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                        Text(
                            text = if (courseDetailUiState.courseDetailType == CourseDetailType.COURSE) courseDetailUiState.courseDetail.date else courseDetailUiState.advertisementDetail.createAt,
                            style = DateRoadTheme.typography.bodySemi15,
                            color = DateRoadTheme.colors.gray400
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = if (courseDetailUiState.courseDetailType == CourseDetailType.COURSE) courseDetailUiState.courseDetail.title else courseDetailUiState.advertisementDetail.title,
                            style = DateRoadTheme.typography.titleExtra24,
                            color = DateRoadTheme.colors.black
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        if (courseDetailUiState.courseDetailType == CourseDetailType.COURSE) {
                            CourseDetailInfoBar(
                                totalTime = courseDetailUiState.courseDetail.totalTime,
                                totalCost = courseDetailUiState.courseDetail.totalCost,
                                city = courseDetailUiState.courseDetail.city
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                        when (courseDetailUiState.courseDetailType) {
                            CourseDetailType.COURSE -> {
                                when (courseDetailUiState.courseDetail.isCourseMine || courseDetailUiState.courseDetail.isAccess) {
                                    true -> {
                                        Text(
                                            text = courseDetailUiState.courseDetail.description,
                                            style = DateRoadTheme.typography.bodyMed13Context,
                                            color = DateRoadTheme.colors.black
                                        )
                                    }

                                    false -> {
                                        Column(
                                            modifier = Modifier
                                                .fillMaxSize(),
                                            horizontalAlignment = Alignment.CenterHorizontally
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
                                                    color = DateRoadTheme.colors.purple600,
                                                    modifier = Modifier.align(Alignment.CenterHorizontally)
                                                )
                                                Spacer(modifier = Modifier.height(24.dp))
                                                DateRoadFilledButton(
                                                    modifier = Modifier
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
                                                    enabledBackgroundColor = DateRoadTheme.colors.purple600,
                                                    enabledTextColor = DateRoadTheme.colors.white,
                                                    disabledBackgroundColor = DateRoadTheme.colors.gray200,
                                                    disabledTextColor = DateRoadTheme.colors.gray400,
                                                    cornerRadius = 14.dp,
                                                    paddingHorizontal = 52.dp,
                                                    paddingVertical = 16.dp
                                                )
                                                Spacer(modifier = Modifier.height(16.dp))
                                            }
                                        }
                                    }
                                }
                            }

                            CourseDetailType.ADVERTISEMENT -> {
                                Text(
                                    text = courseDetailUiState.advertisementDetail.description,
                                    style = DateRoadTheme.typography.bodyMed13Context,
                                    color = DateRoadTheme.colors.black
                                )
                                Spacer(modifier = Modifier.height(80.dp))
                            }
                        }
                    }
                }
                if (courseDetailUiState.courseDetailType == CourseDetailType.COURSE && courseDetailUiState.courseDetail.isCourseMine || courseDetailUiState.courseDetail.isAccess) {
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
                            Spacer(modifier = Modifier.height(12.dp))
                        }
                    }

                    items(courseDetailUiState.courseDetail.places.size) { index ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        ) {
                            DateRoadPlaceCard(
                                placeCardType = PlaceCardType.COURSE_NORMAL,
                                sequence = index,
                                place = courseDetailUiState.courseDetail.places[index]
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }

                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        ) {
                            Spacer(modifier = Modifier.height(14.dp))
                            Text(
                                text = stringResource(id = R.string.course_total_cost_string),
                                style = DateRoadTheme.typography.titleBold18,
                                color = DateRoadTheme.colors.black
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
                                courseDetailUiState.courseDetail.tags.forEach { tag ->
                                    tag.getDateTagTypeByName()?.let { tagType ->
                                        DateRoadImageChip(
                                            textId = tagType.titleRes,
                                            imageRes = tagType.imageRes,
                                            chipType = ChipType.DATE,
                                            isSelected = false
                                        )
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(38.dp))
                            if (!courseDetailUiState.courseDetail.isCourseMine) {
                                Spacer(modifier = Modifier.height(80.dp))
                            }
                        }
                    }
                }
            }
        }

        DateRoadBasicTopBar(
            title = "",
            backGroundColor = if (isTopBarTransparent) Color.Transparent else DateRoadTheme.colors.white,
            iconLeftResource = R.drawable.ic_top_bar_back_white,
            onIconClick = { onTopBarIconClicked() },
            buttonContent = {
                if (courseDetailUiState.courseDetail.isCourseMine && courseDetailUiState.courseDetailType == CourseDetailType.COURSE) {
                    Icon(
                        painterResource(id = R.drawable.btn_course_detail_more_white),
                        contentDescription = null,
                        tint = if (isTopBarTransparent) DateRoadTheme.colors.white else DateRoadTheme.colors.black,
                        modifier = Modifier.noRippleClickable { onEditBottomSheet() }
                    )
                }
            },
            leftTint = if (isTopBarTransparent) DateRoadTheme.colors.white else DateRoadTheme.colors.black
        )

        if (!courseDetailUiState.courseDetail.isCourseMine && courseDetailUiState.courseDetail.isAccess) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = DateRoadTheme.colors.white)
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 16.dp, vertical = 16.dp)
            ) {
                Row {
                    DateRoadImageButton(
                        iconResId = R.drawable.ic_coures_detail_heart_default,
                        enabledContentColor = DateRoadTheme.colors.purple600,
                        disabledContentColor = DateRoadTheme.colors.gray200,
                        enabledBackgroundColor = DateRoadTheme.colors.gray100,
                        disabledBackgroundColor = DateRoadTheme.colors.gray100,
                        isEnabled = courseDetailUiState.isLikedButtonChecked,
                        onClick = onLikeButtonClicked,
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
