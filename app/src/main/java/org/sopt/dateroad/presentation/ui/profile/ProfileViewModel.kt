package org.sopt.dateroad.presentation.ui.profile

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import org.sopt.dateroad.domain.model.EditProfile
import org.sopt.dateroad.presentation.type.DateTagType
import org.sopt.dateroad.presentation.ui.component.textfield.model.TextFieldValidateResult
import org.sopt.dateroad.presentation.util.base.BaseViewModel
import org.sopt.dateroad.presentation.util.view.LoadState

@HiltViewModel
class ProfileViewModel @Inject constructor() : BaseViewModel<ProfileContract.ProfileUiState, ProfileContract.ProfileSideEffect, ProfileContract.ProfileEvent>() {

    override fun createInitialState(): ProfileContract.ProfileUiState = ProfileContract.ProfileUiState()

    override suspend fun handleEvent(event: ProfileContract.ProfileEvent) {
        when (event) {
            is ProfileContract.ProfileEvent.ImageChanged -> setState { copy(image = event.image) }
            is ProfileContract.ProfileEvent.PostSignUp -> postSignUp(event.editProfile)
            is ProfileContract.ProfileEvent.OnDateChipClicked -> handleDateChipClicked(event.tag)
            is ProfileContract.ProfileEvent.OnImageButtonClicked -> setState { copy(isBottomSheetOpen = true) }
            is ProfileContract.ProfileEvent.FetchNicknameCheck -> fetchNicknameCheck(event.name)
            is ProfileContract.ProfileEvent.OnNicknameValueChanged -> handleNicknameValueChanged(event.name)
            is ProfileContract.ProfileEvent.OnBottomSheetDismissRequest -> setState { copy(isBottomSheetOpen = false) }
            is ProfileContract.ProfileEvent.OnNicknameButtonClicked -> handleNicknameCheck()
            is ProfileContract.ProfileEvent.CheckEnrollButtonEnable ->  setState { copy(isEnrollButtonEnabled = event.isEnrollButtonEnabled) }
        }
    }

    private fun handleDateChipClicked(dateTagType: DateTagType) {
        setState {
            val updatedTags = currentState.tag.toMutableList()
            when {
                updatedTags.contains(dateTagType) -> updatedTags -= dateTagType
                updatedTags.size < 3 -> updatedTags += dateTagType
            }
            copy(
                tag = updatedTags,
            )
        }
    }

    private fun handleNicknameValueChanged(name: String) {
        setState {
            copy(
                name = name,
                isNicknameButtonEnabled = when {
                    name.length in 2..5 -> true
                    else -> false
                }
            )
        }
    }

    private fun handleNicknameCheck() {
        setState {
            copy(
                nicknameValidateResult = when {
                    name.isEmpty() -> TextFieldValidateResult.Basic
                    isNicknameChecked -> TextFieldValidateResult.Success
                    else -> TextFieldValidateResult.Success
                },

            )
        }
    }

    private fun postSignUp(editProfile: EditProfile) {
        viewModelScope.launch {
            try {
                setState { copy(loadState = LoadState.Success) }
                // TODO: 서버 통신
                setSideEffect(ProfileContract.ProfileSideEffect.NavigateToHome)
            } catch (e: Exception) {
                setState { copy(loadState = LoadState.Error) }
            }
        }
    }

    private fun fetchNicknameCheck(name: String) {
        viewModelScope.launch {
            try {
                setState { copy(isNicknameChecked = true, loadState = LoadState.Success) }
            } catch (e: Exception) {
                setState { copy(loadState = LoadState.Error) }
            }
        }
    }
}
