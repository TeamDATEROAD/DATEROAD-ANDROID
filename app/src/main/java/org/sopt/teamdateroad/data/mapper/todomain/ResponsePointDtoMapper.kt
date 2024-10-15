package org.sopt.teamdateroad.data.mapper.todomain

import org.sopt.teamdateroad.data.dataremote.model.response.ResponsePointDto
import org.sopt.teamdateroad.data.mapper.toEntity.toGainedPoint
import org.sopt.teamdateroad.data.mapper.toEntity.toUsedPoint
import org.sopt.teamdateroad.domain.model.Point

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
