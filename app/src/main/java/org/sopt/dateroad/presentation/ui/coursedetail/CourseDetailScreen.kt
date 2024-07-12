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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import org.sopt.dateroad.R
import org.sopt.dateroad.domain.model.CourseDetail
import org.sopt.dateroad.domain.model.Place
import org.sopt.dateroad.presentation.type.ChipType
import org.sopt.dateroad.presentation.type.PlaceCardType
import org.sopt.dateroad.presentation.type.TagType
import org.sopt.dateroad.presentation.type.TwoButtonDialogWithDescriptionType
import org.sopt.dateroad.presentation.ui.component.bottomsheet.DateRoadBasicBottomSheet
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
import org.sopt.dateroad.ui.theme.DateRoadTheme

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CourseDetailScreen(
    courseDetail: CourseDetail
) {
    var isBottomSheetOpen by rememberSaveable { mutableStateOf(false) }
    var readCourseDialog by rememberSaveable { mutableStateOf(false) }
    var pointLackDialog by rememberSaveable { mutableStateOf(false) }
    var freeReadDialog by rememberSaveable { mutableStateOf(false) }

    val context = LocalContext.current
    val buttonText =
        if (courseDetail.free > 0) {
            stringResource(id = R.string.course_detail_free_read_button)
        } else {
            stringResource(id = R.string.course_detail_point_read_button)
        }
    val pagerState = rememberPagerState()

    val mappedTags = context.mapTagsToDateTagType(courseDetail.tags)

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
                        count = courseDetail.imageList.size,
                        state = pagerState,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f),
                        userScrollEnabled = courseDetail.isAccess // Disable scrolling in pager if isAccess is false
                    ) { page ->
                        Image(
                            painter = painterResource(id = courseDetail.imageList[page]),
                            contentDescription = null,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    Image(
                        painter = painterResource(id = R.drawable.ic_top_bar_back_white),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 11.dp)
                            .clickable { }
                            .padding(top = 5.dp)
                    )

                    if (courseDetail.isMine) {
                        Image(
                            painter = painterResource(id = R.drawable.btn_course_detail_more_white),
                            contentDescription = null,
                            modifier = Modifier
                                .clickable { isBottomSheetOpen = true }
                                .padding(top = 5.dp)
                                .align(Alignment.TopEnd)
                        )
                    }

                    DateRoadImageTag(
                        textContent = courseDetail.like.toString(),
                        imageContent = R.drawable.ic_tag_heart,
                        tagContentType = TagType.HEART,
                        modifier = Modifier
                            .padding(start = 10.dp, bottom = 10.dp)
                            .align(Alignment.BottomStart)
                    )

                    DateRoadTextTag(
                        textContent = "${pagerState.currentPage + 1}/${courseDetail.imageList.size}",
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
                        text = courseDetail.date,
                        style = DateRoadTheme.typography.bodySemi15,
                        color = DateRoadTheme.colors.gray400
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "5년차 장기연애 커플이 보장하는 성수동 당일치기 데이트 코스",
                        style = DateRoadTheme.typography.titleExtra24,
                        color = DateRoadTheme.colors.black
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    CourseDetailInfoBar(
                        courseDetail
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    if (courseDetail.isAccess) {
                        Text(
                            text = courseDetail.description,
                            style = DateRoadTheme.typography.bodyMed13Context,
                            color = DateRoadTheme.colors.black
                        )
                    } else {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            GradientBoxWithText(text = courseDetail.description)
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
                                    text = "코스 정보가 궁금하신가요?",
                                    style = DateRoadTheme.typography.bodyBold17,
                                    color = DateRoadTheme.colors.black,
                                    modifier = Modifier.align(Alignment.CenterHorizontally)
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "50P로 코스를 확인해보세요",
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
                                        if (courseDetail.free > 0) {
                                            freeReadDialog = true
                                        } else {
                                            readCourseDialog = true
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
            if (courseDetail.isAccess) {
                item {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        Spacer(modifier = Modifier.height(30.dp))
                        Text(
                            text = "코스 타임라인",
                            style = DateRoadTheme.typography.titleBold18,
                            color = DateRoadTheme.colors.black
                        )
                    }
                }

                items(courseDetail.places) { place ->
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
                            text = "총 비용",
                            style = DateRoadTheme.typography.titleBold18,
                            color = DateRoadTheme.colors.black
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = courseDetail.totalCost,
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
                            text = "태그",
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
                        Row {
                            mappedTags.forEach { tag ->
                                DateRoadImageChip(
                                    textId = tag.titleRes,
                                    imageRes = tag.imageRes,
                                    chipType = ChipType.DATE,
                                    isSelected = false
                                )
                                Spacer(modifier = Modifier.width(7.dp))
                            }
                        }
                        Spacer(modifier = Modifier.height(38.dp))
                        if (!courseDetail.isMine) {
                            Spacer(modifier = Modifier.height(80.dp))
                        }
                    }
                }
            }
        }

        if (!courseDetail.isMine && courseDetail.isAccess) {
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
                        isEnabled = true,
                        onClick = {},
                        cornerRadius = 14.dp,
                        paddingHorizontal = 23.dp,
                        paddingVertical = 18.dp
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    DateRoadFilledButton(
                        modifier = Modifier.weight(1f),
                        isEnabled = true,
                        textContent = "코스 가져오기",
                        onClick = { readCourseDialog = true },
                        textStyle = DateRoadTheme.typography.bodyBold15,
                        enabledBackgroundColor = DateRoadTheme.colors.deepPurple,
                        enabledTextColor = DateRoadTheme.colors.white,
                        disabledBackgroundColor = DateRoadTheme.colors.gray200,
                        disabledTextColor = DateRoadTheme.colors.gray400,
                        cornerRadius = 10.dp,
                        paddingHorizontal = 0.dp,
                        paddingVertical = 17.dp
                    )
                }
            }
        }
    }

    if (readCourseDialog) {
        DateRoadTwoButtonDialogWithDescription(
            twoButtonDialogWithDescriptionType = TwoButtonDialogWithDescriptionType.READ_COURSE,
            onDismissRequest = { readCourseDialog = false },
            onClickConfirm = {
                readCourseDialog = false
                if (courseDetail.totalPoint < 50) {
                    pointLackDialog = true
                }
            },
            onClickDismiss = { readCourseDialog = false }
        )
    }

    if (pointLackDialog) {
        DateRoadTwoButtonDialogWithDescription(
            twoButtonDialogWithDescriptionType = TwoButtonDialogWithDescriptionType.POINT_LACK,
            onDismissRequest = { pointLackDialog = false },
            onClickConfirm = { pointLackDialog = false },
            onClickDismiss = { pointLackDialog = false }
        )
    }

    if (freeReadDialog) {
        DateRoadTwoButtonDialogWithDescription(
            twoButtonDialogWithDescriptionType = TwoButtonDialogWithDescriptionType.FREE_READ,
            onDismissRequest = { freeReadDialog = false },
            onClickConfirm = { freeReadDialog = false },
            onClickDismiss = { freeReadDialog = false }
        )
    }

    DateRoadBasicBottomSheet(
        isBottomSheetOpen = isBottomSheetOpen,
        title = "데이트 코스 설정",
        isButtonEnabled = false,
        buttonText = "취소",
        itemList = listOf(
            "글 삭제" to { }
        ),
        onDismissRequest = { isBottomSheetOpen = !isBottomSheetOpen },
        onButtonClick = { isBottomSheetOpen = !isBottomSheetOpen }
    )
}

@Preview
@Composable
fun CourseDetailScreenPreview() {
    val courseDetail = CourseDetail(
        courseId = 1,
        imageList = listOf(
            R.drawable.img_course_detail_dummy,
            R.drawable.img_course_detail_dummy,
            R.drawable.img_course_detail_dummy
        ),
        like = 999,
        totalTime = 10,
        date = "2023-07-12",
        city = "건대/성수/왕십리",
        title = "성수동 당일치기 데이트 코스 둘러보러 가실까요?",
        description = "나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래?\n\n 나랑 더미데이트 하러 갈래?  나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래?  나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래?\n\n  나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래?  나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래?",
        places = listOf(
            Place(sequence = 1, title = "성수미술관 성수점", duration = "2시간"),
            Place(sequence = 2, title = "성수미술관 성수점", duration = "2시간"),
            Place(sequence = 3, title = "성수미술관 성수점", duration = "2시간")
        ),
        totalCost = "120,000 원",
        tags = listOf("드라이브", "쇼핑", "실내"),
        isAccess = false,
        free = 1,
        isMine = false,
        totalPoint = 95
    )
    CourseDetailScreen(courseDetail = courseDetail)
}


