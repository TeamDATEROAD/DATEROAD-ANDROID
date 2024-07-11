package org.sopt.dateroad.presentation.type

import androidx.annotation.ColorRes
import androidx.compose.ui.graphics.Color
import org.sopt.dateroad.ui.theme.defaultDateRoadColors

enum class DateType(
    @ColorRes val backgroundColor: Color,
    @ColorRes val lineColor: Color,
    val tagType: TagType
) {
    PINK(
        backgroundColor = defaultDateRoadColors.pink200,
        lineColor = defaultDateRoadColors.pink300,
        tagType = TagType.TIMELINE_DATE_PINK
    ),
    PURPLE(
        backgroundColor = defaultDateRoadColors.purple200,
        lineColor = defaultDateRoadColors.purple300,
        tagType = TagType.TIMELINE_DATE_PURPLE
    ),
    LIME(
        backgroundColor = defaultDateRoadColors.lime,
        lineColor = defaultDateRoadColors.lime300,
        tagType = TagType.TIMELINE_DATE_LIME
    )
}
