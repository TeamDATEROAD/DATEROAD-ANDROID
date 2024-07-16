package org.sopt.dateroad.data.mapper.todomain

import org.sopt.dateroad.data.dataremote.model.response.ResponseCourseDetailDto
import org.sopt.dateroad.domain.model.CourseDetail

fun ResponseCourseDetailDto.toDomain(): CourseDetail = CourseDetail(
    courseId = this.courseId,
    images = this.images.sortedBy { responseImageDto -> responseImageDto.sequence }.map { responseImageDto -> responseImageDto.imageUrl },
    like = this.like.toLike(),
    totalTime = this.totalTime.toDuration(),
    date = this.date.toCourseDetailDate(),
    city = this.city,
    title = this.title,
    description = this.description,
    places = this.places.sortedBy { responsePlaceDto -> responsePlaceDto.sequence }.map { responsePlaceDto -> responsePlaceDto.toDomain() },
    totalCost = totalCost.toCost(),
    tags = this.tags.map { responseTagDto -> responseTagDto.tag },
    isAccess = this.isAccess,
    free = this.free,
    totalPoint = this.totalPoint,
    isCourseMine = this.isCourseMine,
    isUserLiked = this.isUserLiked

)