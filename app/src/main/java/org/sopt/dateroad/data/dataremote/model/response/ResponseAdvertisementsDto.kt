package org.sopt.dateroad.data.dataremote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ResponseAdvertisementsDto(
    @SerialName("advertisementDtoResList")
    val advertisementDtoResList: List<ResponseAdvertisementDto>
)
