package org.sopt.dateroad.presentation.type

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import org.sopt.dateroad.R

enum class MyPageMenuType(
    @StringRes val titleRes: Int,
    @DrawableRes val iconRes: Int = R.drawable.ic_my_page_arrow
) {
    MY_COURSE_ENROLL(
        titleRes = R.string.my_page_menu_my_enroll_course
    ),
    POINT_SYSTEM(
        titleRes = R.string.my_page_menu_point_guide
    ),
    QUESTION(
        titleRes = R.string.my_page_menu_question
    ),
    LOGOUT(
        titleRes = R.string.my_page_menu_logout
    )
}
