package org.sopt.dateroad.data.mapper.todomain

import org.sopt.dateroad.data.dataremote.model.response.ResponsePointsDto
import org.sopt.dateroad.domain.model.Point

fun ResponsePointsDto.toDomain(): List<Point> = this.points.map { responsePointDto ->
    responsePointDto.toDomain()
}
