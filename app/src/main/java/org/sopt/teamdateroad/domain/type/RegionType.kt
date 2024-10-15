package org.sopt.teamdateroad.domain.type

import org.sopt.teamdateroad.domain.util.Region

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
