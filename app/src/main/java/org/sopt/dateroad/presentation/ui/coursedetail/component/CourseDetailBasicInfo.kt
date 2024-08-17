package org.sopt.dateroad.presentation.ui.coursedetail.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun CourseDetailBasicInfo(
    date: String,
    title: String,
    totalTime: String,
    totalCost: String,
    city: String
) {
    Column {
        Spacer(modifier = Modifier.height(18.dp))
        Text(
            text = date,
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
        CourseDetailInfoBar(
            totalTime = totalTime,
            totalCost = totalCost,
            city = city
        )
    }
}