package org.sopt.dateroad.data.mapper.todata

import org.sopt.dateroad.data.dataremote.model.request.RequestCourseDto
import org.sopt.dateroad.data.dataremote.model.request.RequestTimelineDto
import org.sopt.dateroad.data.mapper.toEntity.toAreaName
import org.sopt.dateroad.domain.model.Enroll

fun Enroll.toTimelineData(): RequestTimelineDto = RequestTimelineDto(
    title = this.title,
    date = this.date,
    startAt = this.startAt,
    tags = this.tags.map { tag -> tag.toData() },
    country = this.country?.name.orEmpty(),
    city = this.city.toAreaName(),
    places = places.mapIndexed { index, place -> place.toData(sequence = index) }
)

fun Enroll.toCourseData(): RequestCourseDto = RequestCourseDto(
    title = this.title,
    date = this.date,
    startAt = this.startAt,
    country = country?.name.orEmpty(),
    city = this.city.toAreaName(),
    description = this.description,
    cost = this.cost.toInt()
)
