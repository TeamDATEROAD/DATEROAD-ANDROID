package org.sopt.dateroad.presentation.ui.mypage

import org.sopt.dateroad.domain.model.Profile
import org.sopt.dateroad.presentation.util.base.UiEvent
import org.sopt.dateroad.presentation.util.base.UiSideEffect
import org.sopt.dateroad.presentation.util.base.UiState
import org.sopt.dateroad.presentation.util.view.LoadState

class MyPageContract {
    data class MyPageUiState(
        val loadState: LoadState = LoadState.Idle,
        val profile: Profile = Profile(),
        val showLogoutDialog: Boolean = false,
        val showWithdrawalDialog: Boolean = false,
        val showSoonDialog: Boolean = false
    ) : UiState

    sealed interface MyPageSideEffect : UiSideEffect {
        data object NavigateToPointHistory : MyPageSideEffect
        data object NavigateToMyCourse : MyPageSideEffect
    }

    sealed class MyPageEvent : UiEvent {
        data class FetchProfile(val loadState: LoadState, val profile: Profile) : MyPageEvent()
        data class DeleteLogout(val loadState: LoadState) : MyPageEvent()
        data class DeleteWithdrawal(val loadState: LoadState) : MyPageEvent()
        data class SetLogoutDialog(val showLogoutDialog: Boolean) : MyPageEvent()
        data class SetWithdrawalDialog(val showWithdrawalDialog: Boolean) : MyPageEvent()
        data class SetSoonDialog(val showSoonDialog: Boolean) : MyPageEvent()
    }
}
