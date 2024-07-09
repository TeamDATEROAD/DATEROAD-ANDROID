package org.sopt.dateroad.presentation.type

import androidx.annotation.StringRes
import org.sopt.dateroad.R
import org.sopt.dateroad.presentation.util.PointHistoryTab.GAINED_HISTORY_POSITION
import org.sopt.dateroad.presentation.util.PointHistoryTab.USED_HISTORY_POSITION

enum class PointHistoryTabType(
    val position: Int,
    @StringRes val titleRes: Int
) {
    GAINED_HISTORY(
        position = GAINED_HISTORY_POSITION,
        titleRes = R.string.point_history_tab_gained_history
    ),
    USED_HISTORY(
        position = USED_HISTORY_POSITION,
        titleRes = R.string.point_history_tab_used_history
    )
}
