package org.sopt.dateroad.presentation.type

import androidx.annotation.ColorRes
import androidx.compose.ui.graphics.Color
import org.sopt.dateroad.ui.theme.defaultDateRoadColors

enum class DateType(
    @ColorRes val backgroundColor: Color,
    @ColorRes val lineColor: Color,
    @ColorRes val tagColor: Color
) {
    PINK(
        backgroundColor = defaultDateRoadColors.pink200,
        lineColor = defaultDateRoadColors.pink300,
        tagColor = defaultDateRoadColors.pink100
    ),
    PURPLE(
        backgroundColor = defaultDateRoadColors.purple200,
        lineColor = defaultDateRoadColors.purple300,
        tagColor = defaultDateRoadColors.purple100
    ),
    LIME(
        backgroundColor = defaultDateRoadColors.lime,
        lineColor = defaultDateRoadColors.lime300,
        tagColor = defaultDateRoadColors.lime100
    )
}
