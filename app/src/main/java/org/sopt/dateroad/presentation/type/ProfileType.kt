package org.sopt.dateroad.presentation.type

import androidx.annotation.StringRes
import org.sopt.dateroad.R

enum class ProfileType(
    @StringRes val topAppBarTitleRes: Int,
    @StringRes val buttonTextRes: Int
) {
    ENROLL(
        topAppBarTitleRes = R.string.profile_enroll_top_bar_title,
        buttonTextRes = R.string.enroll_profile_button
    ),
    EDIT(
        topAppBarTitleRes = R.string.profile_edit_top_bar_title,
        buttonTextRes = R.string.edit_profile_button
    )
}
