package org.sopt.dateroad.data.mapper.todomain

import org.sopt.dateroad.data.dataremote.model.response.ResponseAdvertisementDto
import org.sopt.dateroad.domain.model.Advertisement

fun ResponseAdvertisementDto.toDomain(): Advertisement = Advertisement(
    advertisementId = this.advertisementId,
    thumbnail = this.thumbnail
)
