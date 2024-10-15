package org.sopt.teamdateroad.data.mapper.todomain

import org.sopt.teamdateroad.data.dataremote.model.response.ResponsePlaceDto
import org.sopt.teamdateroad.data.mapper.toEntity.toDuration
import org.sopt.teamdateroad.domain.model.Place

fun ResponsePlaceDto.toDomain() = Place(
    title = this.title,
    duration = this.duration.toDuration()
)
