package org.sopt.teamdateroad.presentation.ui.component.pager

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import org.sopt.teamdateroad.R
import org.sopt.teamdateroad.presentation.type.TagType
import org.sopt.teamdateroad.presentation.ui.component.tag.DateRoadImageTag
import org.sopt.teamdateroad.presentation.ui.component.tag.DateRoadTextTag

@OptIn(ExperimentalPagerApi::class)
@Composable
fun DateRoadImagePager(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    images: List<String>,
    userScrollEnabled: Boolean,
    like: String?
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        HorizontalPager(
            count = images.size,
            state = pagerState,
            modifier = Modifier.fillMaxWidth(),
            userScrollEnabled = userScrollEnabled
        ) { page ->
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(images[page])
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop
            )
        }

        if (like != null) {
            DateRoadImageTag(
                textContent = like,
                imageContent = R.drawable.ic_tag_heart,
                tagContentType = TagType.HEART,
                modifier = Modifier
                    .padding(start = 10.dp, bottom = 10.dp)
                    .align(Alignment.BottomStart)
            )
        }

        DateRoadTextTag(
            textContent = stringResource(id = R.string.fraction_format, pagerState.currentPage + 1, pagerState.pageCount),
            tagContentType = TagType.COURSE_DETAIL_PHOTO_NUMBER,
            modifier = Modifier
                .padding(end = 10.dp, bottom = 10.dp)
                .align(Alignment.BottomEnd)
        )
    }
}
