package org.sopt.dateroad.presentation.ui.enrollprofile

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.R
import org.sopt.dateroad.presentation.type.DateChipGroupType
import org.sopt.dateroad.presentation.type.DateTagType
import org.sopt.dateroad.presentation.ui.component.button.DateRoadFilledButton
import org.sopt.dateroad.presentation.ui.component.chipgroup.DateRoadDateChipGroup
import org.sopt.dateroad.presentation.ui.component.textfield.DateRoadTextFieldWithButton
import org.sopt.dateroad.presentation.ui.component.textfield.model.TextFieldValidateResult
import org.sopt.dateroad.presentation.ui.component.topbar.DateRoadBasicTopBar
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun EnrollProfileScreen() {
    val focusManager = LocalFocusManager.current
    var text by remember { mutableStateOf("") }
    var validationState by remember { mutableStateOf<TextFieldValidateResult>(TextFieldValidateResult.Basic) }
    var isNicknameButtonEnabled by remember { mutableStateOf(false) }
    var selectedDateTags by rememberSaveable { mutableStateOf<List<DateTagType>>(emptyList()) }
    var isEnrollButtonEnabled by remember { mutableStateOf(false) }

    fun updateEnrollButtonState() {
        isEnrollButtonEnabled = validationState == TextFieldValidateResult.Success && selectedDateTags.isNotEmpty()
    }

    fun validateNickname(text: String) {
        validationState = when {
            text.isEmpty() -> TextFieldValidateResult.Basic
            text.length < 2 -> TextFieldValidateResult.ValidationError
            else -> TextFieldValidateResult.Success
        }
        isNicknameButtonEnabled = text.isNotEmpty()
        updateEnrollButtonState()
    }

    val screenHeight = 708.dp

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DateRoadTheme.colors.white)
            .padding(horizontal = screenHeight * 16 / 708)
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
        Spacer(modifier = Modifier.height(screenHeight * 40 / 708))
        Box(
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_enroll_profile_default),
                contentDescription = null
            )
            Image(
                painter = painterResource(id = R.drawable.btn_my_profile_plus),
                contentDescription = null,
                modifier = Modifier.align(Alignment.BottomEnd)
            )
        }
        Spacer(modifier = Modifier.height(screenHeight * 40 / 708))

        DateRoadTextFieldWithButton(
            validateState = validationState,
            title = "닉네임",
            placeholder = "닉네임을 입력해 주세요",
            successDescription = "사용가능한 닉네임입니다.",
            validationErrorDescription = "최소 2글자를 입력해주세요.",
            conflictErrorDescription = "이미 사용중인 닉네임입니다.",
            buttonText = "중복확인",
            isButtonEnabled = isNicknameButtonEnabled,
            value = text,
            onValueChange = { newValue ->
                text = newValue
                validateNickname(text = newValue)
            }
        )
        Spacer(modifier = Modifier.height(screenHeight * 23 / 708))

        DateRoadDateChipGroup(
            dateChipGroupType = DateChipGroupType.PROFILE,
            selectedDateTags = selectedDateTags,
            onSelectedDateTagsChanged = { selectedDateTag ->
                selectedDateTags = if (selectedDateTags.contains(selectedDateTag)) {
                    selectedDateTags - selectedDateTag
                } else {
                    if (selectedDateTags.size < DateChipGroupType.PROFILE.maxSize) {
                        selectedDateTags + selectedDateTag
                    } else {
                        selectedDateTags
                    }
                }
                updateEnrollButtonState()
            }
        )

        Spacer(modifier = Modifier.weight(1f))

        DateRoadFilledButton(
            modifier = Modifier.fillMaxWidth(),
            isEnabled = isEnrollButtonEnabled,
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
}

@Preview
@Composable
fun EnrollProfilePreview() {
    EnrollProfileScreen()
}
