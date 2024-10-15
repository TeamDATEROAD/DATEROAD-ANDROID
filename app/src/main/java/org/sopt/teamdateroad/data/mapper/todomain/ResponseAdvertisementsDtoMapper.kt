package org.sopt.teamdateroad.data.mapper.todomain

import org.sopt.teamdateroad.data.dataremote.model.response.ResponseAdvertisementsDto
import org.sopt.teamdateroad.domain.model.Advertisement

fun ResponseAdvertisementsDto.toDomain(): List<Advertisement> = this.advertisements.map { responseAdvertisementDto -> responseAdvertisementDto.toDomain() }
