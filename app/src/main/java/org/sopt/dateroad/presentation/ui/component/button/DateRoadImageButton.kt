package org.sopt.dateroad.presentation.ui.component.button

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.R
import org.sopt.dateroad.ui.theme.DATEROADTheme
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun DateRoadImageButton(
    modifier: Modifier = Modifier,
    isEnabled: Boolean = false,
    enabledBackgroundColor: Color = DateRoadTheme.colors.deepPurple,
    enabledContentColor: Color = DateRoadTheme.colors.white,
    disabledBackgroundColor: Color = DateRoadTheme.colors.gray200,
    disabledContentColor: Color = DateRoadTheme.colors.gray400,
    iconResId: Int = R.drawable.ic_all_plus_white,
    cornerRadius: Dp,
    paddingHorizontal: Dp,
    paddingVertical: Dp,
    onClick: () -> Unit
) {
    val backgroundColor = if (isEnabled) enabledBackgroundColor else disabledBackgroundColor
    val contentColor = if (isEnabled) enabledContentColor else disabledContentColor

    DateRoadButton(
        modifier = modifier,
        backgroundColor = backgroundColor,
        cornerRadius = cornerRadius,
        paddingHorizontal = paddingHorizontal,
        paddingVertical = paddingVertical,
        onClick = onClick
    ) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = null,
            tint = contentColor
        )
    }
}

@Preview
@Composable
fun DateRoadImageButtonPreview() {
    DATEROADTheme {
        Column {
            DateRoadImageButton(
                isEnabled = true,
                onClick = {},
                cornerRadius = 14.dp,
                paddingHorizontal = 16.dp,
                paddingVertical = 8.dp
            )
            DateRoadImageButton(
                isEnabled = true,
                onClick = {},
                cornerRadius = 14.dp,
                paddingHorizontal = 12.dp,
                paddingVertical = 12.dp
            )
            DateRoadImageButton(
                isEnabled = false,
                onClick = {},
                cornerRadius = 14.dp,
                paddingHorizontal = 12.dp,
                paddingVertical = 12.dp
            )
            DateRoadImageButton(
                isEnabled = true,
                onClick = {},
                cornerRadius = 44.dp,
                paddingHorizontal = 12.dp,
                paddingVertical = 12.dp
            )
        }
    }
}
