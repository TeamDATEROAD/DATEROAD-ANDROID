package org.sopt.dateroad.presentation.ui.component.pointbox

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun DateRoadMyPagePointBox(nickname: String, point: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(DateRoadTheme.colors.white)
    ) {
        Column(modifier = Modifier.padding(start = 14.dp, top = 18.dp, bottom = 14.dp, end = 10.dp)) {
            Text(
                text = stringResource(id = R.string.point_box_nickname, nickname),
                color = DateRoadTheme.colors.gray400,
                style = DateRoadTheme.typography.bodyMed13
            )
            Spacer(modifier = Modifier.height(9.dp))
            Text(
                text = stringResource(id = R.string.point_box_point, point),
                color = DateRoadTheme.colors.black,
                style = DateRoadTheme.typography.titleExtra24
            )
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 10.dp, bottom = 11.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 14.dp, vertical = 11.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.my_page_point_card_text),
                    style = DateRoadTheme.typography.bodyMed13,
                    color = DateRoadTheme.colors.gray400
                )
                Spacer(modifier = Modifier.width(10.dp))
                Icon(
                    painter = painterResource(id = R.drawable.ic_my_page_point_record_arrow),
                    contentDescription = null,
                    tint = DateRoadTheme.colors.gray400
                )
            }
        }
    }
}

@Preview
@Composable
fun DateRoadMyPagePointBoxPreview() {
    DateRoadMyPagePointBox("호은", 200)
}
