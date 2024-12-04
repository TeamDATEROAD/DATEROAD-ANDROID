package org.sopt.teamdateroad.data.mapper.todomain

import org.sopt.teamdateroad.data.dataremote.model.response.ResponseTimelineDto
import org.sopt.teamdateroad.data.mapper.toEntity.toBasicDates
import org.sopt.teamdateroad.data.mapper.toEntity.toDDayString
import org.sopt.teamdateroad.data.mapper.toEntity.toFormattedDate
import org.sopt.teamdateroad.domain.model.Timeline

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
