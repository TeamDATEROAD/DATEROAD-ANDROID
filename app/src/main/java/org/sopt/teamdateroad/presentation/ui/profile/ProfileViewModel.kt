package org.sopt.teamdateroad.presentation.ui.profile

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import org.sopt.teamdateroad.data.mapper.todata.toEditProfile
import org.sopt.teamdateroad.domain.model.EditProfile
import org.sopt.teamdateroad.domain.model.SignUp
import org.sopt.teamdateroad.domain.usecase.GetNicknameCheckUseCase
import org.sopt.teamdateroad.domain.usecase.GetUserUseCase
import org.sopt.teamdateroad.domain.usecase.PatchEditProfileUseCase
import org.sopt.teamdateroad.domain.usecase.PostSignUpUseCase
import org.sopt.teamdateroad.domain.usecase.SetAccessTokenUseCase
import org.sopt.teamdateroad.domain.usecase.SetRefreshTokenUseCase
import org.sopt.teamdateroad.presentation.type.ProfileType
import org.sopt.teamdateroad.presentation.ui.component.textfield.model.TextFieldValidateResult
import org.sopt.teamdateroad.presentation.util.Token
import org.sopt.teamdateroad.presentation.util.base.BaseViewModel
import org.sopt.teamdateroad.presentation.util.view.LoadState

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getNicknameCheckUseCase: GetNicknameCheckUseCase,
    private val postSignUpUseCase: PostSignUpUseCase,
    private val setAccessTokenUseCase: SetAccessTokenUseCase,
    private val setRefreshTokenUseCase: SetRefreshTokenUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val patchEditProfileUseCase: PatchEditProfileUseCase
) : BaseViewModel<ProfileContract.ProfileUiState, ProfileContract.ProfileSideEffect, ProfileContract.ProfileEvent>() {

    override fun createInitialState(): ProfileContract.ProfileUiState = ProfileContract.ProfileUiState()

    override suspend fun handleEvent(event: ProfileContract.ProfileEvent) {
        when (event) {
            is ProfileContract.ProfileEvent.OnImageValueChanged -> setState { copy(signUp = currentState.signUp.copy(image = event.image)) }
            is ProfileContract.ProfileEvent.OnDateChipClicked -> setState {
                if (currentState.profileType == ProfileType.ENROLL) {
                    copy(
                        signUp = currentState.signUp.copy(
                            tag = currentState.signUp.tag.toMutableList().apply {
                                if (contains(event.tag)) {
                                    remove(event.tag)
                                } else if (size < 3) {
                                    add(event.tag)
                                }
                            }
                        )
                    )
                } else {
                    copy(
                        editProfile = currentState.editProfile.copy(
                            tags = currentState.editProfile.tags.toMutableList().apply {
                                if (contains(event.tag)) {
                                    remove(event.tag)
                                } else if (size < 3) {
                                    add(event.tag)
                                }
                            }
                        )
                    )
                }
            }
            is ProfileContract.ProfileEvent.OnImageButtonClicked -> setState { copy(isBottomSheetOpen = true) }
            is ProfileContract.ProfileEvent.GetNicknameCheck -> setState {
                copy(
                    loadState = event.loadState,
                    nicknameValidateResult = event.nicknameValidateResult
                )
            }

            is ProfileContract.ProfileEvent.OnNicknameValueChanged -> setState {
                if (currentState.profileType == ProfileType.ENROLL) {
                    copy(
                        signUp = currentState.signUp.copy(userSignUpInfo = currentState.signUp.userSignUpInfo.copy(name = event.name)),
                        isNicknameButtonEnabled = event.name.length in MIN_NICKNAME_LENGTH..MAX_NICKNAME_LENGTH,
                        nicknameValidateResult = when {
                            event.name.length < 2 -> TextFieldValidateResult.ValidationError
                            else -> TextFieldValidateResult.Basic
                        }
                    )
                } else {
                    copy(
                        editProfile = currentState.editProfile.copy(name = event.name),
                        isNicknameButtonEnabled = event.name.length in MIN_NICKNAME_LENGTH..MAX_NICKNAME_LENGTH,
                        nicknameValidateResult = when {
                            event.name.length < 2 -> TextFieldValidateResult.ValidationError
                            else -> TextFieldValidateResult.Basic
                        }
                    )
                }
            }

            is ProfileContract.ProfileEvent.OnBottomSheetDismissRequest -> setState { copy(isBottomSheetOpen = false) }
            is ProfileContract.ProfileEvent.CheckEnrollButtonEnable -> setState { copy(isEnrollButtonEnabled = event.isEnrollButtonEnabled) }
            is ProfileContract.ProfileEvent.PostSignUp -> setState { copy(signUpLoadState = event.signUpLoadState) }
            is ProfileContract.ProfileEvent.SetSignUpImage -> setState { copy(signUp = currentState.signUp.copy(image = event.image)) }
            is ProfileContract.ProfileEvent.SetEditProfileImage -> setState { copy(editProfile = currentState.editProfile.copy(image = event.image)) }
            is ProfileContract.ProfileEvent.InitProfileType -> setState { copy(profileType = event.profileType) }
            is ProfileContract.ProfileEvent.FetchProfile -> setState { copy(fetchProfileLoadState = event.fetchProfileLoadState, editProfile = event.editProfile, currentProfile = event.currentProfile) }
            is ProfileContract.ProfileEvent.PatchEditProfile -> setState { copy(editProfileLoadState = event.editProfileLoadState) }
        }
    }

    fun postSignUp(signUp: SignUp) {
        viewModelScope.launch {
            setEvent(ProfileContract.ProfileEvent.PostSignUp(signUpLoadState = LoadState.Loading))
            postSignUpUseCase(signUp = signUp).onSuccess { auth ->
                setEvent(ProfileContract.ProfileEvent.PostSignUp(signUpLoadState = LoadState.Success))
                setAccessTokenUseCase(accessToken = Token.BEARER + auth.accessToken)
                setRefreshTokenUseCase(refreshToken = auth.refreshToken)
            }.onFailure {
                setEvent(ProfileContract.ProfileEvent.PostSignUp(signUpLoadState = LoadState.Error))
            }
        }
    }

    fun getNicknameCheck(name: String) {
        viewModelScope.launch {
            setEvent(
                ProfileContract.ProfileEvent.GetNicknameCheck(loadState = LoadState.Loading, nicknameValidateResult = TextFieldValidateResult.Basic)
            )
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
                            nicknameValidateResult = if (currentState.currentProfile.name == name) {
                                TextFieldValidateResult.Success
                            } else {
                                TextFieldValidateResult.ConflictError
                            }
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

    fun fetchProfile() {
        viewModelScope.launch {
            setEvent(ProfileContract.ProfileEvent.FetchProfile(fetchProfileLoadState = LoadState.Loading, editProfile = currentState.editProfile, currentProfile = currentState.currentProfile))
            getUserUseCase().onSuccess { profile ->
                setEvent(ProfileContract.ProfileEvent.FetchProfile(fetchProfileLoadState = LoadState.Success, editProfile = profile.toEditProfile(), currentProfile = profile))
            }.onFailure {
                setEvent(ProfileContract.ProfileEvent.FetchProfile(fetchProfileLoadState = LoadState.Error, editProfile = currentState.editProfile, currentProfile = currentState.currentProfile))
            }
        }
    }

    fun patchEditProfile(editProfile: EditProfile) {
        viewModelScope.launch {
            setEvent(ProfileContract.ProfileEvent.PatchEditProfile(editProfileLoadState = LoadState.Loading))
            patchEditProfileUseCase(editProfile = editProfile).onSuccess {
                setEvent(ProfileContract.ProfileEvent.PatchEditProfile(editProfileLoadState = LoadState.Success))
            }.onFailure {
                setEvent(ProfileContract.ProfileEvent.PatchEditProfile(editProfileLoadState = LoadState.Error))
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
