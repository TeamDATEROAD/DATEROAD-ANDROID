package org.sopt.dateroad.domain.repository

import org.sopt.dateroad.domain.model.AdvertisementDetail
import org.sopt.dateroad.domain.model.Advertisement

interface AdvertisementRepository {
    suspend fun getAdvertisementDetail(advertisementId: Int): Result<AdvertisementDetail>

    suspend fun getHomeAdvertisements(): Result<List<Advertisement>>
}
