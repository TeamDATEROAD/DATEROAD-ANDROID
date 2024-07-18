package org.sopt.dateroad.data.mapper.toEntity

import org.sopt.dateroad.domain.model.CourseDetail
import org.sopt.dateroad.domain.model.Enroll

fun CourseDetail.toEnroll() = Enroll(
    title = this.title,
    date = this.date.fromCourseDetailToEnrollDate(),
    startAt = this.startAt,
    tags = this.tags,
    country = null,
    city = this.city,
    places = this.places
)