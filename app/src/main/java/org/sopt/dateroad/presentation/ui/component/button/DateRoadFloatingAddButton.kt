package org.sopt.dateroad.presentation.ui.component.button

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.R
import org.sopt.dateroad.ui.theme.DATEROADTheme
import org.sopt.dateroad.ui.theme.DateRoadTheme
import org.sopt.dateroad.ui.theme.DeepPurple

@Composable
fun DateRoadFloatingAddButton(
    modifier: Modifier = Modifier,
    backgroundColor: Color = DateRoadTheme.colors.deepPurple
) {
    Row(
        modifier = modifier
            .shadow(
                elevation = 20.dp,
                shape = RoundedCornerShape(44.dp),
                clip = false
            )
            .clip(RoundedCornerShape(44.dp))
            .background(backgroundColor)
            .padding(14.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_all_plus_white),
            contentDescription = null
        )
    }
}

@Preview
@Composable
fun DateRoadFloatingAddButtonPreview() {
    DATEROADTheme {
        DateRoadFloatingAddButton()
    }
}
