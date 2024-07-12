package org.sopt.dateroad.presentation.ui.pointguide

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.R
import org.sopt.dateroad.presentation.type.MyPagePointInfoType
import org.sopt.dateroad.presentation.ui.component.mypage.DateRoadMyPagePointInfo
import org.sopt.dateroad.presentation.ui.component.topbar.DateRoadBasicTopBar
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun PointGuideScreen() {
    Column(
        Modifier
            .fillMaxSize()
            .background(DateRoadTheme.colors.white)

    ) {
        DateRoadBasicTopBar(
            title = "포인트 제도 소개",
            iconLeftResource = R.drawable.ic_top_bar_back_white,
            backGroundColor = DateRoadTheme.colors.white,
            onIconClick = {}
        )
        Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
            Spacer(modifier = Modifier.height(17.dp))

            Text(text = "데이트로드는 포인트로\n 데이트 코스 열람할 수 있어도.")
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "포인트는 데이트 코스 등록하면 얻을 수 있어요.",
                style = DateRoadTheme.typography.bodyMed15,
                color = DateRoadTheme.colors.gray500
            )
            Spacer(modifier = Modifier.height(24.dp))

            DateRoadMyPagePointInfo(myPagePointInfoType = MyPagePointInfoType.FIRST)
            Spacer(modifier = Modifier.height(16.dp))
            DateRoadMyPagePointInfo(myPagePointInfoType = MyPagePointInfoType.SECOND)
            Spacer(modifier = Modifier.height(16.dp))
            DateRoadMyPagePointInfo(myPagePointInfoType = MyPagePointInfoType.THIRD)
            Spacer(modifier = Modifier.height(16.dp))
            DateRoadMyPagePointInfo(myPagePointInfoType = MyPagePointInfoType.FOURTH)
        }
    }
}

@Preview
@Composable
fun PointGuideScreenPreview() {
    PointGuideScreen()
}
