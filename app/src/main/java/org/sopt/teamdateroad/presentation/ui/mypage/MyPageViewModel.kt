package org.sopt.teamdateroad.presentation.ui.mypage

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import org.sopt.teamdateroad.domain.usecase.ClearUserInfoUseCase
import org.sopt.teamdateroad.domain.usecase.DeleteSignOutUseCase
import org.sopt.teamdateroad.domain.usecase.DeleteWithdrawUseCase
import org.sopt.teamdateroad.domain.usecase.GetUserUseCase
import org.sopt.teamdateroad.presentation.util.base.BaseViewModel
import org.sopt.teamdateroad.presentation.util.view.LoadState

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val clearUserInfoUseCase: ClearUserInfoUseCase,
    private val deleteWithdrawUserUseCase: DeleteWithdrawUseCase,
    private val deleteSignOutUseCase: DeleteSignOutUseCase,
    private val getUserUseCase: GetUserUseCase
) : BaseViewModel<MyPageContract.MyPageUiState, MyPageContract.MyPageSideEffect, MyPageContract.MyPageEvent>() {

    override fun createInitialState(): MyPageContract.MyPageUiState =
        MyPageContract.MyPageUiState()

    override suspend fun handleEvent(event: MyPageContract.MyPageEvent) {
        when (event) {
            is MyPageContract.MyPageEvent.DeleteLogout -> setState { copy(deleteSignOutLoadState = event.deleteSignOutLoadState) }
            is MyPageContract.MyPageEvent.DeleteWithdrawal -> setState { copy(showWithdrawalDialog = event.showWithdrawalDialog, deleteUserLoadState = event.deleteUserLoadState) }
            is MyPageContract.MyPageEvent.SetLogoutDialog -> setState { copy(showLogoutDialog = event.showLogoutDialog) }
            is MyPageContract.MyPageEvent.SetWithdrawalDialog -> setState { copy(showWithdrawalDialog = event.showWithdrawalDialog) }
            is MyPageContract.MyPageEvent.FetchProfile -> setState { copy(loadState = event.loadState, profile = event.profile) }
            is MyPageContract.MyPageEvent.OnWebViewClick -> setState { copy(showWebView = true) }
            is MyPageContract.MyPageEvent.WebViewClose -> setState { copy(showWebView = false) }
        }
    }

    fun fetchProfile() {
        viewModelScope.launch {
            setEvent(MyPageContract.MyPageEvent.FetchProfile(loadState = LoadState.Loading, profile = currentState.profile))
            getUserUseCase().onSuccess { profile ->
                setEvent(MyPageContract.MyPageEvent.FetchProfile(loadState = LoadState.Success, profile = profile))
            }.onFailure {
                setEvent(MyPageContract.MyPageEvent.FetchProfile(loadState = LoadState.Error, profile = currentState.profile))
            }
        }
    }

    fun deleteLogout() {
        viewModelScope.launch {
            setEvent(MyPageContract.MyPageEvent.DeleteLogout(showLogoutDialog = false, deleteSignOutLoadState = LoadState.Loading))
            deleteSignOutUseCase().onSuccess {
                setEvent(MyPageContract.MyPageEvent.DeleteLogout(showLogoutDialog = false, deleteSignOutLoadState = LoadState.Success))
                clearUserInfoUseCase()
            }.onFailure {
                setEvent(MyPageContract.MyPageEvent.DeleteLogout(showLogoutDialog = false, deleteSignOutLoadState = LoadState.Error))
            }
        }
    }

    fun withdrawal(authCode: String? = null) {
        viewModelScope.launch {
            setEvent(MyPageContract.MyPageEvent.DeleteWithdrawal(showWithdrawalDialog = true, deleteUserLoadState = LoadState.Loading))
            deleteWithdrawUserUseCase(authCode).onSuccess {
                setEvent(MyPageContract.MyPageEvent.DeleteWithdrawal(showWithdrawalDialog = false, deleteUserLoadState = LoadState.Success))
                clearUserInfoUseCase()
            }.onFailure {
                setEvent(MyPageContract.MyPageEvent.DeleteWithdrawal(showWithdrawalDialog = false, deleteUserLoadState = LoadState.Error))
            }
        }
    }
}
