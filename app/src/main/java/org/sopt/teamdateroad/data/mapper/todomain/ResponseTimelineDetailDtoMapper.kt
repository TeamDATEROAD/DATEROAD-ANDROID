package org.sopt.teamdateroad.data.mapper.todomain

import org.sopt.teamdateroad.data.dataremote.model.response.ResponseTimelineDetailDto
import org.sopt.teamdateroad.data.mapper.toEntity.toBasicDates
import org.sopt.teamdateroad.data.mapper.toEntity.toDDayString
import org.sopt.teamdateroad.data.mapper.toEntity.toStartAtString
import org.sopt.teamdateroad.domain.model.TimelineDetail

fun ResponseTimelineDetailDto.toDomain(): TimelineDetail = TimelineDetail(
    timelineId = this.timelineId,
    title = this.title,
    startAt = this.startAt.toStartAtString(),
    city = this.city,
    tags = this.tags.map { responseTagDto -> responseTagDto.tag },
    date = this.date.toBasicDates(),
    places = this.places.sortedBy { responsePlaceDto -> responsePlaceDto.sequence }.map { it.toDomain() },
    dDay = this.dDay.toDDayString()
)
