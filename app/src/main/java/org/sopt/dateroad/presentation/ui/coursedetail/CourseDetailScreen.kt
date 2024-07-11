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
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import org.sopt.dateroad.R
import org.sopt.dateroad.domain.model.Course
import org.sopt.dateroad.domain.model.Place
import org.sopt.dateroad.presentation.type.ChipType
import org.sopt.dateroad.presentation.type.DateTagType
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
import org.sopt.dateroad.ui.theme.DateRoadTheme

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CourseDetailScreen(course: Course, places: List<Place>, tags: List<DateTagType>, isMyCourse: Boolean, isAccess: Boolean) {
    var isBottomSheetOpen by rememberSaveable { mutableStateOf(false) }
    var readCourseDialog by rememberSaveable { mutableStateOf(false) }
    var pointLackDialog by rememberSaveable { mutableStateOf(false) }

    var contentOffsetY by remember { mutableStateOf(0f) }
    val density = LocalDensity.current.density
    val imageList = listOf(
        R.drawable.img_course_detail_dummy,
        R.drawable.img_course_detail_dummy,
        R.drawable.img_course_detail_dummy
    )
    val pagerState = rememberPagerState()

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .background(DateRoadTheme.colors.white),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            userScrollEnabled = isAccess // Disable scrolling if isAccess is false
        ) {
            item {
                Box(modifier = Modifier.fillMaxWidth()) {
                    HorizontalPager(
                        count = imageList.size,
                        state = pagerState,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f),
                        userScrollEnabled = isAccess // Disable scrolling in pager if isAccess is false
                    ) { page ->
                        Image(
                            painter = painterResource(id = imageList[page]),
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

                    if (isMyCourse) {
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
                        textContent = "5",
                        imageContent = R.drawable.ic_tag_heart,
                        tagContentType = TagType.HEART,
                        modifier = Modifier
                            .padding(start = 10.dp, bottom = 10.dp)
                            .align(Alignment.BottomStart)
                    )

                    DateRoadTextTag(
                        textContent = "${pagerState.currentPage + 1}/${imageList.size}",
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
                        .onGloballyPositioned { coordinates ->
                            contentOffsetY = coordinates.positionInParent().y
                        }
                ) {
                    Text(
                        text = "2024년 6월 27일 방문",
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
                        course = Course(
                            id = 1,
                            url = "https://avatars.githubusercontent.com/u/103172971?v=4",
                            city = "건대/성수/왕십리",
                            title = "성수동 당일치기 데이트 코스 둘러보러 가실까요?",
                            cost = "5만원 이하",
                            duration = "10시간",
                            like = "999"
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래?\n\n 나랑 더미데이트 하러 갈래?  나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래?  나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래?\n\n  나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래?  나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래? 나랑 더미데이트 하러 갈래?",
                        style = DateRoadTheme.typography.bodyMed13Context,
                        color = DateRoadTheme.colors.black
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    Text(
                        text = "코스 타임라인",
                        style = DateRoadTheme.typography.titleBold18,
                        color = DateRoadTheme.colors.black
                    )
                }
            }

            items(places) { place ->
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
                        text = "90,000원",
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
                        tags.forEach { tag ->
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
                    if (!isMyCourse) {
                        Spacer(modifier = Modifier.height(80.dp))
                    }
                }
            }
        }

        if (!isMyCourse) {
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

        if (!isAccess) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                DateRoadTheme.colors.white.copy(alpha = 1f)
                            ),
                            startY = contentOffsetY,
                            endY = contentOffsetY + 220.dp.toPx(density)
                        )
                    )
            ) {
                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                ) {
                    Spacer(modifier = Modifier.height(600.dp))
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
                        style = DateRoadTheme.typography.bodyBold17,
                        color = DateRoadTheme.colors.black,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    DateRoadFilledButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 52.dp)
                            .align(Alignment.CenterHorizontally),
                        isEnabled = true,
                        textContent = "포인트로 코스 열람하기",
                        onClick = { readCourseDialog = true },
                        textStyle = DateRoadTheme.typography.bodyBold15,
                        enabledBackgroundColor = DateRoadTheme.colors.deepPurple,
                        enabledTextColor = DateRoadTheme.colors.white,
                        disabledBackgroundColor = DateRoadTheme.colors.gray200,
                        disabledTextColor = DateRoadTheme.colors.gray400,
                        cornerRadius = 14.dp,
                        paddingHorizontal = 0.dp,
                        paddingVertical = 16.dp
                    )
                }
            }
        }
    }

    if (readCourseDialog) {
        DateRoadTwoButtonDialogWithDescription(
            twoButtonDialogWithDescriptionType = TwoButtonDialogWithDescriptionType.READ_COURSE ,
            onDismissRequest = { readCourseDialog = false },
            onClickConfirm = { readCourseDialog = false
                             if (true){ //TODO: 포인트 부족 로직
                                 pointLackDialog=true
                             }},
            onClickDismiss = { readCourseDialog = false }
        )
    }

    if (pointLackDialog) {
        DateRoadTwoButtonDialogWithDescription(
            twoButtonDialogWithDescriptionType = TwoButtonDialogWithDescriptionType.POINT_LACK ,
            onDismissRequest = { pointLackDialog = false },
            onClickConfirm = { pointLackDialog = false
                },
            onClickDismiss = { pointLackDialog = false }
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
    var isBottomSheetOpen by rememberSaveable { mutableStateOf(false) }

    val course = Course(
        id = 1,
        url = "https://avatars.githubusercontent.com/u/103172971?v=4",
        city = "건대/성수/왕십리",
        title = "성수동 당일치기 데이트 코스 둘러보러 가실까요?",
        cost = "5만원 이하",
        duration = "10시간",
        like = "999"
    )
    val places = listOf(
        Place(sequence = 1, title = "성수미술관 성수점", duration = "2시간"),
        Place(sequence = 2, title = "성수미술관 성수점", duration = "2시간"),
        Place(sequence = 3, title = "성수미술관 성수점", duration = "2시간")
    )
    val tags = listOf(
        DateTagType.DRIVE,
        DateTagType.SHOPPING,
        DateTagType.INDOOR
    )
    CourseDetailScreen(
        course = course,
        places = places,
        tags = tags,
        isMyCourse = false,
        isAccess = false
    )

    DateRoadBasicBottomSheet(
        isBottomSheetOpen = isBottomSheetOpen,
        title = "데이트 코스 설정",
        isButtonEnabled = false,
        buttonText = "취소",
        itemList = listOf(
            "글 삭제" to { isBottomSheetOpen = false }
        ),
        onDismissRequest = { isBottomSheetOpen = false }
    )
}

private fun androidx.compose.ui.unit.Dp.toPx(density: Float): Float {
    return this.value * density
}
