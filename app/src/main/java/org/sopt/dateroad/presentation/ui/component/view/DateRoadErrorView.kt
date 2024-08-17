package org.sopt.dateroad.presentation.ui.component.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.R
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun DateRoadErrorView(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = DateRoadTheme.colors.white),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(193f))
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 64.dp),
            painter = painterResource(id = R.drawable.ic_error_server),
            contentDescription = null,

        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = stringResource(id = R.string.error_view_server),
            color = DateRoadTheme.colors.gray500,
            style = DateRoadTheme.typography.titleBold18,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.weight(186f))
    }
}

@Preview
@Composable
fun ErrorPreview() {
    DateRoadErrorView()
}
