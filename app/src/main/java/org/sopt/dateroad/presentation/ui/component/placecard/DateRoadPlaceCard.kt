package org.sopt.dateroad.presentation.ui.component.placecard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.presentation.type.TagType
import org.sopt.dateroad.presentation.ui.component.tag.DateRoadTextTag
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun DateRoadPlaceCard(
    timeline: String,
    text: String,
    time: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(DateRoadTheme.colors.gray100)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 13.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            DateRoadTextTag(
                textContent = timeline,
                tagContentType = TagType.PLACE_CARD_NUMBER
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = text,
                modifier = Modifier.weight(1f),
                style = DateRoadTheme.typography.bodyBold15
            )
            Spacer(modifier = Modifier.width(14.dp))
            DateRoadTextTag(
                textContent = time,
                tagContentType = TagType.PLACE_CARD_TIME
            )
        }
    }
}

@Preview
@Composable
fun DateRoadPlaceCardPreview() {
    Column {
        DateRoadPlaceCard("1", "성수미술관 성수점", "2시간")
    }
}
