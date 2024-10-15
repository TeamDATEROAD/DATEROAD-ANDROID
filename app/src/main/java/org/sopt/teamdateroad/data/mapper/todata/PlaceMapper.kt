package org.sopt.teamdateroad.data.mapper.todata

import org.sopt.teamdateroad.data.dataremote.model.request.RequestPlaceDto
import org.sopt.teamdateroad.data.dataremote.util.Duration.DURATION
import org.sopt.teamdateroad.domain.model.Place

fun Place.toData(sequence: Int): RequestPlaceDto = RequestPlaceDto(
    sequence = sequence,
    title = this.title,
    duration = duration.substringBefore(DURATION).toFloat()
)
