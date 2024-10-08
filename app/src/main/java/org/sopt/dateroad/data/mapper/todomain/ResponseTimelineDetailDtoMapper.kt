package org.sopt.dateroad.data.mapper.todomain

import org.sopt.dateroad.data.dataremote.model.response.ResponseTimelineDetailDto
import org.sopt.dateroad.data.mapper.toEntity.toBasicDates
import org.sopt.dateroad.data.mapper.toEntity.toDDayString
import org.sopt.dateroad.data.mapper.toEntity.toStartAtString
import org.sopt.dateroad.domain.model.TimelineDetail

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
