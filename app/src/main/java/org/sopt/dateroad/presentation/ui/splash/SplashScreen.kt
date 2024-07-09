package org.sopt.dateroad.presentation.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.dateroad.R
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun SplashScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DateRoadTheme.colors.deepPurple),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(226f / (226f + 284f)))
        Image(painter = painterResource(id = R.drawable.ic_splash_logo), contentDescription = "")
        Spacer(modifier = Modifier.weight(284f / (226f + 284f)))
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen()
}
