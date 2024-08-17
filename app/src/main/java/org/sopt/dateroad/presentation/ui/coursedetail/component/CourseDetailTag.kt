package org.sopt.dateroad.presentation.ui.coursedetail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.R
import org.sopt.dateroad.presentation.type.ChipType
import org.sopt.dateroad.presentation.type.DateTagType
import org.sopt.dateroad.presentation.ui.component.chip.DateRoadImageChip
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun CourseDetailTag(
    tags: List<DateTagType>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = stringResource(id = R.string.course_detail_tag),
            style = DateRoadTheme.typography.titleBold18,
            color = DateRoadTheme.colors.black
        )
        Spacer(modifier = Modifier.height(12.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(7.dp)) {
            items(tags) { tag ->
                DateRoadImageChip(
                    textId = tag.titleRes,
                    imageRes = tag.imageRes,
                    chipType = ChipType.DATE,
                    isSelected = false
                )
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
    }
}