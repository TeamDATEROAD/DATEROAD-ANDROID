package org.sopt.teamdateroad.data.mapper.toEntity

import org.sopt.teamdateroad.domain.model.Enroll
import org.sopt.teamdateroad.domain.model.TimelineDetail

fun TimelineDetail.toEnroll() = Enroll(
    title = this.title,
    startAt = this.startAt,
    country = this.city.toRegionType(),
    city = this.city.fromTitleToAreaType(),
    tags = this.tags,
    date = this.date.fromDateToEnrollDate(),
    places = this.places
)
