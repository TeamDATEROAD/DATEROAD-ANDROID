package org.sopt.dateroad.presentation.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import coil.compose.rememberAsyncImagePainter
import org.sopt.dateroad.R
import org.sopt.dateroad.presentation.type.DateChipGroupType
import org.sopt.dateroad.presentation.type.DateTagType
import org.sopt.dateroad.presentation.ui.component.bottomsheet.DateRoadBasicBottomSheet
import org.sopt.dateroad.presentation.ui.component.button.DateRoadBasicButton
import org.sopt.dateroad.presentation.ui.component.chipgroup.DateRoadDateChipGroup
import org.sopt.dateroad.presentation.ui.component.textfield.DateRoadTextFieldWithButton
import org.sopt.dateroad.presentation.ui.component.textfield.model.TextFieldValidateResult
import org.sopt.dateroad.presentation.ui.component.topbar.DateRoadBasicTopBar
import org.sopt.dateroad.presentation.util.modifier.noRippleClickable
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun ProfileRoute(
    viewModel: ProfileViewModel = hiltViewModel(),
    navigationToHome: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { profileSideEffect ->
                when (profileSideEffect) {
                    is ProfileContract.ProfileSideEffect.NavigateToHome -> {
                        navigationToHome()
                    }
                }
            }
    }

    ProfileScreen(
        profileUiState = uiState,
        onImageButtonClicked = { viewModel.setEvent(ProfileContract.ProfileEvent.OnImageButtonClicked) },
        onNicknameValueChanged = { name -> viewModel.setEvent(ProfileContract.ProfileEvent.OnNicknameValueChanged(name = name)) },
        onDateChipClicked = { tag -> viewModel.setEvent(ProfileContract.ProfileEvent.OnDateChipClicked(tag = tag)) },
        onBottomSheetDismissRequest = { viewModel.setEvent(ProfileContract.ProfileEvent.OnBottomSheetDismissRequest) },
        onNicknameButtonClicked = {
            viewModel.getNicknameCheck(uiState.name)
        },
        onEnrollButtonClicked = { viewModel.setSideEffect(ProfileContract.ProfileSideEffect.NavigateToHome) }
    )

    if (uiState.nicknameValidateResult == TextFieldValidateResult.Success && uiState.tag.isNotEmpty()) {
        viewModel.setEvent(ProfileContract.ProfileEvent.CheckEnrollButtonEnable(true))
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
    onEnrollButtonClicked: () -> Unit
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
            title = stringResource(id = R.string.profile_top_bar_title),
            backGroundColor = DateRoadTheme.colors.white
        )
        Spacer(modifier = Modifier.height(40.dp))
        Box(
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Image(
                painter = if (profileUiState.image.isEmpty()) {
                    painterResource(id = R.drawable.ic_enroll_profile_default)
                } else {
                    rememberAsyncImagePainter(model = profileUiState.image)
                },
                contentDescription = null,
                modifier = Modifier
                    .height(128.dp)
                    .aspectRatio(1f)
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
            placeholder = stringResource(id = R.string.profile_text_field_placeholder),
            successDescription = stringResource(id = R.string.profile_text_field_success_description),
            conflictErrorDescription = stringResource(id = R.string.profile_text_field_conflict_error_description),
            buttonText = stringResource(id = R.string.profile_text_field_button_text),
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

        DateRoadBasicButton(
            isEnabled = profileUiState.isEnrollButtonEnabled,
            textContent = stringResource(id = R.string.enroll_profile_button),
            onClick = onEnrollButtonClicked
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
    DateRoadBasicBottomSheet(
        isBottomSheetOpen = profileUiState.isBottomSheetOpen,
        title = stringResource(id = R.string.profile_bottom_sheet_title),
        isButtonEnabled = false,
        buttonText = stringResource(id = R.string.profile_bottom_sheet_button_text),
        itemList = listOf(
            stringResource(id = R.string.profile_bottom_sheet_button_enroll) to {
            },
            stringResource(id = R.string.profile_bottom_sheet_button_delete) to {
            }
        ),
        onDismissRequest = { onBottomSheetDismissRequest() },
        onButtonClick = { onBottomSheetDismissRequest() }
    )
}
