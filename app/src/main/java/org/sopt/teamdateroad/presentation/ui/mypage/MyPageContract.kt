package org.sopt.teamdateroad.presentation.ui.mypage

import org.sopt.teamdateroad.domain.model.Profile
import org.sopt.teamdateroad.presentation.util.base.UiEvent
import org.sopt.teamdateroad.presentation.util.base.UiSideEffect
import org.sopt.teamdateroad.presentation.util.base.UiState
import org.sopt.teamdateroad.presentation.util.view.LoadState

class MyPageContract {
    data class MyPageUiState(
        val loadState: LoadState = LoadState.Idle,
        val profile: Profile = Profile(),
        val showLogoutDialog: Boolean = false,
        val showWithdrawalDialog: Boolean = false,
        val showWebView: Boolean = false,
        val deleteUserLoadState: LoadState = LoadState.Idle,
        val deleteSignOutLoadState: LoadState = LoadState.Idle
    ) : UiState

    sealed interface MyPageSideEffect : UiSideEffect {
        data object NavigateToEditProfile : MyPageSideEffect
        data object NavigateToPointHistory : MyPageSideEffect
        data object NavigateToMyCourse : MyPageSideEffect
        data object NavigateToPointGuide : MyPageSideEffect
        data object NavigateToLogin : MyPageSideEffect
    }

    sealed class MyPageEvent : UiEvent {
        data class FetchProfile(val loadState: LoadState, val profile: Profile) : MyPageEvent()
        data class DeleteLogout(val showLogoutDialog: Boolean, val deleteSignOutLoadState: LoadState) : MyPageEvent()
        data class DeleteWithdrawal(val showWithdrawalDialog: Boolean, val deleteUserLoadState: LoadState) : MyPageEvent()
        data class SetLogoutDialog(val showLogoutDialog: Boolean) : MyPageEvent()
        data class SetWithdrawalDialog(val showWithdrawalDialog: Boolean) : MyPageEvent()
        data object OnWebViewClick : MyPageEvent()
        data object WebViewClose : MyPageEvent()
    }
}
