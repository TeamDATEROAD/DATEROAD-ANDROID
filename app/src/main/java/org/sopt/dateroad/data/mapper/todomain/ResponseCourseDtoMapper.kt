package org.sopt.dateroad.data.mapper.todomain

import org.sopt.dateroad.data.dataremote.model.response.ResponseCourseDto
import org.sopt.dateroad.domain.model.Course
import org.sopt.dateroad.domain.type.MoneyTagType.Companion.toCostTagTitle

fun ResponseCourseDto.toDomain(): Course = Course(
    courseId = this.courseId,
    thumbnail = this.thumbnail,
    title = this.title,
    city = this.city,
    cost = this.cost.toCostTagTitle(),
    duration = this.duration.toDuration(),
    like = this.like.toLike()
)
