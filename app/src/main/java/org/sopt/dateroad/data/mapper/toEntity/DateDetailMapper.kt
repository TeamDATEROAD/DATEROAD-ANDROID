package org.sopt.dateroad.data.mapper.toEntity

import org.sopt.dateroad.domain.model.DateDetail
import org.sopt.dateroad.domain.model.Enroll

fun DateDetail.toEnroll() = Enroll(
    title = this.title,
    startAt = this.startAt,
    country = this.city.toRegionType(),
    city = this.city.fromTitleToAreaType(),
    tags = this.tags,
    date = this.date.fromDateToEnrollDate(),
    places = this.places
)