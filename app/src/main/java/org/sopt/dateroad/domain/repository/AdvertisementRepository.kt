package org.sopt.dateroad.domain.repository

import org.sopt.dateroad.domain.model.AdvertisementDetail

interface AdvertisementRepository {
    suspend fun getAdvertisementDetail(advertisementId: Int): Result<AdvertisementDetail>
}
