package org.sopt.dateroad.data.dataremote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponsePointHistoryDto(
    @SerialName("gained")
    val gained: List<ResponsePointDto>,
    @SerialName("used")
    val used: List<ResponsePointDto>
)