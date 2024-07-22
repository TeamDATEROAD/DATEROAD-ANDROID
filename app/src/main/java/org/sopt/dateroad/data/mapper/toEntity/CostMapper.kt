package org.sopt.dateroad.data.mapper.toEntity

import java.text.NumberFormat
import java.util.Locale
import org.sopt.dateroad.data.dataremote.util.Cost

fun Int.toCost(): String = "${NumberFormat.getNumberInstance(Locale.KOREA).format(this)}${Cost.COST}"
fun Int.toCostTag(): String {
    return when {
        this > 100000 -> "10만원 초과"
        this > 50000 -> "10만원 이하"
        this > 30000 -> "5만원 이하"
        else -> "3만원 이하"
    }
}
