package org.sopt.dateroad.data.mapper.todomain

import org.sopt.dateroad.data.dataremote.model.response.ResponsePointHistoryDto
import org.sopt.dateroad.domain.model.PointHistory

fun ResponsePointHistoryDto.toDomain(): PointHistory = PointHistory(
    gained = this.gained.map { responsePointDto -> responsePointDto.toDomain() },
    used = this.used.map { responsePointDto -> responsePointDto.toDomain() }
)