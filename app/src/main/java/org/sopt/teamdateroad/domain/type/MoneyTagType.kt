package org.sopt.teamdateroad.domain.type

import org.sopt.teamdateroad.domain.util.Cost

enum class MoneyTagType(
    val title: String,
    val threshold: Int,
    val costParameter: Int
) {
    LESS_THAN_30000(
        title = Cost.LESS_THAN_30000_TITLE,
        threshold = 30000,
        costParameter = 3
    ),
    LESS_THAN_50000(
        title = Cost.LESS_THAN_50000_TITLE,
        threshold = 50000,
        costParameter = 5
    ),
    LESS_THAN_100000(
        title = Cost.LESS_THAN_100000_TITLE,
        threshold = 100000,
        costParameter = 10
    ),
    EXCESS_100000(
        title = Cost.EXCESS_100000_TITLE,
        threshold = Int.MAX_VALUE,
        costParameter = 11
    );

    companion object {
        fun Int.toCostTagTitle(): String = when {
            this > LESS_THAN_100000.threshold -> EXCESS_100000.title
            this > LESS_THAN_50000.threshold -> LESS_THAN_100000.title
            this > LESS_THAN_30000.threshold -> LESS_THAN_50000.title
            else -> LESS_THAN_30000.title
        }
    }
}
