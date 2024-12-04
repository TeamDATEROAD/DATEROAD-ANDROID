package org.sopt.teamdateroad.data.mapper.toEntity

import org.sopt.teamdateroad.domain.model.CourseDetail
import org.sopt.teamdateroad.domain.model.Enroll

fun CourseDetail.toEnroll() = Enroll(
    title = this.title,
    startAt = this.startAt,
    tags = this.tags,
    country = this.city.toRegionType(),
    city = this.city.fromTitleToAreaType(),
    places = this.places
)
