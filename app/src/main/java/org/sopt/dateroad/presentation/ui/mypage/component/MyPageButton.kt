package org.sopt.dateroad.presentation.ui.mypage.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.presentation.type.MyPageMenuType
import org.sopt.dateroad.presentation.util.modifier.noRippleClickable
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun MyPageButton(
    modifier: Modifier = Modifier,
    myPageMenuType: MyPageMenuType,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .padding(top = 19.dp, bottom = 20.dp)
            .fillMaxWidth()
            .noRippleClickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = stringResource(id = myPageMenuType.titleRes), style = DateRoadTheme.typography.bodySemi15, color = DateRoadTheme.colors.black, modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.width(20.dp))
        Icon(painter = painterResource(id = myPageMenuType.iconRes), contentDescription = null)
    }
}

@Preview
@Composable
fun DateRoadMyPageButtonPreview() {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DateRoadTheme.colors.white)
    ) {
        MyPageButton(myPageMenuType = MyPageMenuType.MY_COURSE_ENROLL)
        MyPageButton(myPageMenuType = MyPageMenuType.POINT_SYSTEM)
        MyPageButton(myPageMenuType = MyPageMenuType.QUESTION)
        MyPageButton(myPageMenuType = MyPageMenuType.LOGOUT)
    }
}
