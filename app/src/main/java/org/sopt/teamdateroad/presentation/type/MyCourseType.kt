package org.sopt.teamdateroad.presentation.type

import androidx.annotation.StringRes
import org.sopt.teamdateroad.R

enum class MyCourseType(
    @StringRes val topBarTitleRes: Int
) {
    READ(
        topBarTitleRes = R.string.top_bar_title_my_course_read
    ),
    ENROLL(
        topBarTitleRes = R.string.top_bar_title_my_course_enroll
    )
}
