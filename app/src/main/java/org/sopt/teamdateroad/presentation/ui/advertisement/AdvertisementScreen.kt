package org.sopt.teamdateroad.presentation.ui.advertisement

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import org.sopt.teamdateroad.presentation.ui.advertisement.component.AdvertisementDetail
import org.sopt.teamdateroad.presentation.ui.component.pager.DateRoadImagePager
import org.sopt.teamdateroad.presentation.ui.component.topbar.DateRoadScrollResponsiveTopBar
import org.sopt.teamdateroad.presentation.ui.component.view.DateRoadErrorView
import org.sopt.teamdateroad.presentation.ui.component.view.DateRoadIdleView
import org.sopt.teamdateroad.presentation.ui.component.view.DateRoadLoadingView
import org.sopt.teamdateroad.presentation.util.view.LoadState
import org.sopt.teamdateroad.ui.theme.DateRoadTheme

@Composable
fun AdvertisementRoute(
    viewmodel: AdvertisementViewModel = hiltViewModel(),
    popBackStack: () -> Unit,
    advertisementId: Int
) {
    val uiState by viewmodel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(viewmodel.sideEffect, lifecycleOwner) {
        viewmodel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { advertisementSideEffect ->
                when (advertisementSideEffect) {
                    is AdvertisementContract.AdvertisementSideEffect.PopBackStack -> popBackStack()
                }
            }
    }

    LaunchedEffect(Unit) {
        viewmodel.fetchAdvertisementDetail(advertisementId = advertisementId)
    }

    when (uiState.loadState) {
        LoadState.Idle -> DateRoadIdleView()

        LoadState.Loading -> DateRoadLoadingView()

        LoadState.Success -> AdvertisementScreen(
            advertisementUiState = uiState,
            onTopBarIconClicked = { viewmodel.setSideEffect(AdvertisementContract.AdvertisementSideEffect.PopBackStack) }
        )

        LoadState.Error -> DateRoadErrorView()
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun AdvertisementScreen(
    advertisementUiState: AdvertisementContract.AdvertisementUiState,
    onTopBarIconClicked: () -> Unit
) {
    var imageHeight by remember { mutableIntStateOf(0) }

    val scrollState = rememberLazyListState()
    val isScrollResponsiveDefault by remember {
        derivedStateOf {
            scrollState.firstVisibleItemIndex == 0 && scrollState.firstVisibleItemScrollOffset < imageHeight
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            state = scrollState,
            modifier = Modifier
                .fillMaxSize()
                .background(DateRoadTheme.colors.white)
        ) {
            with(advertisementUiState.advertisementDetail) {
                item {
                    DateRoadImagePager(
                        modifier = Modifier
                            .onGloballyPositioned { coordinates ->
                                imageHeight = coordinates.size.height
                            },
                        pagerState = PagerState(),
                        images = images,
                        userScrollEnabled = true,
                        like = null
                    )
                }

                item {
                    AdvertisementDetail(
                        advertisementTagTitle = advertisementTagTitle,
                        createAt = createAt,
                        title = title,
                        description = description
                    )
                }
            }
        }

        DateRoadScrollResponsiveTopBar(
            isDefault = isScrollResponsiveDefault,
            onLeftIconClick = onTopBarIconClicked
        )
    }
}
