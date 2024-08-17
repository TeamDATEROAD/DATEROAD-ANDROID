package org.sopt.dateroad.presentation.ui.component.topbar

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import org.sopt.dateroad.R
import org.sopt.dateroad.presentation.util.modifier.noRippleClickable
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun DateRoadScrollResponsiveTopBar(
    isDefault: Boolean,
    defaultBackgroundColor: Color = Color.Transparent,
    scrolledBackgroundColor: Color = DateRoadTheme.colors.white,
    defaultIconTintColor: Color = DateRoadTheme.colors.white,
    scrolledIconTintColor: Color = DateRoadTheme.colors.black,
    @DrawableRes leftIconResource: Int = R.drawable.ic_top_bar_back_white,
    onLeftIconClick: () -> Unit = {},
    @DrawableRes rightIconResource: Int? = null,
    onRightIconClick: () -> Unit = {}
) {
    Box {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            DateRoadTheme.colors.black.copy(alpha = 0.6f),
                            Color.Transparent
                        )
                    )
                )
                .matchParentSize()
        )

        DateRoadBasicTopBar(
            backGroundColor = if (isDefault) defaultBackgroundColor else scrolledBackgroundColor,
            leftIconResource = leftIconResource,
            onLeftIconClick = onLeftIconClick,
            leftIconTint = if (isDefault) defaultIconTintColor else scrolledIconTintColor,
            buttonContent = {
                if (rightIconResource != null) {
                    Icon(
                        painter = painterResource(id = rightIconResource),
                        contentDescription = null,
                        tint = if (isDefault) defaultIconTintColor else scrolledIconTintColor,
                        modifier = Modifier.noRippleClickable(onRightIconClick)
                    )
                }
            }
        )
    }
}