package org.sopt.teamdateroad.presentation.ui.pointhistory.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.teamdateroad.domain.model.Point
import org.sopt.teamdateroad.ui.theme.DATEROADTheme
import org.sopt.teamdateroad.ui.theme.DateRoadTheme

@Composable
fun PointHistoryCard(
    modifier: Modifier = Modifier,
    point: Point
) {
    Row(
        modifier = modifier
            .padding(top = 20.dp, start = 16.dp, end = 20.dp, bottom = 20.dp)
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(end = 15.dp),
            text = point.point,
            color = DateRoadTheme.colors.black,
            style = DateRoadTheme.typography.bodyBold15
        )
        Column(
            modifier = Modifier.weight(235 / 74f)
        ) {
            Text(
                text = point.description,
                color = DateRoadTheme.colors.gray500,
                style = DateRoadTheme.typography.bodyBold15
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = point.createdAt,
                color = DateRoadTheme.colors.gray500,
                style = DateRoadTheme.typography.bodyMed15
            )
        }
    }
}

@Preview
@Composable
fun PointHistoryCardPreview() {
    DATEROADTheme {
        PointHistoryCard(
            point = Point(
                point = "+10P",
                description = "코스 등록하기",
                createdAt = "2024.06.23"
            )
        )
    }
}
