package org.sopt.dateroad.data.dataremote.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class RequestReissueDto(
    @SerialName("refreshToken")
    val refreshToken: String
)
