package org.sopt.dateroad.presentation.ui.advertisement.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.presentation.type.TagType
import org.sopt.dateroad.presentation.ui.component.tag.DateRoadTextTag
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun AdvertisementDetail(
    advertisementTagTitle: String,
    createAt: String,
    title: String,
    description: String
) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        DateRoadTextTag(
            textContent = advertisementTagTitle,
            tagContentType = TagType.ADVERTISEMENT_TITLE
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = createAt,
            style = DateRoadTheme.typography.bodySemi15,
            color = DateRoadTheme.colors.gray400
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = title,
            style = DateRoadTheme.typography.titleExtra24,
            color = DateRoadTheme.colors.black
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = description,
            style = DateRoadTheme.typography.bodyMed13Context,
            color = DateRoadTheme.colors.black
        )
        Spacer(modifier = Modifier.height(54.dp))
    }
}
