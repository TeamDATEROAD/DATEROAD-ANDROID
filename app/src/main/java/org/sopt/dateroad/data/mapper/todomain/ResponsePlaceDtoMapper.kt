package org.sopt.dateroad.data.mapper.todomain

import org.sopt.dateroad.data.dataremote.model.response.ResponsePlaceDto
import org.sopt.dateroad.domain.model.Place

fun ResponsePlaceDto.toDomain() = Place(
    title = this.title,
    duration = this.duration.toDuration()
)
