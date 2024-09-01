package org.sopt.dateroad.data.dataremote.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestEditProfileDto(
    @SerialName("name")
    val name: String,
    @SerialName("image")
    val image: String,
    @SerialName("tags")
    val tags: List<String>
)
