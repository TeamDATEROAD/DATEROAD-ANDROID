package org.sopt.teamdateroad.domain.repository

import org.sopt.teamdateroad.domain.model.Advertisement
import org.sopt.teamdateroad.domain.model.AdvertisementDetail

interface AdvertisementRepository {
    suspend fun getAdvertisementDetail(advertisementId: Int): Result<AdvertisementDetail>

    suspend fun getHomeAdvertisements(): Result<List<Advertisement>>
}
