package org.sopt.dateroad.presentation.ui.coursedetail.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.R
import org.sopt.dateroad.domain.model.Place
import org.sopt.dateroad.presentation.type.PlaceCardType
import org.sopt.dateroad.presentation.ui.component.card.DateRoadPlaceCard
import org.sopt.dateroad.ui.theme.DateRoadTheme

fun LazyListScope.courseDetailTimeline(
    places: List<Place>,
    startAt: String
) {
    item {
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = stringResource(id = R.string.course_detail_timeline_title),
            style = DateRoadTheme.typography.titleBold18,
            color = DateRoadTheme.colors.black,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(7.dp))
        Text(
            text = startAt,
            style = DateRoadTheme.typography.bodySemi15,
            color = DateRoadTheme.colors.gray400,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(14.dp))
    }
    items(places.size) { index ->
        DateRoadPlaceCard(
            modifier = Modifier.padding(horizontal = 16.dp),
            placeCardType = PlaceCardType.COURSE_NORMAL,
            sequence = index,
            place = places[index]
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}
