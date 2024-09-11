package org.sopt.dateroad.presentation.ui.component.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.R
import org.sopt.dateroad.presentation.ui.component.button.DateRoadFilledButton
import org.sopt.dateroad.presentation.ui.component.textfield.model.TextFieldValidateResult
import org.sopt.dateroad.ui.theme.DATEROADTheme
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun DateRoadTextFieldWithButton(
    modifier: Modifier = Modifier,
    validateState: TextFieldValidateResult = TextFieldValidateResult.Basic,
    title: String? = null,
    titleDescription: String? = null,
    placeholder: String = "",
    successDescription: String = "",
    validationErrorDescription: String = "",
    conflictErrorDescription: String = "",
    buttonText: String,
    isButtonEnabled: Boolean,
    maxLength: Int = 5,
    value: String = "",
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChange: (String) -> Unit = { _ -> },
    onButtonClick: () -> Unit = {},
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Default),
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            title?.let { title ->
                Text(
                    text = title,
                    color = DateRoadTheme.colors.black,
                    style = DateRoadTheme.typography.bodyBold15
                )
            }
            titleDescription?.let { titleDescription ->
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = titleDescription,
                    color = DateRoadTheme.colors.gray300,
                    style = DateRoadTheme.typography.bodyMed13
                )
            }
        }
        Spacer(
            modifier = Modifier.padding(top = 10.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = DateRoadTheme.colors.gray100, shape = RoundedCornerShape(14.dp))
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 16.dp),
                value = value,
                onValueChange = {
                    val filteredValue = it.filter { char ->
                        char.toString().matches(Regex("[ㄱ-ㅎ가-힣a-zA-Z0-9]"))
                    }
                    if (filteredValue.length <= maxLength) onValueChange(filteredValue)
                },
                cursorBrush = SolidColor(DateRoadTheme.colors.purple600),
                singleLine = true,
                keyboardActions = keyboardActions,
                keyboardOptions = keyboardOptions,
                visualTransformation = visualTransformation,
                textStyle = DateRoadTheme.typography.bodySemi15,
                decorationBox = { innerTextField ->
                    innerTextField()
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            color = DateRoadTheme.colors.gray300,
                            style = DateRoadTheme.typography.bodySemi15
                        )
                    }
                }
            )
            Spacer(modifier = Modifier.width(12.dp))
            DateRoadFilledButton(
                isEnabled = isButtonEnabled,
                textContent = buttonText,
                textStyle = DateRoadTheme.typography.bodyMed13,
                enabledBackgroundColor = DateRoadTheme.colors.purple600,
                enabledTextColor = DateRoadTheme.colors.white,
                disabledBackgroundColor = DateRoadTheme.colors.gray200,
                disabledTextColor = DateRoadTheme.colors.gray400,
                cornerRadius = 10.dp,
                paddingHorizontal = 14.dp,
                paddingVertical = 6.dp,
                onClick = onButtonClick
            )
        }
        Spacer(modifier = Modifier.height(7.dp))
        Row {
            Text(
                modifier = Modifier
                    .weight(1f),
                text = when (validateState) {
                    is TextFieldValidateResult.Success -> successDescription
                    is TextFieldValidateResult.ValidationError -> validationErrorDescription
                    is TextFieldValidateResult.ConflictError -> conflictErrorDescription
                    else -> ""
                },
                color = if (validateState == TextFieldValidateResult.Success) DateRoadTheme.colors.purple600 else DateRoadTheme.colors.alertRed,
                style = DateRoadTheme.typography.capReg11
            )
            Text(
                text = stringResource(id = R.string.fraction_format, value.length, maxLength),
                color = DateRoadTheme.colors.gray300,
                style = DateRoadTheme.typography.capReg11
            )
        }
    }
}

@Preview
@Composable
fun DateRoadTextFieldWithButtonPreview() {
    DATEROADTheme {
        var text by remember { mutableStateOf("") }
        var validationState by remember { mutableStateOf<TextFieldValidateResult>(TextFieldValidateResult.Basic) }
        var isButtonEnabled by remember { mutableStateOf(false) }

        fun validateTest(text: String) {
            validationState = when {
                text.isEmpty() -> TextFieldValidateResult.Basic
                text.length < 2 -> TextFieldValidateResult.ValidationError
                else -> TextFieldValidateResult.Success
            }
        }

        DateRoadTextFieldWithButton(
            validateState = validationState,
            title = "닉네임",
            titleDescription = "(한글, 영문, 숫자만 가능)",
            placeholder = "닉네임을 입력해 주세요",
            successDescription = "사용가능한 닉네임입니다.",
            validationErrorDescription = "최소 2글자를 입력해주세요.",
            conflictErrorDescription = "이미 사용중인 닉네임입니다.",
            buttonText = "중복확인",
            isButtonEnabled = isButtonEnabled,
            value = text,
            onValueChange = { newValue ->
                text = newValue
                validateTest(text = newValue)
                isButtonEnabled = text.isNotEmpty()
            }
        )
    }
}
