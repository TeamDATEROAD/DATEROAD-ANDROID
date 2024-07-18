package org.sopt.dateroad.data.mapper.toEntity

import android.util.Log
import org.sopt.dateroad.domain.type.GyeonggiAreaType
import org.sopt.dateroad.domain.type.GyeonggiAreaType.Companion.toGyeonggiAreaTitle
import org.sopt.dateroad.domain.type.GyeonggiAreaType.Companion.toGyeonggiAreaType
import org.sopt.dateroad.domain.type.IncheonAreaType
import org.sopt.dateroad.domain.type.IncheonAreaType.Companion.toIncheonAreaTitle
import org.sopt.dateroad.domain.type.IncheonAreaType.Companion.toIncheonAreaType
import org.sopt.dateroad.domain.type.SeoulAreaType
import org.sopt.dateroad.domain.type.SeoulAreaType.Companion.toSeoulAreaTitle
import org.sopt.dateroad.domain.type.SeoulAreaType.Companion.toSeoulAreaType

fun Any?.toAreaTitle(): String = when (this) {
    is SeoulAreaType -> this.title
    is GyeonggiAreaType -> this.title
    is IncheonAreaType -> this.title
    else -> ""
}

fun String.toAreaType(): Any? =
    when {
        this.toSeoulAreaTitle().isNotEmpty() -> this.toSeoulAreaType()
        this.toGyeonggiAreaTitle().isNotEmpty() -> this.toGyeonggiAreaType()
        this.toIncheonAreaTitle().isNotEmpty() -> this.toIncheonAreaType()
        else -> this
    }
