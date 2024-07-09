package org.sopt.dateroad.presentation.ui.dummy

import org.sopt.dateroad.domain.model.Dummy
import org.sopt.dateroad.presentation.util.base.UiState
import org.sopt.dateroad.presentation.util.base.UiEvent
import org.sopt.dateroad.presentation.util.base.UiSideEffect
import org.sopt.dateroad.presentation.util.view.MviState

class DummyContract {
    data class DummyUiState(
        val dummyUiState: MviState<List<Dummy>>
    ) : UiState

    sealed interface DummySideEffect : UiSideEffect {
        data class ShowToast(val message: String) : DummySideEffect
    }

    sealed class DummyEvent : UiEvent {
        data object OnDummyTvClicked : DummyEvent()
    }
}
