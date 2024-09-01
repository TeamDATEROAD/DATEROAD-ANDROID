package org.sopt.dateroad.presentation.type

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import org.sopt.dateroad.R

enum class EmptyViewType(
    @StringRes val titleRes: Int,
    @DrawableRes val imageRes: Int
) {
    POINT_HISTORY_GAINED_HISTORY(
        titleRes = R.string.empty_view_point_history_earn_history,
        imageRes = R.drawable.img_empty_point_history_gained_history
    ),
    POINT_HISTORY_USED_HISTORY(
        titleRes = R.string.empty_view_point_history_usage_history,
        imageRes = R.drawable.img_empty_point_history_used_history
    ),
    READ(
        titleRes = R.string.empty_view_read,
        imageRes = R.drawable.ic_empty_read
    ),
    LOOK(
        titleRes = R.string.empty_view_look,
        imageRes = R.drawable.ic_empty_look
    ),
    TIMELINE(
        titleRes = R.string.empty_view_timeline,
        imageRes = R.drawable.ic_empty_timeline
    ),
    PAST(
        titleRes = R.string.empty_view_past,
        imageRes = R.drawable.ic_empty_default
    ),
    MY_COURSE_READ(
        titleRes = R.string.empty_view_my_course_read,
        imageRes = R.drawable.ic_empty_default
    ),
    MY_COURSE_ENROLL(
        titleRes = R.string.empty_view_my_course_enroll,
        imageRes = R.drawable.ic_empty_my_page_enroll
    )
}
