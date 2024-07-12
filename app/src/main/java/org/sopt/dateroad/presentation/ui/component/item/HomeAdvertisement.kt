package org.sopt.dateroad.presentation.ui.component.item

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import org.sopt.dateroad.domain.model.Advertisement
import org.sopt.dateroad.presentation.type.TagType
import org.sopt.dateroad.presentation.ui.component.tag.DateRoadTextTag
import org.sopt.dateroad.presentation.util.modifier.noRippleClickable
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun HomeAdvertisement(
    modifier: Modifier,
    advertisement: Advertisement,
    onClick: (Int) -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .aspectRatio(328f / 132f)
            .noRippleClickable(onClick = { onClick(advertisement.advertismentId) })
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
        Column(
            modifier = modifier.padding(13.dp)
        ) {
            DateRoadTextTag(
                textContent = advertisement.tag,
                tagContentType = TagType.ADVERTISEMENT_TITLE
            )
            Spacer(modifier = modifier.size(10.dp))
            Text(
                text = advertisement.title,
                style = DateRoadTheme.typography.bodyBold15,
                color = DateRoadTheme.colors.gray400,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview
@Composable
fun HomeAdvertisementPreview() {
    Column {
        HomeAdvertisement(
            modifier = Modifier,
            advertisement = Advertisement(
                advertismentId = 0,
                imageUrl = "www.naver.jpg",
                title = "관리자 아카이빙 게시물 이름",
                tag = "에디터 픽"
            )
        )
    }
}
