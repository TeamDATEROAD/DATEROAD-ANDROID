package org.sopt.dateroad.presentation.ui.enroll.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.R
import org.sopt.dateroad.presentation.util.modifier.noRippleClickable
import org.sopt.dateroad.ui.theme.DATEROADTheme
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun EnrollAddPhotoButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .width(130.dp)
            .aspectRatio(1f)
            .noRippleClickable(onClick = onClick)
            .clip(RoundedCornerShape(14.dp))
            .background(DateRoadTheme.colors.gray100),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(17.dp))
        Image(
            modifier = Modifier
                .clip(CircleShape)
                .background(DateRoadTheme.colors.gray200)
                .padding(horizontal = 9.dp, vertical = 10.dp),
            painter = painterResource(id = R.drawable.ic_all_camera),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(13.dp))
        Text(
            text = stringResource(id = R.string.enroll_add_photo),
            color = DateRoadTheme.colors.gray300,
            style = DateRoadTheme.typography.capBold11,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun EnrollAddPhotoButtonPreview() {
    DATEROADTheme {
        EnrollAddPhotoButton()
    }
}