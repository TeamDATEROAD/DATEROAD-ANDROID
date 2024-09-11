package org.sopt.dateroad.presentation.ui.profile

import org.sopt.dateroad.domain.model.EditProfile
import org.sopt.dateroad.domain.model.Profile
import org.sopt.dateroad.domain.model.SignUp
import org.sopt.dateroad.presentation.type.ProfileType
import org.sopt.dateroad.presentation.ui.component.textfield.model.TextFieldValidateResult
import org.sopt.dateroad.presentation.util.base.UiEvent
import org.sopt.dateroad.presentation.util.base.UiSideEffect
import org.sopt.dateroad.presentation.util.base.UiState
import org.sopt.dateroad.presentation.util.view.LoadState

class ProfileContract {
    data class ProfileUiState(
        val loadState: LoadState = LoadState.Idle,
        val signUpLoadState: LoadState = LoadState.Idle,
        val fetchProfileLoadState: LoadState = LoadState.Idle,
        val editProfileLoadState: LoadState = LoadState.Idle,
        val profileType: ProfileType = ProfileType.ENROLL,
        val signUp: SignUp = SignUp(),
        val editProfile: EditProfile = EditProfile(),
        val isNicknameButtonEnabled: Boolean = false,
        val isEnrollButtonEnabled: Boolean = false,
        val isNicknameChecked: Boolean = false,
        val isBottomSheetOpen: Boolean = false,
        val nicknameValidateResult: TextFieldValidateResult = TextFieldValidateResult.Basic,
        val currentProfile: Profile = Profile()
    ) : UiState

    sealed interface ProfileSideEffect : UiSideEffect {
        data object NavigateToHome : ProfileSideEffect
        data object NavigateToMyPage : ProfileSideEffect
        data object PopBackStack : ProfileSideEffect
    }

    sealed class ProfileEvent : UiEvent {
        data class GetNicknameCheck(val loadState: LoadState, val nicknameValidateResult: TextFieldValidateResult) : ProfileEvent()
        data class OnDateChipClicked(val tag: String) : ProfileEvent()
        data class OnNicknameValueChanged(val name: String) : ProfileEvent()
        data object OnImageButtonClicked : ProfileEvent()
        data class OnImageValueChanged(val image: String) : ProfileEvent()
        data object OnBottomSheetDismissRequest : ProfileEvent()
        data class CheckEnrollButtonEnable(val isEnrollButtonEnabled: Boolean) : ProfileEvent()
        data class PostSignUp(val signUpLoadState: LoadState) : ProfileEvent()
        data class PatchEditProfile(val editProfileLoadState: LoadState) : ProfileEvent()
        data class SetSignUpImage(val image: String) : ProfileEvent()
        data class SetEditProfileImage(val image: String) : ProfileEvent()
        data class InitProfileType(val profileType: ProfileType) : ProfileEvent()
        data class FetchProfile(
            val fetchProfileLoadState: LoadState,
            val editProfile: EditProfile,
            val currentProfile: Profile
        ) : ProfileEvent()
    }
}
