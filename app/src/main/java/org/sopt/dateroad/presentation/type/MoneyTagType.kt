package org.sopt.dateroad.presentation.type

import androidx.annotation.StringRes
import org.sopt.dateroad.R

enum class MoneyTagType(
    @StringRes val titleRes: Int
) {
    EXCESS_100000(
        titleRes = R.string.money_tag_excess_100000
    ),
    LESS_THAN_100000(
        titleRes = R.string.money_tag_less_than_100000
    ),
    LESS_THAN_50000(
        titleRes = R.string.money_tag_less_than_50000
    ),
    LESS_THAN_30000(
        titleRes = R.string.money_tag_less_than_30000
    )
}