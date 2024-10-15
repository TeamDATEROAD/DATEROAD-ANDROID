package org.sopt.teamdateroad.data.mapper.todata

import org.sopt.teamdateroad.data.dataremote.model.request.RequestCourseDto
import org.sopt.teamdateroad.data.dataremote.model.request.RequestTimelineDto
import org.sopt.teamdateroad.data.mapper.toEntity.toAreaTitle
import org.sopt.teamdateroad.domain.model.Enroll

fun Enroll.toTimelineData(): RequestTimelineDto = RequestTimelineDto(
    title = this.title,
    date = this.date,
    startAt = this.startAt,
    tags = this.tags.map { tag -> tag.toData() },
    country = this.country?.title.orEmpty(),
    city = this.city.toAreaTitle(),
    places = places.mapIndexed { index, place -> place.toData(sequence = index + 1) }
)

fun Enroll.toCourseData(): RequestCourseDto = RequestCourseDto(
    title = this.title,
    date = this.date,
    startAt = this.startAt,
    country = country?.title.orEmpty(),
    city = this.city.toAreaTitle(),
    description = this.description,
    cost = this.cost.toInt()
)
