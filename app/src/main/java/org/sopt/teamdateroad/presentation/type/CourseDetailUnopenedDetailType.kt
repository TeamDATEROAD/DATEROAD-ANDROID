package org.sopt.teamdateroad.presentation.type

import androidx.annotation.StringRes
import org.sopt.teamdateroad.R

enum class CourseDetailUnopenedDetailType(
    @StringRes val descriptionStringRes: Int,
    @StringRes val buttonTextStringRes: Int
) {
    POINT(
        descriptionStringRes = R.string.course_detail_point_read_button_description,
        buttonTextStringRes = R.string.course_detail_point_read_button
    ),
    FREE(
        descriptionStringRes = R.string.course_detail_free_read_button_description,
        buttonTextStringRes = R.string.course_detail_free_read_button
    )
}
