package org.sopt.teamdateroad.data.mapper.todomain

import org.sopt.teamdateroad.data.dataremote.model.response.ResponseEnrollTimelineDto
import org.sopt.teamdateroad.domain.model.EnrollTimelineResult

fun ResponseEnrollTimelineDto.toDomain(): EnrollTimelineResult = EnrollTimelineResult(
    dateScheduleNum = this.dateScheduleNum
)
