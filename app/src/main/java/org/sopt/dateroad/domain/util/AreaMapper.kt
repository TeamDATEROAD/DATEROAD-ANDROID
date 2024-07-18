package org.sopt.dateroad.domain.util

import org.sopt.dateroad.domain.type.GyeonggiAreaType
import org.sopt.dateroad.domain.type.IncheonAreaType
import org.sopt.dateroad.domain.type.SeoulAreaType

fun Any?.toAreaTitle(): String = when (this) {
    is SeoulAreaType -> this.name
    is GyeonggiAreaType -> this.name
    is IncheonAreaType -> this.name
    else -> ""
}
