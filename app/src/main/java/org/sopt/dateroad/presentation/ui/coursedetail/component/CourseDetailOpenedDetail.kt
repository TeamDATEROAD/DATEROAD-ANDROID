package org.sopt.dateroad.presentation.ui.coursedetail.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.domain.model.Place
import org.sopt.dateroad.presentation.type.DateTagType
import org.sopt.dateroad.ui.theme.DateRoadTheme

fun LazyListScope.courseDetailOpenedDetail(
    description: String,
    startAt: String,
    places: List<Place>,
    totalCost: String,
    tags: List<DateTagType>
) {
    item {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = description,
            style = DateRoadTheme.typography.bodyMed13Context,
            color = DateRoadTheme.colors.black
        )
    }
    item {
        Spacer(modifier = Modifier.height(16.dp))
    }
    courseDetailTimeline(places = places, startAt = startAt)
    item {
        CourseDetailCost(totalCost = totalCost)
    }
    item {
        CourseDetailTag(tags = tags)
    }
}
