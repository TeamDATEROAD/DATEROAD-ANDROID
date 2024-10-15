package org.sopt.teamdateroad.presentation.ui.component.tag

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.teamdateroad.R
import org.sopt.teamdateroad.presentation.type.DateTagType
import org.sopt.teamdateroad.presentation.type.TagType
import org.sopt.teamdateroad.ui.theme.DATEROADTheme

@Composable
fun DateRoadImageTag(
    modifier: Modifier = Modifier,
    textContent: String,
    @DrawableRes imageContent: Int,
    spaceValue: Int = 5,
    tagContentType: TagType
) {
    DateRoadTag(
        modifier = modifier,
        tagType = tagContentType
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = imageContent),
                contentDescription = null
            )
            Spacer(modifier = Modifier.size(spaceValue.dp))
            Text(
                text = textContent,
                style = tagContentType.textStyle,
                color = tagContentType.contentColor
            )
        }
    }
}

@Preview
@Composable
fun DateRoadImageTagPreview() {
    DATEROADTheme {
        Column {
            DateRoadImageTag(
                textContent = "10만원 이상",
                imageContent = R.drawable.ic_all_money_12,
                tagContentType = TagType.MONEY
            )
            DateRoadImageTag(
                textContent = "5",
                imageContent = R.drawable.ic_tag_heart,
                tagContentType = TagType.HEART
            )
            DateRoadImageTag(
                textContent = "10시간",
                imageContent = R.drawable.ic_all_clock_12,
                tagContentType = TagType.TIME
            )
            DateRoadImageTag(
                textContent = stringResource(id = DateTagType.DRIVE.titleRes),
                imageContent = DateTagType.DRIVE.imageRes,
                tagContentType = TagType.MY_PAGE_DATE
            )
            DateRoadImageTag(
                textContent = stringResource(id = DateTagType.DRIVE.titleRes),
                imageContent = DateTagType.DRIVE.imageRes,
                tagContentType = TagType.PAST_DATE
            )
            DateRoadImageTag(
                textContent = stringResource(id = DateTagType.EXHIBITION_POPUP.titleRes),
                imageContent = DateTagType.EXHIBITION_POPUP.imageRes,
                tagContentType = TagType.TIMELINE_DATE_PINK
            )
        }
    }
}
