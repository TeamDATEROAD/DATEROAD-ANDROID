package org.sopt.dateroad.data.mapper.todomain

import org.sopt.dateroad.domain.util.Month

fun String.toEnglishMonth(): String {
    return when (this) {
        "01" -> Month.JANUARY
        "02" -> Month.FEBRUARY
        "03" -> Month.MARCH
        "04" -> Month.APRIL
        "05" -> Month.MAY
        "06" -> Month.JUNE
        "07" -> Month.JULY
        "08" -> Month.AUGUST
        "09" -> Month.SEPTEMBER
        "10" -> Month.OCTOBER
        "11" -> Month.NOVEMBER
        "12" -> Month.DECEMBER
        else -> this
    }
}
