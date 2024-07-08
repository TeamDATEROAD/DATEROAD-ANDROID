package org.sopt.dateroad.presentation.type

import androidx.annotation.DrawableRes
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.R

enum class PlaceCardType(
    @DrawableRes val iconRes: Int? = null,
    val startPadding: Dp = 17.dp,
    val endPadding: Dp = 4.dp,
    val verticalPadding: Dp = 5.dp,
    val iconStartPadding: Dp = 16.dp,
    val iconTopPadding: Dp = 19.dp,
    val iconEndPadding: Dp = 16.dp,
    val iconBottomPadding: Dp = 20.dp

) {
    COURSE_NORMAL(
        startPadding = 14.dp,
        endPadding = 13.dp,
        verticalPadding = 13.dp
    ),
    COURSE_EDIT(
        iconRes = R.drawable.ic_date_schedule_move_course
    ),
    COURSE_DELETE(
        iconRes = R.drawable.ic_date_schedule_delete_course,
        iconStartPadding = 18.dp,
        iconTopPadding = 18.dp,
        iconEndPadding = 17.dp,
        iconBottomPadding = 17.dp
    )
}
