package org.sopt.dateroad.domain.type

import org.sopt.dateroad.domain.util.Region

enum class RegionType(
    val title: String
) {
    SEOUL(
        title = Region.SEOUL
    ),
    GYEONGGI(
        title = Region.GYEONGGI
    ),
    INCHEON(
        title = Region.INCHEON
    )
}
