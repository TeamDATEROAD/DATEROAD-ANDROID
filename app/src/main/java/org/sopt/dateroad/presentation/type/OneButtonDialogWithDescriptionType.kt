package org.sopt.dateroad.presentation.type

import androidx.annotation.StringRes
import org.sopt.dateroad.R

enum class OneButtonDialogWithDescriptionType(
    @StringRes val titleRes: Int,
    @StringRes val descriptionRes: Int,
    @StringRes val buttonTextRes: Int
) {
    ENROLL_COURSE(
        titleRes = R.string.one_button_dialog_with_description_enroll_course_title,
        descriptionRes = R.string.one_button_dialog_with_description_enroll_course_description,
        buttonTextRes = R.string.dialog_checked
    )
}