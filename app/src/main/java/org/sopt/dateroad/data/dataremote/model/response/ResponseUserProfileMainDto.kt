package org.sopt.dateroad.data.dataremote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ResponseUserProfileMainDto(
    @SerialName("name")
    val name: String,
    @SerialName("point")
    val point: Int,
    @SerialName("image")
    val image: String
)
