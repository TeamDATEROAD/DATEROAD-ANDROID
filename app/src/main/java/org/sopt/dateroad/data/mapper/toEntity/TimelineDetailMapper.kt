package org.sopt.dateroad.data.mapper.toEntity

import org.sopt.dateroad.domain.model.Enroll
import org.sopt.dateroad.domain.model.TimelineDetail

fun TimelineDetail.toEnroll() = Enroll(
    title = this.title,
    startAt = this.startAt,
    country = this.city.toRegionType(),
    city = this.city.fromTitleToAreaType(),
    tags = this.tags,
    date = this.date.fromDateToEnrollDate(),
    places = this.places
)
