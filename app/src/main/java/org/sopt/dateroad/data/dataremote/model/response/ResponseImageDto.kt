package org.sopt.dateroad.data.dataremote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseImageDto(
    @SerialName("imageUrl")
    val imageUrl: String,
    @SerialName("sequence")
    val sequence: Int
)