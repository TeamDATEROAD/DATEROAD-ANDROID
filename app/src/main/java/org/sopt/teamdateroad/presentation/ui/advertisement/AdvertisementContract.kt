package org.sopt.teamdateroad.presentation.ui.advertisement

import org.sopt.teamdateroad.domain.model.AdvertisementDetail
import org.sopt.teamdateroad.presentation.util.base.UiEvent
import org.sopt.teamdateroad.presentation.util.base.UiSideEffect
import org.sopt.teamdateroad.presentation.util.base.UiState
import org.sopt.teamdateroad.presentation.util.view.LoadState

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
