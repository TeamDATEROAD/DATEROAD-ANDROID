package org.sopt.dateroad.data.mapper.todomain

import org.sopt.dateroad.data.dataremote.model.response.ResponseNearestTimelineDto
import org.sopt.dateroad.data.mapper.toEntity.toDDayString
import org.sopt.dateroad.data.mapper.toEntity.toFormattedDate
import org.sopt.dateroad.data.mapper.toEntity.toStartAtString
import org.sopt.dateroad.domain.model.MainTimeline

fun ResponseNearestTimelineDto.toDomain(): MainTimeline = MainTimeline(
    timelineId = this.timelineId,
    dDay = this.dDay.toDDayString(),
    dateName = this.dateName,
    date = toFormattedDate(),
    startAt = this.startAt.toStartAtString()
)
