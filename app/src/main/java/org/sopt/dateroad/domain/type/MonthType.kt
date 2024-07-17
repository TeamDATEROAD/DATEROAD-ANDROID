package org.sopt.dateroad.domain.type

enum class MonthType(val title: String) {
    JANUARY("January"),
    FEBRUARY("February"),
    MARCH("March"),
    APRIL("April"),
    MAY("May"),
    JUNE("June"),
    JULY("July"),
    AUGUST("August"),
    SEPTEMBER("September"),
    OCTOBER("October"),
    NOVEMBER("November"),
    DECEMBER("December");

    companion object {
        fun fromNumber(month: Int): String = entries.find { it.ordinal + 1 == month }?.title ?: month.toString()
    }
}
