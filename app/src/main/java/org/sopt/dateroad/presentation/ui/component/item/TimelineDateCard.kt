package org.sopt.dateroad.presentation.ui.component.item

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.R
import org.sopt.dateroad.domain.model.DateCard
import org.sopt.dateroad.domain.model.TimelineTag
import org.sopt.dateroad.presentation.type.DateTagType
import org.sopt.dateroad.presentation.type.TagType
import org.sopt.dateroad.presentation.ui.component.tag.DateRoadImageTag
import org.sopt.dateroad.presentation.ui.component.tag.DateRoadTextTag
import org.sopt.dateroad.presentation.util.modifier.noRippleClickable
import org.sopt.dateroad.ui.theme.DateRoadTheme
import org.sopt.dateroad.ui.theme.defaultDateRoadColors

@Composable
fun TimelineDateCard(
    dateCard: DateCard,
    onClick: (Long) -> Unit = {}
) {
    val (bgColor, lineColor, tagColor) = remember(dateCard.dateId) {
        when (dateCard.dateId % 3) {
            0L -> Triple(defaultDateRoadColors.pink200, defaultDateRoadColors.pink300, defaultDateRoadColors.pink100)
            1L -> Triple(defaultDateRoadColors.purple200, defaultDateRoadColors.purple300, defaultDateRoadColors.purple100)
            else -> Triple(defaultDateRoadColors.lime, defaultDateRoadColors.lime300, defaultDateRoadColors.lime100)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .aspectRatio(291 / 406f)
            .background(bgColor)
            .noRippleClickable(onClick = { onClick(dateCard.dateId) })
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_datecard_bg),
            contentDescription = null,
            tint = lineColor,
            modifier = Modifier
                .fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .weight(2f)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "${dateCard.month}\n${dateCard.day}",
                        style = DateRoadTheme.typography.titleExtra24,
                        color = DateRoadTheme.colors.black,
                        minLines = 2,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )
                    DateRoadTextTag(
                        textContent = stringResource(R.string.home_timeline_d_day, dateCard.dDay),
                        tagContentType = TagType.TIMELINE_D_DAY
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(start = 13.dp, end = 13.dp, bottom = 21.dp),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.Start
                ) {
                    if (dateCard.tags.size >= 3) {
                        DateRoadImageTag(
                            textContent = stringResource(id = dateCard.tags[2].timelineTag.titleRes),
                            imageContent = dateCard.tags[2].timelineTag.imageRes,
                            tagContentType = TagType.TIMELINE_DATE,
                            backgroundColor = tagColor,
                            modifier = Modifier
                                .graphicsLayer(rotationZ = -12f)
                                .padding(start = 19.dp, bottom = 5.dp)
                        )
                    }
                    if (dateCard.tags.size >= 2) {
                        DateRoadImageTag(
                            textContent = stringResource(id = dateCard.tags[1].timelineTag.titleRes),
                            imageContent = dateCard.tags[1].timelineTag.imageRes,
                            tagContentType = TagType.TIMELINE_DATE,
                            backgroundColor = tagColor,
                            modifier = Modifier
                                .graphicsLayer(rotationZ = 15f)
                                .padding(start = 60.dp, bottom = 10.dp)
                        )
                    }
                    DateRoadImageTag(
                        textContent = stringResource(id = dateCard.tags[0].timelineTag.titleRes),
                        imageContent = dateCard.tags[0].timelineTag.imageRes,
                        tagContentType = TagType.TIMELINE_DATE,
                        backgroundColor = tagColor
                    )
                }
            }
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .padding(top = 0.dp)
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
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 13.dp, vertical = 25.dp)
            ) {
                Text(
                    text = dateCard.city,
                    style = DateRoadTheme.typography.bodyMed15,
                    color = DateRoadTheme.colors.gray500,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = dateCard.title,
                    style = DateRoadTheme.typography.titleExtra24,
                    color = DateRoadTheme.colors.black,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}

@Preview
@Composable
fun TimelineDateCardPreview() {
    Column {
        TimelineDateCard(
            dateCard = DateCard(
                dateId = 0,
                dDay = "3",
                title = "성수동 당일치기 데이트\n가볼까요?",
                year = "2024",
                month = "JUNE",
                day = "23",
                city = "건대/성수/왕십리",
                tags = listOf(TimelineTag(DateTagType.SHOPPING), TimelineTag(DateTagType.DRIVE), TimelineTag(DateTagType.EXHIBITION_POP_UP))
            )
        )
    }
}
