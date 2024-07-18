package org.sopt.dateroad.data.dataremote.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestUsePointDto(
    @SerialName("point")
    val point: Int=50,
    @SerialName("type")
    val type: String="POINT_USED",
    @SerialName("description")
    val description: String="포인트 사용"
)
