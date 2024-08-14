package org.sopt.dateroad.presentation.type

import androidx.annotation.ColorRes
import androidx.compose.ui.graphics.Color
import org.sopt.dateroad.ui.theme.defaultDateRoadColors

enum class TimelineType(
    val index: Int,
    @ColorRes val backgroundColor: Color,
    @ColorRes val lineColor: Color,
    val tagType: TagType
) {
    PINK(
        index = 0,
        backgroundColor = defaultDateRoadColors.pink200,
        lineColor = defaultDateRoadColors.pink300,
        tagType = TagType.TIMELINE_DATE_PINK
    ),
    PURPLE(
        index = 1,
        backgroundColor = defaultDateRoadColors.purple200,
        lineColor = defaultDateRoadColors.purple300,
        tagType = TagType.TIMELINE_DATE_PURPLE
    ),
    LIME(
        index = 2,
        backgroundColor = defaultDateRoadColors.lime200,
        lineColor = defaultDateRoadColors.lime300,
        tagType = TagType.TIMELINE_DATE_LIME
    );

    companion object {
        fun getTimelineTypeByIndex(index: Int): TimelineType = entries.first { it.index == index % entries.size }
    }
}
