package org.sopt.dateroad.presentation.ui.pointguide

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.R
import org.sopt.dateroad.presentation.type.MyPagePointInfoType
import org.sopt.dateroad.presentation.ui.component.mypage.DateRoadMyPagePointInfo
import org.sopt.dateroad.presentation.ui.component.partialcolortext.PartialColorText
import org.sopt.dateroad.presentation.ui.component.topbar.DateRoadBasicTopBar
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun ProfileGuideRoute(
    popBackStack: () -> Unit
) {
    PointGuideScreen(onIconClick = popBackStack)
}

@Composable
fun PointGuideScreen(
    onIconClick: () -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(DateRoadTheme.colors.white)
    ) {
        DateRoadBasicTopBar(
            title = stringResource(id = R.string.top_bar_point_guide),
            iconLeftResource = R.drawable.ic_top_bar_back_white,
            backGroundColor = DateRoadTheme.colors.white,
            onIconClick = onIconClick
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(17.dp))
            Text(
                text = PartialColorText(
                    text = stringResource(id = R.string.point_guide_title),
                    keywords = listOf("포인트", "데이트 코스"),
                    color = DateRoadTheme.colors.deepPurple
                ),
                style = DateRoadTheme.typography.titleExtra20
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(id = R.string.point_guide_description),
                style = DateRoadTheme.typography.bodyMed15,
                color = DateRoadTheme.colors.gray500
            )
            Spacer(modifier = Modifier.height(24.dp))

            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                MyPagePointInfoType.entries.forEach { myPagePointInfoType ->
                    DateRoadMyPagePointInfo(myPagePointInfoType = myPagePointInfoType)
                }
            }
        }
    }
}

@Preview
@Composable
fun PointGuideScreenPreview() {
    PointGuideScreen(onIconClick = {})
}
