package org.sopt.dateroad.data.mapper.toEntity

import org.sopt.dateroad.domain.type.GyeonggiAreaType.Companion.fromTitleToGyeonggiAreaType
import org.sopt.dateroad.domain.type.IncheonAreaType.Companion.fromTitleToIncheonAreaType
import org.sopt.dateroad.domain.type.RegionType
import org.sopt.dateroad.domain.type.SeoulAreaType.Companion.fromTitleToSeoulAreaType

fun String.toRegionType(): RegionType? = when {
    this.fromTitleToSeoulAreaType() != null -> RegionType.SEOUL

    this.fromTitleToGyeonggiAreaType() != null -> RegionType.GYEONGGI

    this.fromTitleToIncheonAreaType() != null -> RegionType.INCHEON

    else -> null
}
