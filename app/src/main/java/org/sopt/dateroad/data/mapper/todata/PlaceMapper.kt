package org.sopt.dateroad.data.mapper.todata

import org.sopt.dateroad.data.dataremote.model.request.RequestPlaceDto
import org.sopt.dateroad.domain.model.Place

fun Place.toData(sequence: Int): RequestPlaceDto = RequestPlaceDto(
    sequence = sequence,
    title = this.title,
    duration = duration.substringBefore(" ").toFloat()
)
