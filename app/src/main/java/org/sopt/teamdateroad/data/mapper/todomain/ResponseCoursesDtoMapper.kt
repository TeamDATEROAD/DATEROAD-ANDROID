package org.sopt.teamdateroad.data.mapper.todomain

import org.sopt.teamdateroad.data.dataremote.model.response.ResponseCoursesDto
import org.sopt.teamdateroad.domain.model.Course

fun ResponseCoursesDto.toDomain(): List<Course> = this.courses.map { course -> course.toDomain() }
