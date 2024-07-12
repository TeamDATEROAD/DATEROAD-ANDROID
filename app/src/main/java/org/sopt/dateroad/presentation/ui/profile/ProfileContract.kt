package org.sopt.dateroad.presentation.ui.profile

import org.sopt.dateroad.domain.model.EditProfile
import org.sopt.dateroad.presentation.type.DateTagType
import org.sopt.dateroad.presentation.ui.component.textfield.model.TextFieldValidateResult
import org.sopt.dateroad.presentation.util.base.UiEvent
import org.sopt.dateroad.presentation.util.base.UiSideEffect
import org.sopt.dateroad.presentation.util.base.UiState
import org.sopt.dateroad.presentation.util.view.LoadState

class ProfileContract {
    data class ProfileUiState(
        val loadState: LoadState = LoadState.Idle,
        val image: String = "",
        val name: String = "",
        val tag: List<DateTagType> = listOf(),
        val isNicknameButtonEnabled: Boolean = false,
        val isEnrollButtonEnabled: Boolean = false,
        val isNicknameChecked: Boolean = false,
        val isBottomSheetOpen: Boolean = false,
        val nicknameValidateResult: TextFieldValidateResult = TextFieldValidateResult.Basic
    ) : UiState

    sealed interface ProfileSideEffect : UiSideEffect {
        data object NavigateToHome : ProfileSideEffect
    }

    sealed class ProfileEvent : UiEvent {
        data class FetchNicknameCheck(val name: String) : ProfileEvent()
        data class OnEnrollButtonClicked(val editProfile: EditProfile) : ProfileEvent()
        data class OnDateChipClicked(val tag: DateTagType) : ProfileEvent()
        data class OnNicknameValueChanged(val name: String) : ProfileEvent()
        data object OnImageButtonClicked : ProfileEvent()
        data class OnImageValueChanged(val image: String) : ProfileEvent()
        data object OnBottomSheetDismissRequest : ProfileEvent()
        data object OnNicknameButtonClicked : ProfileEvent()
        data class CheckEnrollButtonEnable(val isEnrollButtonEnabled: Boolean) : ProfileEvent()
    }
}
