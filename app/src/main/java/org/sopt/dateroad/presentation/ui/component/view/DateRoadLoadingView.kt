package org.sopt.dateroad.presentation.ui.component.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun DateRoadLoadingView(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier
        .fillMaxSize()
        .background(color = DateRoadTheme.colors.white))
}