package org.sopt.teamdateroad.data.mapper.todomain

import org.sopt.teamdateroad.data.dataremote.model.response.ResponseUserPointDto
import org.sopt.teamdateroad.data.mapper.toEntity.toPoint
import org.sopt.teamdateroad.domain.model.UserPoint

fun ResponseUserPointDto.toDomain(): UserPoint = UserPoint(
    name = this.name,
    point = this.point.toPoint(),
    imageUrl = this.image
)
