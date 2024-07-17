package org.sopt.dateroad.data.dataremote.datasource

import org.sopt.dateroad.data.dataremote.model.response.ResponseAdvertisementsDto

interface AdvertisementRemoteDataSource {
    suspend fun getHomeAdvertisements(): ResponseAdvertisementsDto
}
