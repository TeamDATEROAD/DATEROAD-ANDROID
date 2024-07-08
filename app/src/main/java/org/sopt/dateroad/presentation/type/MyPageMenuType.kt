package org.sopt.dateroad.presentation.type

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import org.sopt.dateroad.R

enum class MyPageMenuType(
    @StringRes val titleRes: Int,
    @DrawableRes val iconRes: Int = R.drawable.ic_my_page_arrow
) {
    FIRST(
        titleRes = R.string.my_page_menu_my_enroll_course
    ),
    SECOND(
        titleRes = R.string.my_page_menu_point_guide
    ),
    THIRD(
        titleRes = R.string.my_page_menu_question
    ),
    FOURTH(
        titleRes = R.string.my_page_menu_logout
    )
}
