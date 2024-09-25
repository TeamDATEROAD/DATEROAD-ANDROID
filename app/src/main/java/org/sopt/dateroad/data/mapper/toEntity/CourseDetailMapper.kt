package org.sopt.dateroad.data.mapper.toEntity

import org.sopt.dateroad.domain.model.CourseDetail
import org.sopt.dateroad.domain.model.Enroll

fun CourseDetail.toEnroll() = Enroll(
    title = this.title,
    startAt = this.startAt,
    tags = this.tags,
    country = this.city.toRegionType(),
    city = this.city.fromTitleToAreaType(),
    places = this.places
)
