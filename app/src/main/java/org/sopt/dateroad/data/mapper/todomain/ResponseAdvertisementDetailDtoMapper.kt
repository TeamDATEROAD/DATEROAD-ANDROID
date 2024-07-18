package org.sopt.dateroad.data.mapper.todomain

import org.sopt.dateroad.data.dataremote.model.response.ResponseAdvertisementDetailDto
import org.sopt.dateroad.data.mapper.toEntity.toAdvertisementDetailDate
import org.sopt.dateroad.domain.model.AdvertisementDetail
import org.sopt.dateroad.domain.type.AdvertisementTagType.Companion.toAdvertisementTagTitle

fun ResponseAdvertisementDetailDto.toDomain(): AdvertisementDetail = AdvertisementDetail(
    images = this.images.sortedBy { responseImageDto -> responseImageDto.sequence }.map { responseImageDto -> responseImageDto.imageUrl },
    advertisementTagTitle = this.advertisementTagType.toAdvertisementTagTitle(),
    title = this.title,
    createAt = this.createAt.toAdvertisementDetailDate(),
    description = this.description
)
