package org.sopt.dateroad.presentation.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import org.sopt.dateroad.R
import org.sopt.dateroad.presentation.type.OnboardingType
import org.sopt.dateroad.presentation.ui.component.button.DateRoadFilledButton
import org.sopt.dateroad.presentation.ui.component.dotsindicator.DotsIndicator
import org.sopt.dateroad.presentation.ui.component.partialcolortext.PartialColorText
import org.sopt.dateroad.ui.theme.DateRoadTheme

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingRoute(
    viewModel: OnBoardingViewModel = hiltViewModel(),
    navigateToProfile: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { onBoardingSideEffect ->
                when (onBoardingSideEffect) {
                    is OnBoardingContract.OnBoardingSideEffect.NavigateToProfile -> navigateToProfile()
                }
            }
    }

    OnboardingScreen(
        onBoardingUiState = uiState,
        onEnrollProfileButtonClicked = { viewModel.setSideEffect(OnBoardingContract.OnBoardingSideEffect.NavigateToProfile) }
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen(
    onBoardingUiState: OnBoardingContract.OnBoardingUiState,
    pagerState: PagerState = rememberPagerState(),
    onEnrollProfileButtonClicked: () -> Unit
) {
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DateRoadTheme.colors.white)
    ) {
        HorizontalPager(
            count = OnboardingType.entries.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { page ->
            val onboardingType = OnboardingType.entries[page]
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = onboardingType.imageRes),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )
                Spacer(modifier = Modifier.height(22.dp))
                Text(
                    text = PartialColorText(
                        stringResource(id = onboardingType.titleRes),
                        keywords = listOf("포인트", "데이트코스", "100", "다양한"),
                        color = DateRoadTheme.colors.deepPurple
                    ),
                    style = DateRoadTheme.typography.titleExtra24,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = stringResource(id = onboardingType.descriptionRes),
                    style = DateRoadTheme.typography.bodySemi13,
                    color = DateRoadTheme.colors.gray500,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = stringResource(id = onboardingType.subDescriptionRes),
                    style = DateRoadTheme.typography.capReg11,
                    color = DateRoadTheme.colors.gray400,
                    textAlign = TextAlign.Center
                )
            }
        }
        Spacer(modifier = Modifier.height(14.dp))
        DateRoadFilledButton(
            modifier = Modifier.align(Alignment.CenterHorizontally).width(242.dp),
            isEnabled = true,
            textContent =
            if (pagerState.currentPage == OnboardingType.entries.size - 1) {
                stringResource(id = R.string.onboarding_profile_enroll)
            } else {
                stringResource(id = R.string.onboarding_next)
            },
            onClick = {
                if (pagerState.currentPage == OnboardingType.entries.size - 1) {
                    onEnrollProfileButtonClicked()
                } else {
                    scope.launch {
                        val nextPage = pagerState.currentPage + 1
                        pagerState.animateScrollToPage(nextPage)
                    }
                }
            },
            textStyle = DateRoadTheme.typography.bodyBold15,
            enabledBackgroundColor = DateRoadTheme.colors.deepPurple,
            enabledTextColor = DateRoadTheme.colors.white,
            disabledBackgroundColor = DateRoadTheme.colors.gray200,
            disabledTextColor = DateRoadTheme.colors.gray400,
            cornerRadius = 29.dp,
            paddingHorizontal = 0.dp,
            paddingVertical = 16.dp
        )
        Spacer(modifier = Modifier.height(15.dp))
        DotsIndicator(
            totalDots = OnboardingType.entries.size,
            selectedIndex = pagerState.currentPage,
            indicatorSize = 8.dp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(15.dp))
    }
}
