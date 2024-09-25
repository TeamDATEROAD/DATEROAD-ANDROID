package org.sopt.dateroad.presentation.ui.coursedetail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.R
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun CourseDetailCost(
    totalCost: String
) {
    Spacer(modifier = Modifier.height(14.dp))
    Text(
        modifier = Modifier.padding(horizontal = 16.dp),
        text = stringResource(id = R.string.course_detail_total_cost_string),
        style = DateRoadTheme.typography.titleBold18,
        color = DateRoadTheme.colors.black
    )
    Spacer(modifier = Modifier.height(12.dp))
    Text(
        text = totalCost,
        style = DateRoadTheme.typography.bodyBold15,
        color = DateRoadTheme.colors.black,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(DateRoadTheme.colors.gray100)
            .padding(start = 20.dp, top = 15.dp, end = 5.dp, bottom = 17.dp)
    )
}
