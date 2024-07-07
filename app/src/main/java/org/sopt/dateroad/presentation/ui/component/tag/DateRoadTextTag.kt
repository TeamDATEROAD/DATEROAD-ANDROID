package org.sopt.dateroad.presentation.ui.component.tag

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.dateroad.presentation.type.TagType
import org.sopt.dateroad.ui.theme.DATEROADTheme

@Composable
fun DateRoadTextTag(
    modifier: Modifier = Modifier,
    textContent: String,
    tagContentType: TagType
) {
    DateRoadTag(
        modifier = modifier,
        tagType = tagContentType
    ) {
        Text(
            text = textContent,
            style = tagContentType.textStyle,
            color = tagContentType.contentColor
        )
    }
}

@Preview
@Composable
fun DateRoadTextTagPreview() {
    DATEROADTheme {
        Column {
            DateRoadTextTag(
                textContent = "2시간",
                tagContentType = TagType.PLACE_CARD_TIME
            )
            DateRoadTextTag(
                textContent = "에디터 픽",
                tagContentType = TagType.ADVERTISEMENT_TITLE
            )
            DateRoadTextTag(
                textContent = "1/3",
                tagContentType = TagType.COURSE_DETAIL_PHOTO_NUMBER
            )
            DateRoadTextTag(
                textContent = "1/5",
                tagContentType = TagType.ENROLL_PHOTO_NUMBER
            )
            DateRoadTextTag(
                textContent = "\uD83D\uDE97 드라이브",
                tagContentType = TagType.MY_PAGE_DATE
            )
            DateRoadTextTag(
                textContent = "1/5",
                tagContentType = TagType.ADVERTISEMENT_PAGE_NUMBER
            )
            DateRoadTextTag(
                textContent = "\uD83D\uDE97 드라이브",
                tagContentType = TagType.PAST_DATE
            )
            DateRoadTextTag(
                textContent = "1",
                tagContentType = TagType.PLACE_CARD_NUMBER
            )
            DateRoadTextTag(
                textContent = "\uD83C\uDFA8 전시-팝업",
                tagContentType = TagType.TIMELINE_DATE
            )
            DateRoadTextTag(
                textContent = "D-Day",
                tagContentType = TagType.TIMELINE_D_DAY
            )
        }
    }
}
