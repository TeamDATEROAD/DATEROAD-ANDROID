package org.sopt.teamdateroad.data.mapper.todomain

import org.sopt.teamdateroad.data.dataremote.model.response.ResponseAdvertisementDto
import org.sopt.teamdateroad.domain.model.Advertisement

fun ResponseAdvertisementDto.toDomain(): Advertisement = Advertisement(
    advertisementId = this.advertisementId,
    thumbnail = this.thumbnail
)
