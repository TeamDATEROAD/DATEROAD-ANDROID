package org.sopt.dateroad.data.mapper.todomain

import org.sopt.dateroad.data.dataremote.model.response.ResponseUserPointDto
import org.sopt.dateroad.data.mapper.toEntity.toPoint
import org.sopt.dateroad.domain.model.UserPoint

fun ResponseUserPointDto.toDomain(): UserPoint = UserPoint(
    name = this.name,
    point = this.point.toPoint(),
    imageUrl = this.imageUrl
)
