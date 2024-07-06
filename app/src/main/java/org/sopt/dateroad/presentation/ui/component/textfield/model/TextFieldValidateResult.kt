package org.sopt.dateroad.presentation.ui.component.textfield.model

import androidx.annotation.StringRes

sealed class TextFieldValidateResult {
    data object Basic : TextFieldValidateResult()
    data object Error : TextFieldValidateResult()
    data object Success: TextFieldValidateResult()
}