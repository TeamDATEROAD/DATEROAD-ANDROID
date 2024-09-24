package org.sopt.dateroad.presentation.ui.component.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import org.sopt.dateroad.presentation.util.LoadingView.CLIPMAX
import org.sopt.dateroad.presentation.util.LoadingView.CLIPMIN
import org.sopt.dateroad.presentation.util.LoadingView.LOTTIE
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun DateRoadLoadingView() {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.Asset(LOTTIE)
    )
    val lottieAnimatable = rememberLottieAnimatable()

    LaunchedEffect(composition) {
        lottieAnimatable.animate(
            composition = composition,
            clipSpec = LottieClipSpec.Frame(CLIPMIN, CLIPMAX),
            initialProgress = 0f,
            iteration = LottieConstants.IterateForever
        )
    }

    Box(
        modifier = Modifier.fillMaxSize().background(color = DateRoadTheme.colors.white),
        contentAlignment = Alignment.Center
    ) {
        LottieAnimation(
            composition = composition,
            iterations = LottieConstants.IterateForever,
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.BottomCenter),
            contentScale = ContentScale.FillWidth
        )
    }
}

@Preview
@Composable
fun DateLoadLoadingViewPreview() {
    DateRoadLoadingView()
}
