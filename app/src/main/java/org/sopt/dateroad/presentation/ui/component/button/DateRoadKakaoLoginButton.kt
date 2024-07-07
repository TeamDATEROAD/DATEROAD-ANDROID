package org.sopt.dateroad.presentation.ui.component.button

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.R
import org.sopt.dateroad.ui.theme.DATEROADTheme
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun DateRoadKakaoLoginButton(
    modifier: Modifier = Modifier,
    backgroundColor: Color = DateRoadTheme.colors.kakao,
    contentColor: Color = DateRoadTheme.colors.black,
    onClick: () -> Unit = {}
) {
    DateRoadButton(
        modifier = modifier.fillMaxWidth(),
        backgroundColor = backgroundColor,
        cornerRadius = 6.dp,
        paddingVertical = 11.dp,
        paddingHorizontal = 14.dp,
        onClick = onClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_kakao_logo),
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.size(5.dp))
            Text(
                text = "카카오 로그인",
                style = DateRoadTheme.typography.bodyBold13,
                color = contentColor,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
fun DateRoadKakaoLoginButtonPreview() {
    DATEROADTheme {
        DateRoadKakaoLoginButton()
    }
}
