package org.sopt.dateroad.data.dataremote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponsePointsDto(
    @SerialName("points")
    val points: List<ResponsePointDto>
)
