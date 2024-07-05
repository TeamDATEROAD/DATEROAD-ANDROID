package org.sopt.dateroad.presentation.type

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import org.sopt.dateroad.R

enum class EmptyViewType(
    @StringRes titleRes: Int,
    @DrawableRes imageRes: Int
) {
    POINT_HISTORY_EARN_HISTORY(
        titleRes = R.string.empty_view_point_history_earn_history,
        imageRes = R.drawable.ic_launcher_foreground
    ),
    POINT_HISTORY_USAGE_HISTORY(
        titleRes = R.string.empty_view_point_history_usage_history,
        imageRes = R.drawable.ic_launcher_foreground
    ),
    READ(
        titleRes = R.string.empty_view_read,
        imageRes = R.drawable.ic_launcher_foreground
    ),
    LOOK(
        titleRes = R.string.empty_view_look,
        imageRes = R.drawable.ic_launcher_foreground
    ),
    TIMELINE(
        titleRes = R.string.empty_view_timeline,
        imageRes = R.drawable.ic_launcher_foreground
    ),
    PAST(
        titleRes = R.string.empty_view_past,
        imageRes = R.drawable.ic_launcher_foreground
    ),
    MY_COURSE_READ(
        titleRes = R.string.empty_view_my_course_read,
        imageRes = R.drawable.ic_launcher_foreground
    ),
    MY_COURSE_ENROLL(
        titleRes = R.string.empty_view_my_course_enroll,
        imageRes = R.drawable.ic_launcher_foreground
    )
}
