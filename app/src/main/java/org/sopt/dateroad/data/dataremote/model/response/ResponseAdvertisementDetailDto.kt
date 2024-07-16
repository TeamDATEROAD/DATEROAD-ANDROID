package org.sopt.dateroad.data.dataremote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseAdvertisementDetailDto(
    @SerialName("images")
    val images: List<ResponseImageDto>,
    @SerialName("title")
    val title: String,
    @SerialName("createAt")
    val createAt: String,
    @SerialName("description")
    val description: String,
    @SerialName("adTagType")
    val advertisementTagType: String
)
