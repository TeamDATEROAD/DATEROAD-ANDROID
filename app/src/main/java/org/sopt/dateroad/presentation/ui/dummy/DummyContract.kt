package org.sopt.dateroad.presentation.ui.dummy

import org.sopt.dateroad.domain.model.Dummy
import org.sopt.dateroad.presentation.util.base.State
import org.sopt.dateroad.presentation.util.base.UiEvent
import org.sopt.dateroad.presentation.util.base.UiSideEffect
import org.sopt.dateroad.presentation.util.view.UiState

class DummyContract {
    data class DummyState(
        val dummyUiState: UiState<List<Dummy>>
    ) : State

    sealed interface DummySideEffect : UiSideEffect {
        data class ShowToast(val message: String) : DummySideEffect
    }

    sealed class DummyEvent : UiEvent {
        data object OnDummyTvClicked : DummyEvent()
    }
}
