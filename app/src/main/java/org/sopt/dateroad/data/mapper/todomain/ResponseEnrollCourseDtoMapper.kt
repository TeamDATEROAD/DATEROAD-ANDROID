package org.sopt.dateroad.data.mapper.todomain

import org.sopt.dateroad.data.dataremote.model.response.ResponseEnrollCourseDto
import org.sopt.dateroad.domain.model.EnrollCourseResult

fun ResponseEnrollCourseDto.toDomain(): EnrollCourseResult = EnrollCourseResult(
    userPoint = this.userPoint,
    userCourseCount = this.userCourseCount
)
