package org.sopt.teamdateroad.presentation.ui.component.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.sopt.teamdateroad.ui.theme.DateRoadTheme

@Composable
fun DateRoadIdleView(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = DateRoadTheme.colors.white)
    )
}
