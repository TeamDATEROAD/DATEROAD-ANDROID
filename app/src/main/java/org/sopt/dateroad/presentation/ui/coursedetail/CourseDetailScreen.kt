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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import org.sopt.dateroad.R
import org.sopt.dateroad.domain.model.Course
import org.sopt.dateroad.domain.model.Place
import org.sopt.dateroad.presentation.type.ChipType
import org.sopt.dateroad.presentation.type.DateTagType
import org.sopt.dateroad.presentation.type.PlaceCardType
import org.sopt.dateroad.presentation.type.TagType
import org.sopt.dateroad.presentation.ui.component.button.DateRoadFilledButton
import org.sopt.dateroad.presentation.ui.component.button.DateRoadImageButton
import org.sopt.dateroad.presentation.ui.component.chip.DateRoadImageChip
import org.sopt.dateroad.presentation.ui.component.placecard.DateRoadPlaceCard
import org.sopt.dateroad.presentation.ui.component.tag.DateRoadImageTag
import org.sopt.dateroad.presentation.ui.component.tag.DateRoadTextTag
import org.sopt.dateroad.presentation.ui.coursedetail.component.CourseDetailInfoBar
import org.sopt.dateroad.ui.theme.DateRoadTheme


@OptIn(ExperimentalPagerApi::class)
@Composable
fun CourseDetailScreen(course: Course, places: List<Place>, tags: List<DateTagType>) {
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
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Box(modifier = Modifier.fillMaxWidth()) {
                    HorizontalPager(
                        count = imageList.size,
                        state = pagerState,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
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

                    Image(
                        painter = painterResource(id = R.drawable.btn_course_detail_more_white),
                        contentDescription = null,
                        modifier = Modifier
                            .clickable { }
                            .padding(top = 5.dp)
                            .align(Alignment.TopEnd)
                    )

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
                ) {
                    Text(
                        text = "2024년 6월 27일 방문",
                        style = DateRoadTheme.typography.bodySemi15,
                        color = DateRoadTheme.colors.gray400
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
                        text = "5년차 장기연애 커플이 보장하는 성수동 당일치기 데이트 코스",
                        style = DateRoadTheme.typography.titleExtra24,
                        color = DateRoadTheme.colors.black
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
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)) {
                    DateRoadPlaceCard(
                        placeCardType = PlaceCardType.COURSE_NORMAL,
                        place = place
                    )
                }
            }

            item {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)) {
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
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)) {
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
                    Spacer(modifier = Modifier.height(118.dp))
                }
            }
        }

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
                    onClick = {},
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

@Preview
@Composable
fun CourseDetailScreenPreview() {
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
    CourseDetailScreen(course = course, places = places, tags = tags)
}