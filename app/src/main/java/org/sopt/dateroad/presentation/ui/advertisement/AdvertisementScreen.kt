package org.sopt.dateroad.presentation.ui.advertisement

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun AdvertisementRoute(
    viewmodel: AdvertisementViewModel = hiltViewModel(),
    popBackStack: () -> Unit,
    advertisementId: Int
) {
    val uiState by viewmodel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current
}

@Composable
fun AdvertisementScreen(

) {

}