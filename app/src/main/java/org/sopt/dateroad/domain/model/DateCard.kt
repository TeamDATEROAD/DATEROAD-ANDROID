package org.sopt.dateroad.domain.model

import org.sopt.dateroad.presentation.type.DateTagType

data class DateCard(
    val dateId: Long,
    val title: String,
    val year: String,
    val month: String,
    val day: String,
    val city: String,
    val tags: List<TimelineTag>,
    val dDay: String
)

data class TimelineTag(
    val timelineTag: DateTagType
)
