package org.sopt.dateroad.data.mapper.todomain

import org.sopt.dateroad.data.dataremote.model.response.ResponseProfileDto
import org.sopt.dateroad.data.dataremote.util.Point.POINT
import org.sopt.dateroad.domain.model.Profile

fun ResponseProfileDto.toDomain(): Profile = Profile(
    name = this.name,
    tag = this.tags,
    point = this.point.toPoint(),
    imageUrl = this.imageUrl
)
