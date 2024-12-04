package org.sopt.teamdateroad.presentation.ui.component.textfield.model

sealed class TextFieldValidateResult {
    data object Basic : TextFieldValidateResult()
    data object ValidationError : TextFieldValidateResult()
    data object ConflictError : TextFieldValidateResult()
    data object Success : TextFieldValidateResult()
}
