package org.sopt.teamdateroad.data.dataremote.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestWithdrawDto(
    @SerialName("authCode")
    val authCode: String? = null
)
