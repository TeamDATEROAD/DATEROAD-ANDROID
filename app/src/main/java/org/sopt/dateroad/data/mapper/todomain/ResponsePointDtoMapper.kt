package org.sopt.dateroad.data.mapper.todomain

import org.sopt.dateroad.data.dataremote.model.response.ResponsePointDto
import org.sopt.dateroad.data.mapper.toEntity.toGainedPoint
import org.sopt.dateroad.data.mapper.toEntity.toUsedPoint
import org.sopt.dateroad.domain.model.Point

fun ResponsePointDto.toGainedPointDomain() = Point(
    point = this.point.toGainedPoint(),
    description = this.description,
    createdAt = this.createdAt
)

fun ResponsePointDto.toUsedPointDomain() = Point(
    point = this.point.toUsedPoint(),
    description = this.description,
    createdAt = this.createdAt
)
