package org.sopt.dateroad.presentation.ui.profile

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import coil.compose.rememberAsyncImagePainter
import org.sopt.dateroad.R
import org.sopt.dateroad.data.mapper.todata.toEditProfile
import org.sopt.dateroad.presentation.type.DateChipGroupType
import org.sopt.dateroad.presentation.type.DateTagType
import org.sopt.dateroad.presentation.type.DateTagType.Companion.getDateTagTypeByName
import org.sopt.dateroad.presentation.type.ProfileType
import org.sopt.dateroad.presentation.ui.component.bottomsheet.DateRoadBasicBottomSheet
import org.sopt.dateroad.presentation.ui.component.button.DateRoadBasicButton
import org.sopt.dateroad.presentation.ui.component.chipgroup.DateRoadDateChipGroup
import org.sopt.dateroad.presentation.ui.component.textfield.DateRoadTextFieldWithButton
import org.sopt.dateroad.presentation.ui.component.textfield.model.TextFieldValidateResult
import org.sopt.dateroad.presentation.ui.component.topbar.DateRoadBasicTopBar
import org.sopt.dateroad.presentation.ui.component.view.DateRoadErrorView
import org.sopt.dateroad.presentation.ui.component.view.DateRoadLoadingView
import org.sopt.dateroad.presentation.util.Pattern.NICKNAME_REGEX
import org.sopt.dateroad.presentation.util.UserPropertyAmplitude.USER_NAME
import org.sopt.dateroad.presentation.util.amplitude.AmplitudeUtils
import org.sopt.dateroad.presentation.util.modifier.noRippleClickable
import org.sopt.dateroad.presentation.util.view.LoadState
import org.sopt.dateroad.ui.theme.DateRoadTheme

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ProfileRoute(
    viewModel: ProfileViewModel = hiltViewModel(),
    navigationToHome: () -> Unit,
    navigationToMyPage: () -> Unit,
    profileType: ProfileType,
    popBackStack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    val getGalleryLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        viewModel.setEvent(ProfileContract.ProfileEvent.SetSignUpImage(image = uri.toString()))
        viewModel.setEvent(ProfileContract.ProfileEvent.SetEditProfileImage(image = uri.toString()))
    }

    val getPhotoPickerLauncher = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri: Uri? ->
        viewModel.setEvent(ProfileContract.ProfileEvent.SetSignUpImage(image = uri.toString()))
        viewModel.setEvent(ProfileContract.ProfileEvent.SetEditProfileImage(image = uri.toString()))
    }

    LaunchedEffect(Unit) {
        viewModel.setEvent(ProfileContract.ProfileEvent.InitProfileType(profileType = profileType))
        if (profileType == ProfileType.EDIT) {
            viewModel.fetchProfile()
        }
    }

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { profileSideEffect ->
                when (profileSideEffect) {
                    is ProfileContract.ProfileSideEffect.NavigateToHome -> navigationToHome()
                    is ProfileContract.ProfileSideEffect.NavigateToMyPage -> navigationToMyPage()
                    is ProfileContract.ProfileSideEffect.PopBackStack -> popBackStack()
                }
            }
    }

    when (profileType) {
        ProfileType.ENROLL -> {
            when (uiState.signUpLoadState) {
                LoadState.Idle -> {
                    ProfileScreen(
                        profileUiState = uiState,
                        onImageButtonClicked = { viewModel.setEvent(ProfileContract.ProfileEvent.OnImageButtonClicked) },
                        onNicknameValueChanged = { name -> if (name.matches(NICKNAME_REGEX)) viewModel.setEvent(ProfileContract.ProfileEvent.OnNicknameValueChanged(name = name)) },
                        onDateChipClicked = { tag -> viewModel.setEvent(ProfileContract.ProfileEvent.OnDateChipClicked(tag = tag.name)) },
                        onBottomSheetDismissRequest = { viewModel.setEvent(ProfileContract.ProfileEvent.OnBottomSheetDismissRequest) },
                        onNicknameButtonClicked = { viewModel.getNicknameCheck(uiState.signUp.userSignUpInfo.name) },
                        onEnrollButtonClicked = {
                            viewModel.postSignUp(uiState.signUp)
                        },
                        selectPhoto = {
                            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
                                getGalleryLauncher.launch("image/*")
                            } else {
                                getPhotoPickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                            }
                        },
                        deletePhoto = { viewModel.setEvent(ProfileContract.ProfileEvent.SetSignUpImage(image = "")) },
                        popUpBackStack = { Unit }

                    )
                }

                LoadState.Loading -> DateRoadLoadingView()
                LoadState.Success -> {
                    navigationToHome()
                    AmplitudeUtils.updateStringUserProperty(propertyName = USER_NAME, propertyValue = uiState.signUp.userSignUpInfo.name)
                }

                LoadState.Error -> DateRoadErrorView()
            }
        }

        ProfileType.EDIT -> {
            when (uiState.editProfileLoadState) {
                LoadState.Idle -> {
                    ProfileScreen(
                        profileUiState = uiState,
                        onImageButtonClicked = { viewModel.setEvent(ProfileContract.ProfileEvent.OnImageButtonClicked) },
                        onNicknameValueChanged = { name -> if (name.matches(NICKNAME_REGEX)) viewModel.setEvent(ProfileContract.ProfileEvent.OnNicknameValueChanged(name = name)) },
                        onDateChipClicked = { tag -> viewModel.setEvent(ProfileContract.ProfileEvent.OnDateChipClicked(tag = tag.name)) },
                        onBottomSheetDismissRequest = { viewModel.setEvent(ProfileContract.ProfileEvent.OnBottomSheetDismissRequest) },
                        onNicknameButtonClicked = {
                            viewModel.getNicknameCheck(uiState.editProfile.name)
                        },
                        onEnrollButtonClicked = {
                            viewModel.patchEditProfile(uiState.editProfile)
                        },
                        selectPhoto = {
                            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
                                getGalleryLauncher.launch("image/*")
                            } else {
                                getPhotoPickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                            }
                        },
                        deletePhoto = { viewModel.setEvent(ProfileContract.ProfileEvent.SetEditProfileImage(image = "")) },
                        popUpBackStack = { popBackStack() }
                    )
                }

                LoadState.Loading -> DateRoadLoadingView()
                LoadState.Success -> {
                    navigationToMyPage()
                    AmplitudeUtils.updateStringUserProperty(propertyName = USER_NAME, propertyValue = uiState.editProfile.name)
                }

                LoadState.Error -> DateRoadErrorView()
            }
        }
    }

    when (uiState.profileType) {
        ProfileType.ENROLL -> if (uiState.nicknameValidateResult == TextFieldValidateResult.Success && (uiState.signUp.tag.isNotEmpty())) {
            viewModel.setEvent(ProfileContract.ProfileEvent.CheckEnrollButtonEnable(true))
        } else {
            viewModel.setEvent(ProfileContract.ProfileEvent.CheckEnrollButtonEnable(false))
        }

        ProfileType.EDIT -> {
            if ((uiState.editProfile.tags.isNotEmpty()) && uiState.currentProfile.toEditProfile() != uiState.editProfile || uiState.nicknameValidateResult == TextFieldValidateResult.Success) {
                viewModel.setEvent(ProfileContract.ProfileEvent.CheckEnrollButtonEnable(true))
            } else {
                viewModel.setEvent(ProfileContract.ProfileEvent.CheckEnrollButtonEnable(false))
            }
        }
    }
}

@Composable
fun ProfileScreen(
    profileUiState: ProfileContract.ProfileUiState = ProfileContract.ProfileUiState(),
    onImageButtonClicked: () -> Unit,
    onNicknameValueChanged: (String) -> Unit,
    onDateChipClicked: (DateTagType) -> Unit,
    onBottomSheetDismissRequest: () -> Unit,
    onNicknameButtonClicked: () -> Unit,
    onEnrollButtonClicked: () -> Unit,
    selectPhoto: () -> Unit,
    deletePhoto: () -> Unit,
    popUpBackStack: () -> Unit
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DateRoadTheme.colors.white)
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DateRoadBasicTopBar(
            leftIconResource = if (profileUiState.profileType == ProfileType.EDIT) {
                R.drawable.ic_top_bar_back_white
            } else {
                null
            },
            onLeftIconClick = popUpBackStack,
            title = stringResource(id = profileUiState.profileType.topAppBarTitleRes),
            backGroundColor = DateRoadTheme.colors.white
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Box(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Image(
                    painter = when (profileUiState.profileType) {
                        ProfileType.ENROLL -> if (profileUiState.signUp.image.isEmpty() || profileUiState.signUp.image == "null") {
                            painterResource(id = R.drawable.img_enroll_profile_default)
                        } else {
                            rememberAsyncImagePainter(model = profileUiState.signUp.image)
                        }

                        ProfileType.EDIT -> if (profileUiState.editProfile.image.isNullOrEmpty()) {
                            painterResource(id = R.drawable.img_enroll_profile_default)
                        } else {
                            rememberAsyncImagePainter(model = profileUiState.editProfile.image)
                        }
                    },
                    contentDescription = null,
                    modifier = Modifier
                        .height(128.dp)
                        .aspectRatio(1f)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Image(
                    painter = painterResource(id = R.drawable.btn_my_profile_plus),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .noRippleClickable(onClick = onImageButtonClicked)
                )
            }
            Spacer(modifier = Modifier.height(40.dp))

            DateRoadTextFieldWithButton(
                validateState = profileUiState.nicknameValidateResult,
                title = stringResource(id = R.string.profile_text_field_title),
                titleDescription = stringResource(id = R.string.profile_text_field_title_description),
                placeholder = if (profileUiState.profileType == ProfileType.ENROLL) {
                    stringResource(id = R.string.profile_text_field_placeholder)
                } else {
                    profileUiState.editProfile.name
                },
                validationErrorDescription = stringResource(id = R.string.profile_text_field_validation_error_description),
                successDescription = stringResource(id = R.string.profile_text_field_success_description),
                conflictErrorDescription = stringResource(id = R.string.profile_text_field_conflict_error_description),
                buttonText = stringResource(id = R.string.profile_text_field_button_text),
                isButtonEnabled = profileUiState.isNicknameButtonEnabled,
                value = if (profileUiState.profileType == ProfileType.ENROLL) {
                    profileUiState.signUp.userSignUpInfo.name
                } else {
                    profileUiState.editProfile.name
                },
                onValueChange = onNicknameValueChanged,
                onButtonClick = { onNicknameButtonClicked() }
            )
            Spacer(modifier = Modifier.height(23.dp))

            DateRoadDateChipGroup(
                dateChipGroupType = DateChipGroupType.PROFILE,
                selectedDateTags = if (profileUiState.profileType == ProfileType.ENROLL) {
                    profileUiState.signUp.tag.mapNotNull { it.getDateTagTypeByName() }
                } else {
                    profileUiState.editProfile.tags.mapNotNull { it.getDateTagTypeByName() }
                },
                onSelectedDateTagsChanged = onDateChipClicked
            )

            Spacer(modifier = Modifier.weight(1f))

            DateRoadBasicButton(
                isEnabled = profileUiState.isEnrollButtonEnabled,
                textContent = stringResource(id = profileUiState.profileType.buttonTextRes),
                onClick = onEnrollButtonClicked
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
    DateRoadBasicBottomSheet(
        isBottomSheetOpen = profileUiState.isBottomSheetOpen,
        title = stringResource(id = R.string.profile_bottom_sheet_title),
        isButtonEnabled = false,
        buttonText = stringResource(id = R.string.profile_bottom_sheet_button_text),
        itemList = listOf(
            stringResource(id = R.string.profile_bottom_sheet_button_enroll) to {
                selectPhoto()
            },
            stringResource(id = R.string.profile_bottom_sheet_button_delete) to {
                deletePhoto()
            }
        ),
        onDismissRequest = { onBottomSheetDismissRequest() },
        onButtonClick = { onBottomSheetDismissRequest() }

    )
}
