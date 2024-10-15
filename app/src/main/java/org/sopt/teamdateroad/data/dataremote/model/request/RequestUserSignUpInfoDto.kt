package org.sopt.teamdateroad.data.dataremote.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestUserSignUpInfoDto(
    @SerialName("name")
    val name: String,
    @SerialName("platform")
    val platform: String
)
