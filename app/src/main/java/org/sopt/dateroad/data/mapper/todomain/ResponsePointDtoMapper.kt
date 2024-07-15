package org.sopt.dateroad.data.mapper.todomain

import org.sopt.dateroad.data.dataremote.model.response.ResponsePointDto
import org.sopt.dateroad.domain.model.Point

fun ResponsePointDto.toDomain() = Point(
    point = this.point.toPoint(),
    description = this.description,
    createAt = this.createAt
)