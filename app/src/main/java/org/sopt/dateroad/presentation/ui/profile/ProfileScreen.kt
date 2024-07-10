package org.sopt.dateroad.presentation.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import org.sopt.dateroad.R
import org.sopt.dateroad.presentation.type.DateChipGroupType
import org.sopt.dateroad.presentation.type.DateTagType
import org.sopt.dateroad.presentation.ui.component.bottomsheet.DateRoadBasicBottomSheet
import org.sopt.dateroad.presentation.ui.component.button.DateRoadFilledButton
import org.sopt.dateroad.presentation.ui.component.chipgroup.DateRoadDateChipGroup
import org.sopt.dateroad.presentation.ui.component.textfield.DateRoadTextFieldWithButton
import org.sopt.dateroad.presentation.ui.component.textfield.model.TextFieldValidateResult
import org.sopt.dateroad.presentation.ui.component.topbar.DateRoadBasicTopBar
import org.sopt.dateroad.presentation.util.modifier.noRippleClickable
import org.sopt.dateroad.presentation.util.view.LoadState
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun ProfileRoute(
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { profileSideEffect ->
                when (profileSideEffect) {
                    is ProfileContract.ProfileSideEffect.NavigateToHome -> {}
                }
            }
    }

    when (uiState.loadState) {
        LoadState.Idle -> {
            ProfileScreen(
                profileUiState = uiState,
                onImageButtonClicked = { viewModel.setEvent(ProfileContract.ProfileEvent.OnImageButtonClicked) },
                onNicknameValueChanged = { name -> viewModel.setEvent(ProfileContract.ProfileEvent.OnNicknameValueChanged(name = name)) },
                onDateChipClicked = { tag -> viewModel.setEvent(ProfileContract.ProfileEvent.OnDateChipClicked(tag = tag)) },
                onBottomSheetDismissRequest = { viewModel.setEvent(ProfileContract.ProfileEvent.OnBottomSheetDismissRequest) },
                onNicknameButtonClicked = { viewModel.setEvent(ProfileContract.ProfileEvent.OnNicknameButtonClicked) }
            )
        }

        else -> Unit
    }

    if (uiState.nicknameValidateResult == TextFieldValidateResult.Success && uiState.tag.isNotEmpty()) {
        viewModel.setEvent(ProfileContract.ProfileEvent.CheckEnrollButtonEnable(true))
    }

//    if (uiState.tag.isNotEmpty()) {
//        viewModel.setEvent(ProfileContract.ProfileEvent.CheckEnrollButtonEnable(true))
//    }
}

@Composable
fun ProfileScreen(
    profileUiState: ProfileContract.ProfileUiState = ProfileContract.ProfileUiState(),
    onImageButtonClicked: () -> Unit,
    onNicknameValueChanged: (String) -> Unit,
    onDateChipClicked: (DateTagType) -> Unit,
    onBottomSheetDismissRequest: () -> Unit,
    onNicknameButtonClicked: () -> Unit
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DateRoadTheme.colors.white)
            .padding(horizontal = 16.dp)
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DateRoadBasicTopBar(
            title = "내 프로필",
            backGroundColor = DateRoadTheme.colors.white
        )
        Spacer(modifier = Modifier.height(40.dp))
        Box(
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_enroll_profile_default),
                contentDescription = null,
                modifier = Modifier.size(128.dp)
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
            title = "닉네임",
            placeholder = "닉네임을 입력해 주세요",
            successDescription = "사용가능한 닉네임입니다.",
            validationErrorDescription = "최소 2글자를 입력해주세요.",
            conflictErrorDescription = "이미 사용중인 닉네임입니다.",
            buttonText = "중복확인",
            isButtonEnabled = profileUiState.isNicknameButtonEnabled,
            value = profileUiState.name,
            onValueChange = onNicknameValueChanged,
            onButtonClick = { onNicknameButtonClicked() }
        )
        Spacer(modifier = Modifier.height(23.dp))

        DateRoadDateChipGroup(
            dateChipGroupType = DateChipGroupType.PROFILE,
            selectedDateTags = profileUiState.tag,
            onSelectedDateTagsChanged = onDateChipClicked
        )

        Spacer(modifier = Modifier.weight(1f))

        DateRoadFilledButton(
            modifier = Modifier.fillMaxWidth(),
            isEnabled = profileUiState.isEnrollButtonEnabled,
            textContent = stringResource(id = R.string.enroll_profile_button),
            onClick = {},
            textStyle = DateRoadTheme.typography.bodyBold15,
            enabledBackgroundColor = DateRoadTheme.colors.deepPurple,
            enabledTextColor = DateRoadTheme.colors.white,
            disabledBackgroundColor = DateRoadTheme.colors.gray200,
            disabledTextColor = DateRoadTheme.colors.gray400,
            cornerRadius = 14.dp,
            paddingHorizontal = 0.dp,
            paddingVertical = 17.dp
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
    DateRoadBasicBottomSheet(
        isBottomSheetOpen = profileUiState.isBottomSheetOpen,
        title = "프로필 사진 설정",
        isButtonEnabled = false,
        buttonText = "취소",
        itemList = listOf(
            "사진 등록" to {
            },
            "사진 삭제" to {
            }
        ),
        onDismissRequest = { onBottomSheetDismissRequest() },
        onButtonClick = { onBottomSheetDismissRequest() }
    )
}
