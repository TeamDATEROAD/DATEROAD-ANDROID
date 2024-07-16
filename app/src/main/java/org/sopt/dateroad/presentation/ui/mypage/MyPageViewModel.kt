package org.sopt.dateroad.presentation.ui.mypage

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import org.sopt.dateroad.domain.model.Profile
import org.sopt.dateroad.domain.usecase.DeleteWithdrawUseCase
import org.sopt.dateroad.presentation.util.base.BaseViewModel
import org.sopt.dateroad.presentation.util.view.LoadState

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val deleteWithdrawUserUseCase: DeleteWithdrawUseCase
) : BaseViewModel<MyPageContract.MyPageUiState, MyPageContract.MyPageSideEffect, MyPageContract.MyPageEvent>() {
    override fun createInitialState(): MyPageContract.MyPageUiState =
        MyPageContract.MyPageUiState()

    override suspend fun handleEvent(event: MyPageContract.MyPageEvent) {
        when (event) {
            is MyPageContract.MyPageEvent.FetchProfile -> setState { copy(loadState = event.loadState, profile = event.profile) }
            is MyPageContract.MyPageEvent.DeleteLogout -> setState { copy(loadState = event.loadState, showLogoutDialog = event.showLogoutDialog) }
            is MyPageContract.MyPageEvent.DeleteWithdrawal -> setState { copy(loadState = event.loadState, showWithdrawalDialog = event.showWithdrawalDialog) }
            is MyPageContract.MyPageEvent.SetLogoutDialog -> setState { copy(showLogoutDialog = event.showLogoutDialog) }
            is MyPageContract.MyPageEvent.SetWithdrawalDialog -> setState { copy(showWithdrawalDialog = event.showWithdrawalDialog) }
            is MyPageContract.MyPageEvent.SetSoonDialog -> setState { copy(showSoonDialog = event.showSoonDialog) }
        }
    }

    fun fetchProfile() {
        setEvent(MyPageContract.MyPageEvent.FetchProfile(loadState = LoadState.Success, profile = Profile(name = "지현", tag = listOf("드라이브", "쇼핑", "실내"), point = 100)))
    }

    fun deleteLogout() {
        setEvent(MyPageContract.MyPageEvent.DeleteLogout(loadState = LoadState.Success, showLogoutDialog = false))
    }

    fun deleteWithdrawal() {
        setEvent(MyPageContract.MyPageEvent.DeleteWithdrawal(loadState = LoadState.Success, showWithdrawalDialog = false))
    }

    fun withdrawal(userId: Int, authCode: String?) {
        viewModelScope.launch {
            setState { copy(loadState = LoadState.Loading) }
            deleteWithdrawUserUseCase(userId, authCode).onSuccess {
                setState {
                    copy(
                        loadState = LoadState.Success
                    )
                }
            }.onFailure {
                setState { copy(loadState = LoadState.Error) }
            }
        }
    }
}
