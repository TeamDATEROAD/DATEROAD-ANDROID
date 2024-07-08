package org.sopt.dateroad.presentation.ui.component.textfield.model

sealed class TextFieldValidateResult {
    data object Basic : TextFieldValidateResult()
    data object ValidationError : TextFieldValidateResult()
    data object ConflictError : TextFieldValidateResult()
    data object Success : TextFieldValidateResult()
}
