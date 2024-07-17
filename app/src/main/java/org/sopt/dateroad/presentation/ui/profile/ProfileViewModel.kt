package org.sopt.dateroad.presentation.ui.profile

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import org.sopt.dateroad.domain.model.EditProfile
import org.sopt.dateroad.domain.usecase.GetNicknameCheckUseCase
import org.sopt.dateroad.presentation.type.DateTagType
import org.sopt.dateroad.presentation.ui.component.textfield.model.TextFieldValidateResult
import org.sopt.dateroad.presentation.util.base.BaseViewModel
import org.sopt.dateroad.presentation.util.view.LoadState

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getNicknameCheckUseCase: GetNicknameCheckUseCase
) : BaseViewModel<ProfileContract.ProfileUiState, ProfileContract.ProfileSideEffect, ProfileContract.ProfileEvent>() {

    override fun createInitialState(): ProfileContract.ProfileUiState = ProfileContract.ProfileUiState()

    override suspend fun handleEvent(event: ProfileContract.ProfileEvent) {
        when (event) {
            is ProfileContract.ProfileEvent.OnImageValueChanged -> setState { copy(image = event.image) }
            is ProfileContract.ProfileEvent.OnEnrollButtonClicked -> postSignUp(event.editProfile)
            is ProfileContract.ProfileEvent.OnDateChipClicked -> handleDateChipClicked(event.tag)
            is ProfileContract.ProfileEvent.OnImageButtonClicked -> setState { copy(isBottomSheetOpen = true) }
            is ProfileContract.ProfileEvent.GetNicknameCheck -> setState {
                copy(
                    loadState = event.loadState,
                    nicknameValidateResult = event.nicknameValidateResult
                )
            }

            is ProfileContract.ProfileEvent.OnNicknameValueChanged -> handleNicknameValueChanged(event.name)
            is ProfileContract.ProfileEvent.OnBottomSheetDismissRequest -> setState { copy(isBottomSheetOpen = false) }
            is ProfileContract.ProfileEvent.CheckEnrollButtonEnable -> setState { copy(isEnrollButtonEnabled = event.isEnrollButtonEnabled) }
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
                tag = updatedTags
            )
        }
    }

    private fun handleNicknameValueChanged(name: String) {
        setState {
            copy(
                name = name,
                isNicknameButtonEnabled = name.length in MIN_NICKNAME_LENGTH..MAX_NICKNAME_LENGTH
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

    fun getNicknameCheck(name: String) {
        viewModelScope.launch {
            setEvent(ProfileContract.ProfileEvent.GetNicknameCheck(loadState = LoadState.Loading, nicknameValidateResult = TextFieldValidateResult.Basic))
            getNicknameCheckUseCase(name = name).onSuccess { code ->
                when (code) {
                    SUCCESS -> setEvent(
                        ProfileContract.ProfileEvent.GetNicknameCheck(
                            loadState = LoadState.Success,
                            nicknameValidateResult = TextFieldValidateResult.Success
                        )
                    )

                    CONFLICT -> setEvent(
                        ProfileContract.ProfileEvent.GetNicknameCheck(
                            loadState = LoadState.Success,
                            nicknameValidateResult = TextFieldValidateResult.ConflictError
                        )
                    )
                }
            }.onFailure {
                setEvent(
                    ProfileContract.ProfileEvent.GetNicknameCheck(
                        loadState = LoadState.Error,
                        nicknameValidateResult = TextFieldValidateResult.ValidationError
                    )
                )
            }
        }
    }

    companion object {
        const val MIN_NICKNAME_LENGTH = 2
        const val MAX_NICKNAME_LENGTH = 5
        const val SUCCESS = 200
        const val CONFLICT = 409
    }
}
