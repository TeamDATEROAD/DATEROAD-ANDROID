package org.sopt.teamdateroad.data.mapper.todomain

import org.sopt.teamdateroad.data.dataremote.model.response.ResponsePointHistoryDto
import org.sopt.teamdateroad.domain.model.PointHistory

fun ResponsePointHistoryDto.toDomain(): PointHistory = PointHistory(
    gained = this.gained.toGainedPointDomain(),
    used = this.used.toUsedPointDomain()
)
