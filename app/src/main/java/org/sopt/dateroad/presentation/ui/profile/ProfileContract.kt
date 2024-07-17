package org.sopt.dateroad.presentation.ui.profile

import org.sopt.dateroad.domain.model.EditProfile
import org.sopt.dateroad.domain.model.SignUp
import org.sopt.dateroad.presentation.type.DateTagType
import org.sopt.dateroad.presentation.ui.component.textfield.model.TextFieldValidateResult
import org.sopt.dateroad.presentation.ui.signin.SignInContract.SignInEvent
import org.sopt.dateroad.presentation.util.base.UiEvent
import org.sopt.dateroad.presentation.util.base.UiSideEffect
import org.sopt.dateroad.presentation.util.base.UiState
import org.sopt.dateroad.presentation.util.view.LoadState

class ProfileContract {
    data class ProfileUiState(
        val loadState: LoadState = LoadState.Idle,
        val signUpLoadState: LoadState = LoadState.Idle,
        val signUp: SignUp = SignUp(),
        val isNicknameButtonEnabled: Boolean = false,
        val isEnrollButtonEnabled: Boolean = false,
        val isNicknameChecked: Boolean = false,
        val isBottomSheetOpen: Boolean = false,
        val nicknameValidateResult: TextFieldValidateResult = TextFieldValidateResult.Basic,
    ) : UiState

    sealed interface ProfileSideEffect : UiSideEffect {
        data object NavigateToHome : ProfileSideEffect
    }

    sealed class ProfileEvent : UiEvent {
        data class GetNicknameCheck(val loadState: LoadState, val nicknameValidateResult: TextFieldValidateResult) : ProfileEvent()
        data class OnDateChipClicked(val tag: String) : ProfileEvent()
        data class OnNicknameValueChanged(val name: String) : ProfileEvent()
        data object OnImageButtonClicked : ProfileEvent()
        data class OnImageValueChanged(val image: String) : ProfileEvent()
        data object OnBottomSheetDismissRequest : ProfileEvent()
        data class CheckEnrollButtonEnable(val isEnrollButtonEnabled: Boolean) : ProfileEvent()
        data class PostSignUp(val signUpLoadState: LoadState): ProfileEvent()
    }
}
