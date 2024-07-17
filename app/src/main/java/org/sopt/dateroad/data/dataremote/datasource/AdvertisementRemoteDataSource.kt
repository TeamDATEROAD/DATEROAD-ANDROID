package org.sopt.dateroad.data.dataremote.datasource

import org.sopt.dateroad.data.dataremote.model.response.ResponseAdvertisementDetailDto
import org.sopt.dateroad.data.dataremote.model.response.ResponseAdvertisementsDto

interface AdvertisementRemoteDataSource {
    suspend fun getAdvertisementDetail(advertisementId: Int): ResponseAdvertisementDetailDto
    suspend fun getHomeAdvertisements(): ResponseAdvertisementsDto
}
