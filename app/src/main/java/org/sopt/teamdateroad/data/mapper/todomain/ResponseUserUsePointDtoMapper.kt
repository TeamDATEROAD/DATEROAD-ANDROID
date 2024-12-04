package org.sopt.teamdateroad.data.mapper.todomain

import org.sopt.teamdateroad.data.dataremote.model.response.ResponseUserUsePointDto
import org.sopt.teamdateroad.domain.model.PointUseResult

fun ResponseUserUsePointDto.toDomain(): PointUseResult = PointUseResult(
    userPoint = this.userPoint,
    userFreeRemained = this.userFreeRemained,
    userPurchaseCount = this.userPurchaseCount
)
