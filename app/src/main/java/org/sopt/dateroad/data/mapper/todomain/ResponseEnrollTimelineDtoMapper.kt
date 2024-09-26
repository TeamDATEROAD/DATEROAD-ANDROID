package org.sopt.dateroad.data.mapper.todomain

import org.sopt.dateroad.data.dataremote.model.response.ResponseEnrollTimelineDto
import org.sopt.dateroad.domain.model.EnrollTimelineResult

fun ResponseEnrollTimelineDto.toDomain(): EnrollTimelineResult = EnrollTimelineResult(
    dateScheduleNum = this.dateScheduleNum
)
