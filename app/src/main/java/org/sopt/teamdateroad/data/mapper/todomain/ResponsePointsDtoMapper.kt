package org.sopt.teamdateroad.data.mapper.todomain

import org.sopt.teamdateroad.data.dataremote.model.response.ResponsePointsDto
import org.sopt.teamdateroad.domain.model.Point

fun ResponsePointsDto.toGainedPointDomain(): List<Point> = this.points.map { responsePointDto ->
    responsePointDto.toGainedPointDomain()
}

fun ResponsePointsDto.toUsedPointDomain(): List<Point> = this.points.map { responsePointDto ->
    responsePointDto.toUsedPointDomain()
}
