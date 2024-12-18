package org.sopt.teamdateroad.data.mapper.toEntity

import java.text.NumberFormat
import java.util.Locale
import org.sopt.teamdateroad.data.dataremote.util.Cost
import org.sopt.teamdateroad.data.dataremote.util.TotalCostZero.ZERO_COST

fun Int.toCost(): String = if (this == 0) {
    ZERO_COST
} else {
    NumberFormat.getNumberInstance(Locale.KOREA).format(this) + Cost.COST
}
