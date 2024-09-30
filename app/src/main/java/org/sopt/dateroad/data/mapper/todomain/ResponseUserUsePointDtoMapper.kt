package org.sopt.dateroad.data.mapper.todomain

import org.sopt.dateroad.data.dataremote.model.response.ResponseUserUsePointDto
import org.sopt.dateroad.domain.model.PointUseResult

fun ResponseUserUsePointDto.toDomain(): PointUseResult = PointUseResult(
    userPoint = this.userPoint,
    userFreeRemained = this.userFreeRemained,
    userPurchaseCount = this.userPurchaseCount
)
