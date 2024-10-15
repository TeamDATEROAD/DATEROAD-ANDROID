package org.sopt.teamdateroad.data.mapper.toEntity

import org.sopt.teamdateroad.domain.type.GyeonggiAreaType.Companion.fromTitleToGyeonggiAreaType
import org.sopt.teamdateroad.domain.type.IncheonAreaType.Companion.fromTitleToIncheonAreaType
import org.sopt.teamdateroad.domain.type.RegionType
import org.sopt.teamdateroad.domain.type.SeoulAreaType.Companion.fromTitleToSeoulAreaType

fun String.toRegionType(): RegionType? = when {
    this.fromTitleToSeoulAreaType() != null -> RegionType.SEOUL

    this.fromTitleToGyeonggiAreaType() != null -> RegionType.GYEONGGI

    this.fromTitleToIncheonAreaType() != null -> RegionType.INCHEON

    else -> null
}
