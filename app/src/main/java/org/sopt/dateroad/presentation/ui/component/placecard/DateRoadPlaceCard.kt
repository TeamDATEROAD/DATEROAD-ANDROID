package org.sopt.dateroad.presentation.ui.component.placecard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.R
import org.sopt.dateroad.domain.model.Place
import org.sopt.dateroad.presentation.type.PlaceCardType
import org.sopt.dateroad.presentation.type.TagType
import org.sopt.dateroad.presentation.ui.component.tag.DateRoadTextTag
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun DateRoadPlaceCard(
    placeCardType: PlaceCardType,
    placeTimeline: String? = null,
    place: Place,
    onIconClick: (() -> Unit)? = null
) {
    val paddingValues = Modifier.padding(start = placeCardType.startPadding, end = placeCardType.endPadding)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(DateRoadTheme.colors.gray100)
            .then(paddingValues)
            .padding(vertical = placeCardType.verticalPadding),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        placeTimeline?.let {
            DateRoadTextTag(
                textContent = it,
                tagContentType = TagType.PLACE_CARD_NUMBER
            )
            Spacer(modifier = Modifier.width(14.dp))
        }
        Text(
            text = place.title,
            modifier = Modifier.weight(1f),
            style = DateRoadTheme.typography.bodyBold15
        )
        Spacer(modifier = Modifier.width(10.dp))

        DateRoadTextTag(
            textContent =  stringResource(id = R.string.course_detail_duration, place.duration),
            tagContentType = TagType.PLACE_CARD_TIME
        )
        placeCardType.iconRes?.let {
            Icon(
                painter = painterResource(id = it),
                contentDescription = null,
                modifier = Modifier
                    .padding(
                        start = placeCardType.iconStartPadding,
                        top = placeCardType.iconTopPadding,
                        end = placeCardType.iconEndPadding,
                        bottom = placeCardType.iconBottomPadding
                    )
                    .clickable {
                        onIconClick?.invoke()
                    }
            )
        }
    }
}

@Preview
@Composable
fun DateRoadPlaceCardPreview() {
    Column {
        DateRoadPlaceCard(
            placeCardType = PlaceCardType.COURSE_NORMAL,
            placeTimeline = "1",
            place = Place(title = "성수미술관 성수점", duration = 2)
        )
        Spacer(modifier = Modifier.height(8.dp))
        DateRoadPlaceCard(
            placeCardType = PlaceCardType.COURSE_EDIT,
            place = Place(title = "성수미술관 성수점", duration = 2),
            onIconClick = { }
        )
        Spacer(modifier = Modifier.height(8.dp))
        DateRoadPlaceCard(
            placeCardType = PlaceCardType.COURSE_DELETE,
            place = Place(title = "성수미술관 성수점", duration = 2),
            onIconClick = { }
        )
    }
}
