package org.sopt.dateroad.presentation.type

import androidx.annotation.StringRes
import org.sopt.dateroad.R

enum class RegionType(
    @StringRes name: Int
) {
    SEOUL(
        name = R.string.region_Seoul
    ),
    GYEONGGI(
        name = R.string.region_gyeonggi
    ),
    INCHEON(
        name = R.string.region_incheon
    )
}