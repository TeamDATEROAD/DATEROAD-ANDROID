package org.sopt.dateroad.data.mapper.toEntity

import java.text.NumberFormat
import java.util.Locale
import org.sopt.dateroad.data.dataremote.util.Cost
import org.sopt.dateroad.presentation.util.TotalCostZero.ZEROCOST

fun Int.toCost(): String = if (this == 0) {
    ZEROCOST
} else {
    "${NumberFormat.getNumberInstance(Locale.KOREA).format(this)}${Cost.COST}"
}
