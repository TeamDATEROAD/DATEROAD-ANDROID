package org.sopt.dateroad.data.mapper.todomain

import org.sopt.dateroad.data.dataremote.model.response.ResponsePlaceDto
import org.sopt.dateroad.domain.model.Place

fun ResponsePlaceDto.toDomain() = Place(
    name = this.name,
    duration = this.duration.toDuration(),
    sequence = this.sequence
)
