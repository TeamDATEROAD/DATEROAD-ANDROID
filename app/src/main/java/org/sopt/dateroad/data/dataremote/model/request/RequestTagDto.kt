package org.sopt.dateroad.data.dataremote.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestTagDto(
    @SerialName("tag")
    val tag: String
)