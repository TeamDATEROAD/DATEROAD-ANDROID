package org.sopt.dateroad.data.dataremote.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestUsePointDto(
    @SerialName("point")
    val point: Int,
    @SerialName("type")
    val type: String,
    @SerialName("description")
    val description: String
)
