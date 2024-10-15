package org.sopt.teamdateroad.data.mapper.todomain

import org.sopt.teamdateroad.data.dataremote.model.response.ResponseNearestTimelineDto
import org.sopt.teamdateroad.data.mapper.toEntity.toDDayString
import org.sopt.teamdateroad.data.mapper.toEntity.toFormattedDate
import org.sopt.teamdateroad.data.mapper.toEntity.toStartAtString
import org.sopt.teamdateroad.domain.model.NearestTimeline

fun ResponseNearestTimelineDto.toDomain(): NearestTimeline = NearestTimeline(
    timelineId = this.timelineId,
    dDay = this.dDay.toDDayString(),
    dateName = this.dateName,
    date = toFormattedDate(),
    startAt = this.startAt.toStartAtString()
)
