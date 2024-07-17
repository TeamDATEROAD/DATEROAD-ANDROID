package org.sopt.dateroad.data.dataremote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseProfileDto(
    @SerialName("name")
    val name: String,
    @SerialName("tags")
    val tags: List<String>,
    @SerialName("point")
    val point: Int,
    @SerialName("imageUrl")
    val imageUrl: String
)
