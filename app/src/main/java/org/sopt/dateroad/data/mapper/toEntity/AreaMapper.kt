package org.sopt.dateroad.data.mapper.toEntity

import org.sopt.dateroad.domain.type.GyeonggiAreaType
import org.sopt.dateroad.domain.type.GyeonggiAreaType.Companion.fromTitleToGyeonggiAreaType
import org.sopt.dateroad.domain.type.IncheonAreaType
import org.sopt.dateroad.domain.type.IncheonAreaType.Companion.fromTitleToIncheonAreaType
import org.sopt.dateroad.domain.type.SeoulAreaType
import org.sopt.dateroad.domain.type.SeoulAreaType.Companion.fromTitleToSeoulAreaType

fun Any?.toAreaTitle(): String = when (this) {
    is SeoulAreaType -> this.title
    is GyeonggiAreaType -> this.title
    is IncheonAreaType -> this.title
    else -> ""
}

fun String.fromTitleToAreaType(): Any? =
    when {
        this.fromTitleToSeoulAreaType() != null -> this.fromTitleToSeoulAreaType()
        this.fromTitleToGyeonggiAreaType() != null -> this.fromTitleToGyeonggiAreaType()
        this.fromTitleToIncheonAreaType() != null -> this.fromTitleToIncheonAreaType()
        else -> this
    }
