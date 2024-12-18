package org.sopt.teamdateroad.presentation.ui.pointhistory.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.teamdateroad.R
import org.sopt.teamdateroad.domain.model.UserPoint
import org.sopt.teamdateroad.ui.theme.DateRoadTheme

@Composable
fun PointHistoryPointBox(
    modifier: Modifier = Modifier,
    userPoint: UserPoint
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(DateRoadTheme.colors.purple600)
            .padding(start = 16.dp, top = 16.dp, bottom = 14.dp)
    ) {
        Text(
            text = stringResource(id = R.string.point_box_nickname, userPoint.name),
            color = DateRoadTheme.colors.white,
            style = DateRoadTheme.typography.bodyMed13
        )
        Spacer(modifier = Modifier.height(11.dp))
        Text(
            text = userPoint.point,
            color = DateRoadTheme.colors.white,
            style = DateRoadTheme.typography.titleExtra24,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview
@Composable
fun PointHistoryPointBoxPreview() {
    Column {
        PointHistoryPointBox(userPoint = UserPoint())
    }
}
