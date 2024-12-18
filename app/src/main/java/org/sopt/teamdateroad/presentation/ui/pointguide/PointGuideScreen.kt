package org.sopt.teamdateroad.presentation.ui.pointguide

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import org.sopt.teamdateroad.R
import org.sopt.teamdateroad.presentation.type.MyPagePointInfoType
import org.sopt.teamdateroad.presentation.ui.component.partialcolortext.PartialColorText
import org.sopt.teamdateroad.presentation.ui.component.topbar.DateRoadBasicTopBar
import org.sopt.teamdateroad.presentation.ui.pointguide.component.DateRoadMyPagePointInfo
import org.sopt.teamdateroad.ui.theme.DateRoadTheme

@Composable
fun ProfileGuideRoute(
    padding: PaddingValues,
    popBackStack: () -> Unit
) {
    PointGuideScreen(
        padding = padding,
        onIconClick = popBackStack
    )
}

@Composable
fun PointGuideScreen(
    padding: PaddingValues,
    onIconClick: () -> Unit
) {
    Column(
        Modifier
            .padding(paddingValues = padding)
            .fillMaxSize()
            .background(DateRoadTheme.colors.white)
    ) {
        DateRoadBasicTopBar(
            title = stringResource(id = R.string.point_guide_top_bar),
            leftIconResource = R.drawable.ic_top_bar_back_white,
            backGroundColor = DateRoadTheme.colors.white,
            onLeftIconClick = onIconClick
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
                    color = DateRoadTheme.colors.purple600
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
    PointGuideScreen(
        padding = PaddingValues(0.dp),
        onIconClick = {}
    )
}
