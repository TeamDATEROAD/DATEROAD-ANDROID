package org.sopt.teamdateroad.data.mapper.todomain

import org.sopt.teamdateroad.data.dataremote.model.response.ResponseEnrollCourseDto
import org.sopt.teamdateroad.domain.model.EnrollCourseResult

fun ResponseEnrollCourseDto.toDomain(): EnrollCourseResult = EnrollCourseResult(
    userPoint = this.userPoint,
    userCourseCount = this.userCourseCount
)
