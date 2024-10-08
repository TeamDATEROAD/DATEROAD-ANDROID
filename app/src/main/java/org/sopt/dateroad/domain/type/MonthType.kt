package org.sopt.dateroad.domain.type

import org.sopt.dateroad.data.dataremote.util.Month

enum class MonthType(val title: String) {
    JANUARY(title = Month.JANUARY),
    FEBRUARY(title = Month.FEBRUARY),
    MARCH(title = Month.MARCH),
    APRIL(title = Month.APRIL),
    MAY(title = Month.MAY),
    JUNE(title = Month.JUNE),
    JULY(title = Month.JULY),
    AUGUST(title = Month.AUGUST),
    SEPTEMBER(title = Month.SEPTEMBER),
    OCTOBER(title = Month.OCTOBER),
    NOVEMBER(title = Month.NOVEMBER),
    DECEMBER(title = Month.DECEMBER);

    companion object {
        fun fromNumber(month: Int): String = entries.find { it.ordinal + 1 == month }?.title ?: month.toString()
    }
}
