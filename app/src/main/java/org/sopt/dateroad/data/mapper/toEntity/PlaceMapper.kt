package org.sopt.dateroad.data.mapper.toEntity

import org.sopt.dateroad.domain.type.GyeonggiAreaType.Companion.toGyeonggiAreaTitle
import org.sopt.dateroad.domain.type.IncheonAreaType.Companion.toIncheonAreaTitle
import org.sopt.dateroad.domain.type.SeoulAreaType.Companion.toSeoulAreaTitle

fun String.toAreaTitle(): String = when {
    this.toSeoulAreaTitle().isNotEmpty() -> this.toSeoulAreaTitle()
    this.toGyeonggiAreaTitle().isNotEmpty() -> this.toGyeonggiAreaTitle()
    this.toIncheonAreaTitle().isNotEmpty() -> this.toIncheonAreaTitle()
    else -> this
}
