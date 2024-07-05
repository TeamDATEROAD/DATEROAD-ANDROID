package org.sopt.dateroad.presentation.type

import androidx.annotation.StringRes
import org.sopt.dateroad.R
import org.sopt.dateroad.presentation.util.PointHistoryTab.EARN_HISTORY_POSITION
import org.sopt.dateroad.presentation.util.PointHistoryTab.USAGE_HISTORY_POSITION

enum class PointHistoryTabType(
    val position: Int,
    @StringRes val titleRes: Int
) {
    EARN_HISTORY(
        position = EARN_HISTORY_POSITION,
        titleRes = R.string.point_history_tab_earn_history
    ),
    USAGE_HISTORY(
        position = USAGE_HISTORY_POSITION,
        titleRes = R.string.point_history_tab_usage_history
    )
}
