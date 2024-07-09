package org.sopt.dateroad.presentation.ui.enroll.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.ui.theme.DATEROADTheme

@Composable
fun EnrollPhotoPreviewCard(
    modifier: Modifier = Modifier,
    isDeletable: Boolean,
    image: String,
    onDeleteButtonClick: (Int) -> Unit = {}
) {
    Box(
        modifier = modifier
            .width(130.dp)
            .aspectRatio(1f)
    ) {

    }
}

@Preview
@Composable
fun EnrollPhotoPreviewCardPreview() {
    DATEROADTheme {
        EnrollPhotoPreviewCard(
            isDeletable = true,
            image = ""
        )
    }
}