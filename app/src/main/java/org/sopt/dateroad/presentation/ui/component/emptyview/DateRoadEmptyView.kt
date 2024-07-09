package org.sopt.dateroad.presentation.ui.component.emptyview

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.dateroad.presentation.type.EmptyViewType
import org.sopt.dateroad.ui.theme.DATEROADTheme
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun DateRoadEmptyView(
    modifier: Modifier = Modifier,
    emptyViewType: EmptyViewType
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painter = painterResource(id = emptyViewType.imageRes), contentDescription = null)
        Text(
            text = stringResource(id = emptyViewType.titleRes),
            color = DateRoadTheme.colors.gray500,
            style = DateRoadTheme.typography.titleBold18
        )
    }
}

@Preview
@Composable
fun DateRoadEmptyViewPreview() {
    DATEROADTheme {
        DateRoadEmptyView(emptyViewType = EmptyViewType.POINT_HISTORY_GAINED_HISTORY)
    }
}