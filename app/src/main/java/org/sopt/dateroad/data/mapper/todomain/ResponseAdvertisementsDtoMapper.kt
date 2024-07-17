package org.sopt.dateroad.data.mapper.todomain

import org.sopt.dateroad.data.dataremote.model.response.ResponseAdvertisementsDto
import org.sopt.dateroad.domain.model.Advertisement

fun ResponseAdvertisementsDto.toDomain(): List<Advertisement> = this.advertisements.map { responseAdvertisementDto -> responseAdvertisementDto.toDomain() }
