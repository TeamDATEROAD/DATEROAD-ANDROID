package org.sopt.teamdateroad.data.mapper.todomain

import org.sopt.teamdateroad.data.dataremote.model.response.ResponseCourseDto
import org.sopt.teamdateroad.data.mapper.toEntity.toDuration
import org.sopt.teamdateroad.data.mapper.toEntity.toLike
import org.sopt.teamdateroad.domain.model.Course
import org.sopt.teamdateroad.domain.type.MoneyTagType.Companion.toCostTagTitle

fun ResponseCourseDto.toDomain(): Course = Course(
    courseId = this.courseId,
    thumbnail = this.thumbnail,
    title = this.title,
    city = this.city,
    cost = this.cost.toCostTagTitle(),
    duration = this.duration.toDuration(),
    like = this.like.toLike()
)
