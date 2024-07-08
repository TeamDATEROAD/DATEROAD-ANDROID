package org.sopt.dateroad.presentation.ui.component.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.R
import org.sopt.dateroad.presentation.type.TagType
import org.sopt.dateroad.presentation.ui.component.tag.DateRoadImageTag
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun DateRoadCourseCard(
    imgResource: Int,
    heartCount: String,
    textPlaceContent: String,
    textTitleContent: String,
    textPriceContent: String,
    textTimeContent: String
) {
    val imgSize = 110.dp
    val imageCornerRadius = 14.dp

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(DateRoadTheme.colors.white)
            .padding(vertical = 10.dp, horizontal = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .size(imgSize)
        ) {
            Image(
                painter = painterResource(id = imgResource),
                contentDescription = null,
                modifier = Modifier
                    .matchParentSize()
                    .clip(RoundedCornerShape(imageCornerRadius))
            )
            DateRoadImageTag(
                textContent = heartCount,
                imageContent = R.drawable.ic_tag_heart,
                tagContentType = TagType.HEART,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 5.dp, bottom = 5.dp)
            )
        }
        Column(
            modifier = Modifier
                .height(imgSize)
        ) {
            Text(
                text = textPlaceContent,
                style = DateRoadTheme.typography.bodyMed13,
                color = DateRoadTheme.colors.gray400,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, start = 14.dp, bottom = 5.dp)
            )
            Text(
                text = textTitleContent,
                style = DateRoadTheme.typography.bodyMed13,
                color = DateRoadTheme.colors.black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, start = 14.dp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier
                    .padding(bottom = 4.dp, start = 14.dp)
            ) {
                DateRoadImageTag(
                    textContent = textPriceContent,
                    imageContent = R.drawable.ic_all_money_12,
                    tagContentType = TagType.MONEY
                )
                Spacer(modifier = Modifier.size(6.dp))
                DateRoadImageTag(
                    textContent = textTimeContent,
                    imageContent = R.drawable.ic_all_clock_12,
                    tagContentType = TagType.TIME
                )
            }
        }
    }
}

@Preview
@Composable
fun DateRoadCourseCardPreview() {
    Column {
        DateRoadCourseCard(
            imgResource = R.drawable.ic_launcher_background,
            heartCount = "5",
            textPlaceContent = "건대/성수/왕십리",
            textTitleContent = "여기 야키니쿠 꼭 먹으러 가세요\n하지만 일본에 있습니다.",
            textPriceContent = "10만원 초과",
            textTimeContent = "10시간"
        )
    }
}
