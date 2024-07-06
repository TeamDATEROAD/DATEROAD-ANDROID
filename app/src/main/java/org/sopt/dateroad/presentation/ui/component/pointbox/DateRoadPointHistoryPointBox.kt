package org.sopt.dateroad.presentation.ui.component.pointbox

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.ui.theme.DateRoadTheme
import org.sopt.dateroad.ui.theme.DeepPurple
import org.sopt.dateroad.ui.theme.White

@Composable
fun DateRoadPointHistoryPointBox(nickname: String, point: Int) {
    Column(
        modifier = Modifier
            .size(width = 328.dp, height = 90.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(DeepPurple)
    ) {
        Column(modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 14.dp)) {
            Text(text = "$nickname 님의 포인트", color = White, style = DateRoadTheme.typography.bodyMed13)
            Spacer(modifier = Modifier.height(11.dp))
            Text(text = "$point P", color = White, style = DateRoadTheme.typography.titleExtra24)
        }
    }
}

@Preview
@Composable
fun DateRoadPointHistoryPointBoxPreview() {
    DateRoadPointHistoryPointBox("호은", 200)
}
