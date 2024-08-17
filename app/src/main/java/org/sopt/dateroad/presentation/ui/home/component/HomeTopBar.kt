package org.sopt.dateroad.presentation.ui.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import org.sopt.dateroad.R
import org.sopt.dateroad.presentation.ui.component.tag.DateRoadPointTag
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun DateRoadHomeTopBar(
    title: String = "0 P",
    profileImage: String? = null,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .padding(horizontal = 22.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_dateroad_logo),
            contentDescription = null,
            tint = DateRoadTheme.colors.white
        )
        Spacer(modifier = Modifier.weight(1f))
        DateRoadPointTag(
            text = title,
            profileImage = if (profileImage != null) {
                rememberAsyncImagePainter(profileImage)
            } else {
                painterResource(id = R.drawable.img_profile_small)
            },
            onClick = onClick
        )
    }
}

@Preview
@Composable
fun DateRoadHomeTopBarPreview() {
    Column {
        DateRoadHomeTopBar("5000 P")
    }
}