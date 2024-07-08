package org.sopt.dateroad.presentation.type

import androidx.annotation.DrawableRes
import org.sopt.dateroad.R

enum class PlaceCardType(
    @DrawableRes val iconRes: Int? = null
) {
    COURSE_NORMAL,
    COURSE_EDIT(
        iconRes = R.drawable.ic_date_schedule_move_course
    ),
    COURSE_DELETE(
        iconRes = R.drawable.ic_date_schedule_delete_course
    )
}
