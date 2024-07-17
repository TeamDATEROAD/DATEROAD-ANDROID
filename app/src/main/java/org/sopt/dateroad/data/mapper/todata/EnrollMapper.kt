package org.sopt.dateroad.data.mapper.todata

import org.sopt.dateroad.data.dataremote.model.request.RequestDateDto
import org.sopt.dateroad.domain.model.Enroll
import org.sopt.dateroad.domain.util.toAreaTitle

fun Enroll.toDateData(): RequestDateDto = RequestDateDto(
    title = this.title,
    date = this.date,
    startAt = this.startAt,
    tags = this.tags.map { tag -> tag.toData() },
    country = this.country?.name.orEmpty(),
    city = this.city.toAreaTitle(),
    places = places.mapIndexed { index, place -> place.toData(sequence = index) }
)
