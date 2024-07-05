package org.sopt.dateroad.presentation.type

import androidx.annotation.StringRes
import org.sopt.dateroad.R

enum class DateTagType(
    @StringRes val titleRes: Int
) {
    DRIVE(
        titleRes = R.string.date_tag_drive
    ),
    SHOPPING(
        titleRes = R.string.date_tag_shopping
    ),
    INDOOR(
        titleRes = R.string.date_tag_indoor
    ),
    HEALING(
        titleRes = R.string.date_tag_healing
    ),
    ALCOHOL(
        titleRes = R.string.date_tag_alcohol
    ),
    EPICURISM(
        titleRes = R.string.date_tag_epicurism
    ),
    CRAFT_SHOP(
        titleRes = R.string.date_tag_craft_shop
    ),
    NATURE(
        titleRes = R.string.date_tag_nature
    ),
    ACTIVITY(
        titleRes = R.string.date_tag_activity
    ),
    PERFORMANCE_MUSIC(
        titleRes = R.string.date_tag_performance_music
    ),
    EXHIBITION_POP_UP(
        titleRes = R.string.date_tag_exhibition_pop_up
    )
}