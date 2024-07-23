package org.sopt.dateroad.presentation.type

import org.sopt.dateroad.R

enum class ProfileType(
    val topAppBarTitleRes: Int,
    val buttonTextRes: Int
) {
    Enroll(
        topAppBarTitleRes = R.string.profile_enroll_top_bar_title,
        buttonTextRes = R.string.enroll_profile_button
    ),
    Edit(
        topAppBarTitleRes = R.string.profile_edit_top_bar_title,
        buttonTextRes = R.string.edit_profile_button
    )
}
