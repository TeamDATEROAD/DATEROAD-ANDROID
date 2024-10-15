package org.sopt.teamdateroad.presentation.ui.coursedetail.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.teamdateroad.R
import org.sopt.teamdateroad.ui.theme.DateRoadTheme

@Composable
fun CourseDetailInfoBar(
    totalCostTag: String,
    totalTime: String,
    city: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.weight(116f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_all_money_14),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = totalCostTag,
                color = DateRoadTheme.colors.gray400,
                style = DateRoadTheme.typography.bodySemi15
            )
        }

        Row(
            modifier = Modifier.weight(86f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_all_clock_14),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = totalTime,
                color = DateRoadTheme.colors.gray400,
                style = DateRoadTheme.typography.bodySemi15
            )
        }

        Row(
            modifier = Modifier.weight(122f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_all_location_14),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = city,
                color = DateRoadTheme.colors.gray400,
                style = DateRoadTheme.typography.bodySemi15
            )
        }
    }
}

@Preview
@Composable
fun CourseDetailInfoBarPreview() {
    CourseDetailInfoBar(
        totalTime = "10",
        city = "건대/성수/왕십리",
        totalCostTag = "50000"
    )
}
