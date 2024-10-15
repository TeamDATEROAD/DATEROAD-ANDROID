package org.sopt.teamdateroad.data.mapper.todomain

import org.sopt.teamdateroad.data.dataremote.model.response.ResponseCourseDetailDto
import org.sopt.teamdateroad.data.mapper.toEntity.toCost
import org.sopt.teamdateroad.data.mapper.toEntity.toCourseDetailDate
import org.sopt.teamdateroad.data.mapper.toEntity.toDuration
import org.sopt.teamdateroad.data.mapper.toEntity.toStartAtString
import org.sopt.teamdateroad.domain.model.CourseDetail
import org.sopt.teamdateroad.domain.type.MoneyTagType.Companion.toCostTagTitle

fun ResponseCourseDetailDto.toDomain(): CourseDetail = CourseDetail(
    courseId = this.courseId,
    images = this.images.sortedBy { responseImageDto -> responseImageDto.sequence }.map { responseImageDto -> responseImageDto.imageUrl },
    like = this.like,
    totalTime = this.totalTime.toDuration(),
    date = this.date.toCourseDetailDate(),
    city = this.city,
    title = this.title,
    description = this.description,
    places = this.places.sortedBy { responsePlaceDto -> responsePlaceDto.sequence }.map { responsePlaceDto -> responsePlaceDto.toDomain() },
    totalCostTag = totalCost.toCostTagTitle(),
    totalCost = totalCost.toCost(),
    tags = this.tags.map { responseTagDto -> responseTagDto.tag },
    isAccess = this.isAccess,
    free = this.free,
    totalPoint = this.totalPoint,
    isCourseMine = this.isCourseMine,
    isUserLiked = this.isUserLiked,
    startAt = this.startAt.toStartAtString()
)
