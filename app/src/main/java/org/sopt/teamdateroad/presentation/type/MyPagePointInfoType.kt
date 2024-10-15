package org.sopt.teamdateroad.presentation.type

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import org.sopt.teamdateroad.R

enum class MyPagePointInfoType(
    @StringRes val titleRes: Int,
    @StringRes val descriptionRes: Int,
    @DrawableRes val imageRes: Int = R.drawable.img_my_page_point_info_first

) {
    FIRST(
        titleRes = R.string.point_system_first_title,
        descriptionRes = R.string.point_system_first_description
    ),
    SECOND(
        titleRes = R.string.point_system_second_title,
        descriptionRes = R.string.point_system_second_description,
        imageRes = R.drawable.img_my_page_point_info_second
    ),
    THIRD(
        titleRes = R.string.point_system_third_title,
        descriptionRes = R.string.point_system_third_description,
        imageRes = R.drawable.img_my_page_point_info_third
    ),
    FOURTH(
        titleRes = R.string.point_system_fourth_title,
        descriptionRes = R.string.point_system_fourth_description,
        imageRes = R.drawable.img_my_page_point_info_fourth
    )
}
