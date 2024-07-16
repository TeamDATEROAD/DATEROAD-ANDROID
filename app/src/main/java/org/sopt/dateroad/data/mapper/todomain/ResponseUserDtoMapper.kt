package org.sopt.dateroad.data.mapper.todomain

import org.sopt.dateroad.data.dataremote.model.response.ResponseUserDto
import org.sopt.dateroad.domain.model.Profile

fun ResponseUserDto.toDomain(): Profile = Profile(
    name = this.name,
    tag = this.tags,
    point = this.point,
    imageUrl = this.imageUrl
)
