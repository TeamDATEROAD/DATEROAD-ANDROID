package org.sopt.dateroad.presentation.ui.component.item

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import org.sopt.dateroad.domain.model.Advertisement
import org.sopt.dateroad.presentation.type.TagType
import org.sopt.dateroad.presentation.ui.component.tag.DateRoadTextTag
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun DateRoadAdvertisement(
    advertisement: Advertisement
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(132.dp)
            .clip(RoundedCornerShape(14.dp))
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(advertisement.imageUrl)
                .crossfade(true)
                .build(),
            placeholder = null,
            contentDescription = null
        )
        Column(
            modifier = Modifier.padding(13.dp)
        ) {
            DateRoadTextTag(
                textContent = advertisement.tag,
                tagContentType = TagType.ADVERTISEMENT_TITLE
            )
            Spacer(modifier = Modifier.size(10.dp))
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
fun DateRoadAdvertisementPreview() {
    Column {
        DateRoadAdvertisement(
            advertisement = Advertisement(
                advertismentId = 0,
                imageUrl = "www.naver.jpg",
                title = "관리자 아카이빙 게시물 이름",
                tag = "에디터 픽"
            )
        )
    }
}
