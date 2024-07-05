package org.sopt.dateroad.presentation.type

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import org.sopt.dateroad.R

enum class PointSystemType(
    @StringRes val titleRes: Int,
    @StringRes val descriptionRes: Int,
    @DrawableRes val imageRes: Int
) {
    FIRST(
        titleRes = R.string.point_system_first_title,
        descriptionRes = R.string.point_system_first_description,
        imageRes = R.drawable.ic_launcher_foreground
    ),
    SECOND(
        titleRes = R.string.point_system_second_title,
        descriptionRes = R.string.point_system_second_description,
        imageRes = R.drawable.ic_launcher_foreground
    ),
    THIRD(
        titleRes = R.string.point_system_third_title,
        descriptionRes = R.string.point_system_third_description,
        imageRes = R.drawable.ic_launcher_foreground
    ),
    FOURTH(
        titleRes = R.string.point_system_fourth_title,
        descriptionRes = R.string.point_system_fourth_description,
        imageRes = R.drawable.ic_launcher_foreground
    )
}