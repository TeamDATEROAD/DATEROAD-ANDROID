package org.sopt.dateroad.presentation.type

import androidx.annotation.StringRes
import org.sopt.dateroad.R

enum class MyCourseType(
    @StringRes val topBarTitleRes: Int,
) {
    READ(
        topBarTitleRes = R.string.top_bar_title_my_course_read
    ),
    ENROLL(
        topBarTitleRes = R.string.top_bar_title_my_course_enroll
    )
}