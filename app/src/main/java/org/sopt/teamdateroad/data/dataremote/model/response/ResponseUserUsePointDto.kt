package org.sopt.teamdateroad.data.dataremote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseUserUsePointDto(
    @SerialName("userPoint")
    val userPoint: Int,
    @SerialName("userFreeRemained")
    val userFreeRemained: Int,
    @SerialName("userPurchaseCount")
    val userPurchaseCount: Long
)
