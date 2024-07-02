package org.sopt.dateroad.presentation.ui.dummy

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import org.sopt.dateroad.domain.usecase.GetDummiesUseCase
import org.sopt.dateroad.presentation.util.base.BaseViewModel
import org.sopt.dateroad.presentation.util.view.UiState

@HiltViewModel
class DummyViewModel @Inject constructor(
    private val getDummiesUseCase: GetDummiesUseCase
) : BaseViewModel<DummyContract.DummyState, DummyContract.DummySideEffect, DummyContract.DummyEvent>() {
    override fun createInitialState(): DummyContract.DummyState =
        DummyContract.DummyState(dummyUiState = UiState.Idle)

    override suspend fun handleEvent(event: DummyContract.DummyEvent) {
        when (event) {
            is DummyContract.DummyEvent.OnDummyTvClicked -> {
                setSideEffect(DummyContract.DummySideEffect.ShowToast(message = "test"))
            }
        }
    }

    fun getDummies() {
        viewModelScope.launch {
            setState { copy(dummyUiState = UiState.Loading) }
            getDummiesUseCase(page = 1).onSuccess { dummies ->
                setState { copy(dummyUiState = UiState.Success(data = dummies)) }
            }.onFailure { exception: Throwable ->
                setState { copy(dummyUiState = UiState.Error(exception.message)) }
            }
        }
    }
}
