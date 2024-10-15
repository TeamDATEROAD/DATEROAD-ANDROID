package org.sopt.teamdateroad.data.dataremote.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestPlaceDto(
    @SerialName("sequence")
    val sequence: Int,
    @SerialName("title")
    val title: String,
    @SerialName("duration")
    val duration: Float
)
