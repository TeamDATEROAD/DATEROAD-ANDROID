package org.sopt.dateroad.presentation.ui.component.tag

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.R
import org.sopt.dateroad.presentation.type.TagType
import org.sopt.dateroad.ui.theme.DATEROADTheme

@Composable
fun DateRoadImageTag(
    modifier: Modifier = Modifier,
    textContent: String,
    imageContent: Int,
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
                textContent = "10만원 초과",
                imageContent = R.drawable.ic_all_money_12,
                tagContentType = TagType.MONEY_TAG
            )
            DateRoadImageTag(
                textContent = "5",
                imageContent = R.drawable.ic_tag_heart,
                tagContentType = TagType.HEART_TAG
            )
            DateRoadImageTag(
                textContent = "10시간",
                imageContent = R.drawable.ic_all_clock_12,
                tagContentType = TagType.HOME_TIME
            )
        }
    }
}
