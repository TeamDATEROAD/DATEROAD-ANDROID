package org.sopt.dateroad.presentation.ui.enroll.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.R
import org.sopt.dateroad.presentation.type.TagType
import org.sopt.dateroad.presentation.ui.component.tag.DateRoadTextTag
import org.sopt.dateroad.presentation.util.modifier.noRippleClickable
import org.sopt.dateroad.ui.theme.DATEROADTheme
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun EnrollPhotos(
    modifier: Modifier = Modifier,
    isDeletable: Boolean,
    images: List<String>,
    onPhotoButtonClick: () -> Unit = {},
    onDeleteButtonClick: (Int) -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        LazyRow(
            modifier = Modifier.padding(bottom = 10.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (images.isEmpty()) {
                item {
                    EnrollAddPhotoButton(
                        onClick = onPhotoButtonClick
                    )
                }
            }
            items(images.size) { index ->
                EnrollPhotoPreviewCard(
                    id = index,
                    isDeletable = isDeletable,
                    image = images[index],
                    onDeleteButtonClick = { onDeleteButtonClick(index) }
                )
            }
        }
        if (images.isNotEmpty()) {
            Image(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 16.dp)
                    .clip(CircleShape)
                    .background(DateRoadTheme.colors.gray200)
                    .padding(horizontal = 9.dp, vertical = 10.dp)
                    .noRippleClickable(onClick = onPhotoButtonClick),
                painter = painterResource(id = R.drawable.ic_all_camera),
                contentDescription = null
            )
        }
        DateRoadTextTag(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 16.dp),
            textContent = stringResource(id = R.string.fraction_format, images.size, 10),
            tagContentType = TagType.ENROLL_PHOTO_NUMBER
        )
    }
}

@Preview
@Composable
fun EnrollPhotosPreview() {
    DATEROADTheme {
        EnrollPhotos(
            isDeletable = false,
            images = listOf()
        )
    }
}
