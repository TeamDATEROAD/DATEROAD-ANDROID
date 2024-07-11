package org.sopt.dateroad.presentation.ui.pastdate

import org.sopt.dateroad.presentation.util.base.UiEvent
import org.sopt.dateroad.presentation.util.base.UiSideEffect
import org.sopt.dateroad.presentation.util.base.UiState
import org.sopt.dateroad.presentation.util.view.LoadState

class PastDateContract {
    data class PastDateUiState(
        val loadState: LoadState = LoadState.Idle
    ) : UiState

    sealed interface PastDateSideEffect : UiSideEffect {
        data object PopBackStack : PastDateSideEffect
    }

    sealed class PastDateEvent : UiEvent
}
