package org.sopt.dateroad.data.mapper.todomain

import org.sopt.dateroad.data.dataremote.model.response.ResponseTimelineDto
import org.sopt.dateroad.data.mapper.toEntity.toAreaTitle
import org.sopt.dateroad.data.mapper.toEntity.toAreaType
import org.sopt.dateroad.data.mapper.toEntity.toBasicDates
import org.sopt.dateroad.data.mapper.toEntity.toDDayString
import org.sopt.dateroad.data.mapper.toEntity.toFormattedDate
import org.sopt.dateroad.domain.model.Timeline

fun ResponseTimelineDto.toFutureTimelineDomain(): Timeline = Timeline(
    timelineId = this.timelineId,
    dDay = this.dDay.toDDayString(),
    title = this.title,
    date = this.date.toFormattedDate(),
    city = this.city,
    tags = this.tags.map { it.toDomain() }
)

fun ResponseTimelineDto.toPastTimelineDomain(): Timeline = Timeline(
    timelineId = this.timelineId,
    dDay = this.dDay.toDDayString(),
    title = this.title,
    date = this.date.toBasicDates(),
    city = this.city,
    tags = this.tags.map { it.toDomain() }
)
