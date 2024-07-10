package org.sopt.dateroad.presentation.ui.timeline.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.R
import org.sopt.dateroad.domain.model.Date
import org.sopt.dateroad.presentation.type.DateTagType
import org.sopt.dateroad.presentation.type.DateType
import org.sopt.dateroad.presentation.type.TagType
import org.sopt.dateroad.presentation.ui.component.tag.DateRoadImageTag
import org.sopt.dateroad.presentation.util.modifier.noRippleClickable
import org.sopt.dateroad.ui.theme.DateRoadTheme
import org.sopt.dateroad.ui.theme.defaultDateRoadColors

@Composable
fun PastCard(
    dateCard: Date,
    dateType: DateType,
    onClick: (Int) -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .aspectRatio(328 / 203f)
            .background(dateType.backgroundColor)
            .noRippleClickable(onClick = { onClick(dateCard.dateId) })
    ) {
        Icon(
            painter = painterResource(id = R.drawable.bg_date_card_small),
            contentDescription = null,
            tint = dateType.lineColor,
            modifier = Modifier
                .fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .weight(1.5f)
                    .fillMaxWidth()
                    .padding(top = 14.dp, start = 16.dp, end = 16.dp)
            ) {
                Text(
                    text = dateCard.date,
                    style = DateRoadTheme.typography.bodySemi13,
                    color = DateRoadTheme.colors.black,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Text(
                    text = dateCard.title,
                    style = DateRoadTheme.typography.titleExtra20,
                    color = DateRoadTheme.colors.black,
                    minLines = 2,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
            ) {
                val canvasWidth = size.width
                val dotLength = 3.dp.toPx()
                val dotSpacing = 3.dp.toPx()
                var xOffset = 0f
                val strokeWidth = 2.dp.toPx()

                drawCircle(
                    color = defaultDateRoadColors.white,
                    radius = strokeWidth * 4,
                    center = Offset(x = strokeWidth / 2 - 5, y = 0f)
                )

                while (xOffset < canvasWidth) {
                    drawLine(
                        color = defaultDateRoadColors.white,
                        start = Offset(xOffset, 0f),
                        end = Offset(xOffset + dotLength, 0f),
                        strokeWidth = 2.dp.toPx(),
                        cap = StrokeCap.Butt
                    )
                    xOffset += dotLength + dotSpacing
                }
                drawCircle(
                    color = defaultDateRoadColors.white,
                    radius = strokeWidth * 4,
                    center = Offset(x = canvasWidth - strokeWidth / 2 + 5, y = 0f)
                )
            }
            Spacer(modifier = Modifier.height(17.dp))
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(vertical = 17.dp, horizontal = 16.dp)
            ) {
                Text(
                    text = dateCard.city,
                    style = DateRoadTheme.typography.bodyMed13,
                    color = DateRoadTheme.colors.black
                )
                LazyRow(
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    item {
                        DateRoadImageTag(
                            textContent = stringResource(id = dateCard.tags[0].titleRes),
                            imageContent = dateCard.tags[0].imageRes,
                            tagContentType = TagType.TIMELINE_DATE,
                            backgroundColor = dateType.tagColor
                        )
                    }
                    item {
                        DateRoadImageTag(
                            textContent = stringResource(id = dateCard.tags[1].titleRes),
                            imageContent = dateCard.tags[1].imageRes,
                            tagContentType = TagType.TIMELINE_DATE,
                            backgroundColor = dateType.tagColor,
                            modifier = Modifier
                                .padding(start = 6.dp)
                        )
                    }
                    item {
                        DateRoadImageTag(
                            textContent = stringResource(id = dateCard.tags[2].titleRes),
                            imageContent = dateCard.tags[2].imageRes,
                            tagContentType = TagType.TIMELINE_DATE,
                            backgroundColor = dateType.tagColor,
                            modifier = Modifier
                                .padding(start = 6.dp)
                        )
                    }
                }

            }

        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Preview
@Composable
fun PastCardPreview() {
    Column {
        PastCard(
            dateType = DateType.PINK,
            dateCard = Date(
                dateId = 0,
                dDay = "3",
                title = "성수동 당일치기 데이트가볼까요?\n이정도 어떠신지?",
                date = "2024년 6월 23일",
                city = "건대/성수/왕십리",
                tags = listOf(DateTagType.SHOPPING, DateTagType.DRIVE, DateTagType.EXHIBITION_POP_UP)
            )
        )
    }
}
