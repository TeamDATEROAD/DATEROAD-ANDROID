package org.sopt.dateroad.data.dataremote.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class RequestDummyDto(
    @SerialName("id")
    val id: Int,
    @SerialName("email")
    val email: String
)