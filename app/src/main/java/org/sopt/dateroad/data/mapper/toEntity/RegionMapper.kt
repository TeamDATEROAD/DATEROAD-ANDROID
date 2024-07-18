package org.sopt.dateroad.data.mapper.toEntity

import org.sopt.dateroad.domain.type.GyeonggiAreaType.Companion.toGyeonggiAreaTitle
import org.sopt.dateroad.domain.type.RegionType
import org.sopt.dateroad.domain.type.SeoulAreaType.Companion.toSeoulAreaTitle

fun String.toRegionType(): RegionType = when {
    this.toSeoulAreaTitle().isNotEmpty() -> RegionType.SEOUL
    this.toGyeonggiAreaTitle().isNotEmpty() -> RegionType.GYEONGGI
    else -> RegionType.INCHEON
}