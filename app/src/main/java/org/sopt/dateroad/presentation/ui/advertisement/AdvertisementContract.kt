package org.sopt.dateroad.presentation.ui.advertisement

import org.sopt.dateroad.domain.model.AdvertisementDetail
import org.sopt.dateroad.presentation.util.base.UiEvent
import org.sopt.dateroad.presentation.util.base.UiSideEffect
import org.sopt.dateroad.presentation.util.base.UiState
import org.sopt.dateroad.presentation.util.view.LoadState

class AdvertisementContract {
    data class AdvertisementUiState(
        val loadState: LoadState = LoadState.Idle,
        val advertisementDetail: AdvertisementDetail = AdvertisementDetail()
    ) : UiState

    sealed interface AdvertisementSideEffect : UiSideEffect {
        data object PopBackStack : AdvertisementSideEffect
    }

    sealed class AdvertisementEvent : UiEvent {
        data class FetchAdvertisementDetail(val loadState: LoadState, val advertisementDetail: AdvertisementDetail) : AdvertisementEvent()
    }
}
