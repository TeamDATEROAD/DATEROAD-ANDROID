package org.sopt.dateroad.presentation.ui.home.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.R
import org.sopt.dateroad.domain.model.MainTimeline
import org.sopt.dateroad.presentation.type.TagType
import org.sopt.dateroad.presentation.ui.component.tag.DateRoadTextTag
import org.sopt.dateroad.presentation.util.modifier.noRippleClickable
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun HomeTimeLineCard(
    mainTimeline: MainTimeline = MainTimeline(),
    onClick: () -> Unit = {}
) {
    val purple600 = DateRoadTheme.colors.purple600

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .clip(RoundedCornerShape(20.dp))
            .background(DateRoadTheme.colors.purple500),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .padding(start = if (mainTimeline.dateName.isNotEmpty()) 16.dp else 23.dp, end = 9.dp)
                .weight(4.5f),
            verticalArrangement = Arrangement.Center
        ) {
            if (mainTimeline.dateName.isNotEmpty()) {
                DateRoadTextTag(
                    textContent = mainTimeline.dDay,
                    tagContentType = TagType.TIMELINE_D_DAY
                )
            }
            if (mainTimeline.dateName.isNotEmpty()) {
                Spacer(modifier = Modifier.height(7.dp))
            }
            Text(
                text = if (mainTimeline.dateName.isNotEmpty()) mainTimeline.dateName else stringResource(id = R.string.home_timeline_is_not),
                style = DateRoadTheme.typography.titleBold20,
                color = DateRoadTheme.colors.white,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(bottom = 2.dp)
                    .fillMaxWidth()
            )

            if (mainTimeline.dateName.isNotEmpty()) {
                Row {
                    Text(
                        text = mainTimeline.date,
                        style = DateRoadTheme.typography.bodyMed15,
                        color = DateRoadTheme.colors.purple300,
                        maxLines = 1,
                        textAlign = if (mainTimeline.dateName.isEmpty()) TextAlign.Center else TextAlign.Start,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        text = mainTimeline.startAt,
                        style = DateRoadTheme.typography.bodyMed15,
                        color = DateRoadTheme.colors.purple300,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .padding(start = 19.dp)
                    )
                }
            } else {
                Text(
                    text = stringResource(id = R.string.home_timeline_enroll),
                    style = DateRoadTheme.typography.bodyMed15,
                    color = DateRoadTheme.colors.purple300,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        Canvas(
            modifier = Modifier
                .fillMaxHeight()
                .width(2.dp)
        ) {
            val canvasHeight = size.height
            val dotLength = 3.dp.toPx()
            val dotSpacing = 3.dp.toPx()
            var yOffset = 0f
            val strokeWidth = 2.dp.toPx()

            drawCircle(
                color = purple600,
                radius = strokeWidth * 4,
                center = Offset(x = 0f, y = strokeWidth / 2 - 5)
            )

            while (yOffset < canvasHeight) {
                drawLine(
                    color = purple600,
                    start = Offset(0f, yOffset),
                    end = Offset(0f, yOffset + dotLength),
                    strokeWidth = 2.dp.toPx(),
                    cap = StrokeCap.Butt
                )
                yOffset += dotLength + dotSpacing
            }
            drawCircle(
                color = purple600,
                radius = strokeWidth * 4,
                center = Offset(x = 0f, y = canvasHeight - strokeWidth / 2 + 5)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            Image(
                painter = painterResource(id = if (mainTimeline.dateName.isNotEmpty()) R.drawable.ic_home_right_arrow_purple else R.drawable.ic_home_plus_purple),
                contentDescription = null,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .align(Alignment.CenterHorizontally)
                    .noRippleClickable { onClick() }
            )
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}

@Preview
@Composable
fun DateRoadDateSchedulePreview() {
    Column {
        HomeTimeLineCard(
            mainTimeline = MainTimeline(
                timelineId = 1,
                dDay = "3",
                dateName = "성수 데이트",
                date = "2024.06.13",
                startAt = "14:00 PM"
            )
        )
        HomeTimeLineCard(
            mainTimeline = MainTimeline()
        )
    }
}
