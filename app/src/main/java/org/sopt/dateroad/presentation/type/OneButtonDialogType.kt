package org.sopt.dateroad.presentation.type

import androidx.annotation.StringRes
import org.sopt.dateroad.R

enum class OneButtonDialogType(
    @StringRes val titleRes: Int,
    @StringRes val buttonTextRes: Int
) {
    ENROLL_TIMELINE(
        titleRes = R.string.one_button_dialog_with_description_enroll_timeline_title,
        buttonTextRes = R.string.dialog_checked
    )
}
