package org.sopt.teamdateroad.data.mapper.toEntity

import org.sopt.teamdateroad.domain.type.GyeonggiAreaType
import org.sopt.teamdateroad.domain.type.GyeonggiAreaType.Companion.fromTitleToGyeonggiAreaType
import org.sopt.teamdateroad.domain.type.IncheonAreaType
import org.sopt.teamdateroad.domain.type.IncheonAreaType.Companion.fromTitleToIncheonAreaType
import org.sopt.teamdateroad.domain.type.SeoulAreaType
import org.sopt.teamdateroad.domain.type.SeoulAreaType.Companion.fromTitleToSeoulAreaType

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
