package org.sopt.teamdateroad.data.mapper.todomain

import org.sopt.teamdateroad.data.dataremote.model.response.ResponseProfileDto
import org.sopt.teamdateroad.data.mapper.toEntity.toPoint
import org.sopt.teamdateroad.domain.model.Profile

fun ResponseProfileDto.toDomain(): Profile = Profile(
    name = this.name,
    tag = this.tags,
    point = this.point.toPoint(),
    imageUrl = this.imageUrl
)
