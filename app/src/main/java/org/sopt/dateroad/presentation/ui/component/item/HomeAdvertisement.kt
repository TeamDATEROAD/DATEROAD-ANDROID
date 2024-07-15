package org.sopt.dateroad.presentation.ui.component.item

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import org.sopt.dateroad.domain.model.Advertisement
import org.sopt.dateroad.presentation.util.modifier.noRippleClickable

@Composable
fun HomeAdvertisement(
    modifier: Modifier = Modifier,
    advertisement: Advertisement,
    onClick: (Int) -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .aspectRatio(328f / 132f)
            .noRippleClickable(onClick = { onClick(advertisement.advertisementId) })
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(advertisement.imageUrl)
                .crossfade(true)
                .build(),
            placeholder = null,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}

@Preview
@Composable
fun HomeAdvertisementPreview() {
    Column {
        HomeAdvertisement(
            modifier = Modifier,
            advertisement = Advertisement(
                advertisementId = 0,
                imageUrl = "www.naver.jpg"
            )
        )
    }
}
