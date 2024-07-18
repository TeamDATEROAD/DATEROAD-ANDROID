package org.sopt.dateroad.data.dataremote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseAdvertisementDto(
    @SerialName("advertisementId")
    val advertisementId: Int,
    @SerialName("thumbnail")
    val thumbnail: String
)
