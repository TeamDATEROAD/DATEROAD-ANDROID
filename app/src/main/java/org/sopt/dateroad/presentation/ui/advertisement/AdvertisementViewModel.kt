package org.sopt.dateroad.presentation.ui.advertisement

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.dateroad.domain.usecase.GetAdvertisementDetailUseCase
import org.sopt.dateroad.presentation.util.base.BaseViewModel
import org.sopt.dateroad.presentation.util.view.LoadState
import javax.inject.Inject

@HiltViewModel
class AdvertisementViewModel @Inject constructor(
    private val getAdvertisementDetailUseCase: GetAdvertisementDetailUseCase,
) : BaseViewModel<AdvertisementContract.AdvertisementUiState, AdvertisementContract.AdvertisementSideEffect, AdvertisementContract.AdvertisementEvent>() {
    override fun createInitialState(): AdvertisementContract.AdvertisementUiState = AdvertisementContract.AdvertisementUiState()

    override suspend fun handleEvent(event: AdvertisementContract.AdvertisementEvent) {
        when (event) {
            is AdvertisementContract.AdvertisementEvent.FetchAdvertisementDetail -> setState { copy(loadState = event.loadState, advertisementDetail = event.advertisementDetail) }
        }
    }

    fun fetchAdvertisementDetail(advertisementId: Int) {
        viewModelScope.launch {
            setEvent(AdvertisementContract.AdvertisementEvent.FetchAdvertisementDetail(loadState = LoadState.Loading, advertisementDetail = currentState.advertisementDetail))
            getAdvertisementDetailUseCase(advertisementId = advertisementId).onSuccess { advertisementDetail ->
                setEvent(AdvertisementContract.AdvertisementEvent.FetchAdvertisementDetail(loadState = LoadState.Success, advertisementDetail = advertisementDetail))
            }.onFailure {
                setEvent(AdvertisementContract.AdvertisementEvent.FetchAdvertisementDetail(loadState = LoadState.Error, advertisementDetail = currentState.advertisementDetail))
            }
        }
    }
}