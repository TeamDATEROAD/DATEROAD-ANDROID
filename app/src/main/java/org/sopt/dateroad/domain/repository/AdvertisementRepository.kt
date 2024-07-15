package org.sopt.dateroad.domain.repository

import org.sopt.dateroad.domain.model.Advertisement

interface AdvertisementRepository {
    suspend fun getHomeAdvertisements(): Result<List<Advertisement>>
}
