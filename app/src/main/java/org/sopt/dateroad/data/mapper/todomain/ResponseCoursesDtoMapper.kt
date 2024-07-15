package org.sopt.dateroad.data.mapper.todomain

import org.sopt.dateroad.data.dataremote.model.response.ResponseCoursesDto
import org.sopt.dateroad.domain.model.Course

fun ResponseCoursesDto.toDomain(): List<Course> = this.courses.map { course -> course.toDomain() }
