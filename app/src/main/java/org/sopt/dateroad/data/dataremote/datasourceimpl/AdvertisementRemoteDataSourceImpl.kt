package org.sopt.dateroad.data.dataremote.datasourceimpl

import javax.inject.Inject
import org.sopt.dateroad.data.dataremote.datasource.AdvertisementRemoteDataSource
import org.sopt.dateroad.data.dataremote.model.response.ResponseAdvertisementsDto
import org.sopt.dateroad.data.dataremote.service.AdvertisementService

class AdvertisementRemoteDataSourceImpl @Inject constructor(
    private val advertisementService: AdvertisementService
) : AdvertisementRemoteDataSource {
    override suspend fun getHomeAdvertisements(): ResponseAdvertisementsDto = advertisementService.getHomeAdvertisements()
}
