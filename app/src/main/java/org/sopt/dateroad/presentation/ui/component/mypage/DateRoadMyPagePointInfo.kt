package org.sopt.dateroad.presentation.ui.component.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.presentation.type.MyPagePointInfoType
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun DateRoadMyPagePointInfo(myPagePointInfoType: MyPagePointInfoType) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(DateRoadTheme.colors.gray100)
            .padding(14.dp)
    ) {
        Image(painter = painterResource(id = myPagePointInfoType.imageRes), contentDescription = null)
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Text(
                text = stringResource(id = myPagePointInfoType.titleRes),
                style = DateRoadTheme.typography.bodyBold15,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(id = myPagePointInfoType.descriptionRes),
                color = DateRoadTheme.colors.gray500,
                style = DateRoadTheme.typography.bodyMed13,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
fun DateRoadMyPagePointInfoPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DateRoadTheme.colors.white)
            .padding(horizontal = 16.dp)
    ) {
        DateRoadMyPagePointInfo(myPagePointInfoType = MyPagePointInfoType.FIRST)
        Spacer(modifier = Modifier.height(16.dp))
        DateRoadMyPagePointInfo(myPagePointInfoType = MyPagePointInfoType.SECOND)
        Spacer(modifier = Modifier.height(16.dp))
        DateRoadMyPagePointInfo(myPagePointInfoType = MyPagePointInfoType.THIRD)
        Spacer(modifier = Modifier.height(16.dp))
        DateRoadMyPagePointInfo(myPagePointInfoType = MyPagePointInfoType.FOURTH)
    }
}
