package org.sopt.teamdateroad.data.dataremote.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class RequestSignInDto(
    @SerialName("platform")
    val platform: String
)