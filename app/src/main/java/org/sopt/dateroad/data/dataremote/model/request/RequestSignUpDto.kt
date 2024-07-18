package org.sopt.dateroad.data.dataremote.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestSignUpDto(
    @SerialName("userSignUpReq")
    val userSignUpReq: RequestUserSignUpInfoDto,
    @SerialName("image")
    val image: String,
    @SerialName("tag")
    val tag: List<String>
)
