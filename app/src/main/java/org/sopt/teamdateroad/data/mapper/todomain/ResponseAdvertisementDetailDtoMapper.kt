package org.sopt.teamdateroad.data.mapper.todomain

import org.sopt.teamdateroad.data.dataremote.model.response.ResponseAdvertisementDetailDto
import org.sopt.teamdateroad.data.mapper.toEntity.toAdvertisementDetailDate
import org.sopt.teamdateroad.domain.model.AdvertisementDetail
import org.sopt.teamdateroad.domain.type.AdvertisementTagType.Companion.toAdvertisementTagTitle

fun ResponseAdvertisementDetailDto.toDomain(): AdvertisementDetail = AdvertisementDetail(
    images = this.images.sortedBy { responseImageDto -> responseImageDto.sequence }.map { responseImageDto -> responseImageDto.imageUrl },
    advertisementTagTitle = this.advertisementTagType.toAdvertisementTagTitle(),
    title = this.title,
    createAt = this.createAt.toAdvertisementDetailDate(),
    description = this.description
)
