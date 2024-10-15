package org.sopt.teamdateroad.presentation.type

import androidx.annotation.StringRes
import org.sopt.teamdateroad.R

enum class EnrollType(
    @StringRes val topBarTitleRes: Int
) {
    COURSE(
        topBarTitleRes = R.string.top_bar_title_enroll_course
    ),
    TIMELINE(
        topBarTitleRes = R.string.top_bar_title_enroll_timeline
    )
}
