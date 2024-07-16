package org.sopt.dateroad.data.dataremote.datasource

import org.sopt.dateroad.data.dataremote.model.response.ResponseAdvertisementDetailDto

interface AdvertisementRemoteDataSource {
    suspend fun getAdvertisementDetail(advertisementId: Int): ResponseAdvertisementDetailDto
}
