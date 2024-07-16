package org.sopt.dateroad.data.mapper.todomain

import org.sopt.dateroad.data.dataremote.model.response.ResponseUserProfileMainDto
import org.sopt.dateroad.domain.model.UserPoint

fun ResponseUserProfileMainDto.toDomain(): UserPoint = UserPoint(
    name = this.name,
    point = this.point.toPoint(),
    imageUrl = this.image
)
