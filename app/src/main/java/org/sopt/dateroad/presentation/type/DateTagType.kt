package org.sopt.dateroad.presentation.type

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import org.sopt.dateroad.R

enum class DateTagType(
    @StringRes val titleRes: Int,
    @DrawableRes val imageRes: Int
) {
    DRIVE(
        titleRes = R.string.date_tag_drive,
        imageRes = R.drawable.ic_all_drive
    ),
    SHOPPING(
        titleRes = R.string.date_tag_shopping,
        imageRes = R.drawable.ic_all_shopping
    ),
    INDOORS(
        titleRes = R.string.date_tag_indoor,
        imageRes = R.drawable.ic_all_indoor
    ),
    HEALING(
        titleRes = R.string.date_tag_healing,
        imageRes = R.drawable.ic_all_healing
    ),
    ALCOHOL(
        titleRes = R.string.date_tag_alcohol,
        imageRes = R.drawable.ic_all_alcohol
    ),
    FOOD(
        titleRes = R.string.date_tag_epicurism,
        imageRes = R.drawable.ic_all_epicurism
    ),
    WORKSHOP(
        titleRes = R.string.date_tag_craft_shop,
        imageRes = R.drawable.ic_all_craft_shop
    ),
    NATURE(
        titleRes = R.string.date_tag_nature,
        imageRes = R.drawable.ic_all_nature
    ),
    ACTIVITY(
        titleRes = R.string.date_tag_activity,
        imageRes = R.drawable.ic_all_activity
    ),
    PERFORMANCE_MUSIC(
        titleRes = R.string.date_tag_performance_music,
        imageRes = R.drawable.ic_all_performance_music
    ),
    EXHIBITION_POPUP(
        titleRes = R.string.date_tag_exhibition_pop_up,
        imageRes = R.drawable.ic_all_exhibition_pop_up
    );

    companion object {
        fun String.getDateTagTypeByName(): DateTagType? = entries.find { it.name == this }
    }
}
