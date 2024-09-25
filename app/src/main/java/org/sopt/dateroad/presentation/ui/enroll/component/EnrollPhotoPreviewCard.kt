package org.sopt.dateroad.presentation.ui.enroll.component

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import org.sopt.dateroad.R
import org.sopt.dateroad.presentation.util.modifier.noRippleClickable
import org.sopt.dateroad.ui.theme.DATEROADTheme
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun EnrollPhotoPreviewCard(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    id: Int,
    isDeletable: Boolean,
    image: String,
    onDeleteButtonClick: (Int) -> Unit = {}
) {
    Box(
        modifier = modifier
            .width(130.dp)
            .aspectRatio(1f)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = context)
                .data(image)
                .crossfade(true)
                .build(),
            placeholder = null,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(14.dp))
        )
        if (isDeletable) {
            Image(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 6.dp, end = 6.dp)
                    .clip(CircleShape)
                    .background(DateRoadTheme.colors.gray200)
                    .padding(5.dp)
                    .noRippleClickable(onClick = { onDeleteButtonClick(id) }),
                painter = painterResource(id = R.drawable.ic_all_close),
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
fun EnrollPhotoPreviewCardPreview() {
    DATEROADTheme {
        EnrollPhotoPreviewCard(
            id = 0,
            isDeletable = true,
            image = ""
        )
    }
}
