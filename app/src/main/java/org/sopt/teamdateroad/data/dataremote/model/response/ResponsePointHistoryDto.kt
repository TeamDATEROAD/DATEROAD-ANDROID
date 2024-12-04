package org.sopt.teamdateroad.data.dataremote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponsePointHistoryDto(
    @SerialName("gained")
    val gained: ResponsePointsDto,
    @SerialName("used")
    val used: ResponsePointsDto
)
