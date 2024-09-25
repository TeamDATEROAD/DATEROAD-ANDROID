package org.sopt.dateroad.data.mapper.todomain

import org.sopt.dateroad.data.dataremote.model.response.ResponsePointsDto
import org.sopt.dateroad.domain.model.Point

fun ResponsePointsDto.toGainedPointDomain(): List<Point> = this.points.map { responsePointDto ->
    responsePointDto.toGainedPointDomain()
}

fun ResponsePointsDto.toUsedPointDomain(): List<Point> = this.points.map { responsePointDto ->
    responsePointDto.toUsedPointDomain()
}
