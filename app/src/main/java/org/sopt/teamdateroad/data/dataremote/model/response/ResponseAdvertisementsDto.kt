package org.sopt.teamdateroad.data.dataremote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ResponseAdvertisementsDto(
    @SerialName("advertisementDtoResList")
    val advertisements: List<ResponseAdvertisementDto>
)
