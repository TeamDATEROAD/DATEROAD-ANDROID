package org.sopt.dateroad.presentation.type

import androidx.annotation.StringRes
import org.sopt.dateroad.R

enum class TwoButtonDialogType(
    @StringRes val titleRes: Int,
    @StringRes val confirmButtonTextRes: Int,
    @StringRes val dismissButtonTextRes: Int
) {
    OPEN_KAKAOTALK(
        titleRes = R.string.two_button_dialog_open_kakaotalk_title,
        confirmButtonTextRes = R.string.two_button_dialog_open_kakaotalk_confirm_button_text,
        dismissButtonTextRes = R.string.dialog_cancel
    ),
    LOGOUT(
        titleRes = R.string.two_button_dialog_logout_title,
        confirmButtonTextRes = R.string.logout,
        dismissButtonTextRes = R.string.dialog_cancel
    )
}
