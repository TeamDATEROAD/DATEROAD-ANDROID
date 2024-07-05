package org.sopt.dateroad.presentation.type

import androidx.annotation.StringRes
import org.sopt.dateroad.R

enum class RegionType(
    @StringRes val nameRes: Int
) {
    SEOUL(
        nameRes = R.string.region_Seoul
    ),
    GYEONGGI(
        nameRes = R.string.region_gyeonggi
    ),
    INCHEON(
        nameRes = R.string.region_incheon
    )
}
