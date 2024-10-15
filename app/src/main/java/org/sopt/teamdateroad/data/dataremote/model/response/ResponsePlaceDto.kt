package org.sopt.teamdateroad.data.dataremote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponsePlaceDto(
    @SerialName("sequence")
    val sequence: Int,
    @SerialName("title")
    val title: String,
    @SerialName("duration")
    val duration: Float
)
