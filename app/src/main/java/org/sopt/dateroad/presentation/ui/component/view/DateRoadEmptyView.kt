package org.sopt.dateroad.presentation.ui.component.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.presentation.type.EmptyViewType
import org.sopt.dateroad.ui.theme.DATEROADTheme
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun DateRoadEmptyView(
    modifier: Modifier = Modifier,
    emptyViewType: EmptyViewType
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = emptyViewType.imageRes),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            text = stringResource(id = emptyViewType.titleRes),
            color = DateRoadTheme.colors.gray300,
            style = DateRoadTheme.typography.titleBold18,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun DateRoadEmptyViewPreview() {
    DATEROADTheme {
        DateRoadEmptyView(emptyViewType = EmptyViewType.LOOK)
    }
}
