package org.sopt.dateroad.presentation.ui.component.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.dateroad.R
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun DateRoadLoadingView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = DateRoadTheme.colors.white),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(125f))
        Image(
            modifier = Modifier
                .fillMaxWidth(),
            painter = painterResource(id = R.drawable.img_loading_server),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = stringResource(id = R.string.loading_view_server),
            color = DateRoadTheme.colors.gray500,
            style = DateRoadTheme.typography.titleBold18,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.weight(155f))
    }
}

@Preview
@Composable
fun DateLoadLoadingViewPreview() {
    DateRoadLoadingView()
}
