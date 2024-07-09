package org.sopt.dateroad.presentation.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import org.sopt.dateroad.R
import org.sopt.dateroad.presentation.type.OnboardingType
import org.sopt.dateroad.presentation.ui.component.button.DateRoadFilledButton
import org.sopt.dateroad.ui.theme.DateRoadTheme


@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen(
    onFinish: () -> Unit, modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        HorizontalPager(
            count = 3, state = pagerState, modifier = Modifier.fillMaxSize()
        ) { page ->
            val onboardingPage = OnboardingType.entries[page]
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = onboardingPage.imageRes), contentDescription = null, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize()
                )

                Column(
                    modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
                ) {
                    Spacer(modifier = Modifier.height(463.dp))

                    Text(
                        text = getStyledText(stringResource(id = onboardingPage.titleRes)), style = DateRoadTheme.typography.titleExtra24, textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = stringResource(id = onboardingPage.descriptionRes), style = DateRoadTheme.typography.bodySemi13, color = DateRoadTheme.colors.gray500, textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = stringResource(id = onboardingPage.subDescriptionRes), style = DateRoadTheme.typography.capReg11, color = DateRoadTheme.colors.gray400, textAlign = TextAlign.Center
                    )
                }
            }
        }

        DotsIndicator(
            totalDots = 3, selectedIndex = pagerState.currentPage, modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 533.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(horizontal = 60.dp)
                .padding(bottom = 16.dp)
        ) {
            DateRoadFilledButton(
                modifier = Modifier.fillMaxWidth(),
                isEnabled = true,
                textContent =
                if (pagerState.currentPage == pagerState.pageCount - 1) stringResource(id = R.string.onboarding_profile_enroll)
                else stringResource(id = R.string.onboarding_next),
                onClick = {
                    scope.launch {
                        if (pagerState.currentPage == pagerState.pageCount - 1) {
                            onFinish()
                        } else {
                            val nextPage = pagerState.currentPage + 1
                            pagerState.animateScrollToPage(nextPage)
                        }
                    }
                }, textStyle = DateRoadTheme.typography.bodyBold15, enabledBackgroundColor = DateRoadTheme.colors.deepPurple, enabledTextColor = DateRoadTheme.colors.white, disabledBackgroundColor = DateRoadTheme.colors.gray200, disabledTextColor = DateRoadTheme.colors.gray400, cornerRadius = 29.dp, paddingHorizontal = 0.dp, paddingVertical = 16.dp
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}


@Composable
fun DotsIndicator(totalDots: Int, selectedIndex: Int, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Bottom, modifier = modifier.width(44.dp)
    ) {
        for (i in 0 until totalDots) {
            val color = if (i == selectedIndex) DateRoadTheme.colors.deepPurple else DateRoadTheme.colors.gray200
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(color, CircleShape)
            )
        }
    }
}

@Preview
@Composable
fun OnboardingPreview() {
    OnboardingScreen(onFinish = {})
}

@Composable
fun getStyledText(text: String): AnnotatedString {
    val keywords = listOf("포인트", "데이트코스", "100", "다양한")
    return buildAnnotatedString {
        var currentIndex = 0
        keywords.forEach { keyword ->
            val keywordIndex = text.indexOf(keyword, currentIndex, ignoreCase = true)
            if (keywordIndex >= 0) {
                append(text.substring(currentIndex, keywordIndex))
                withStyle(style = SpanStyle(color = DateRoadTheme.colors.deepPurple)) {
                    append(text.substring(keywordIndex, keywordIndex + keyword.length))
                }
                currentIndex = keywordIndex + keyword.length
            }
        }
        append(text.substring(currentIndex))
    }
}