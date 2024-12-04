package org.sopt.teamdateroad.data.dataremote.datasourceimpl

import javax.inject.Inject
import org.sopt.teamdateroad.data.dataremote.datasource.AdvertisementRemoteDataSource
import org.sopt.teamdateroad.data.dataremote.model.response.ResponseAdvertisementDetailDto
import org.sopt.teamdateroad.data.dataremote.model.response.ResponseAdvertisementsDto
import org.sopt.teamdateroad.data.dataremote.service.AdvertisementService

class AdvertisementRemoteDataSourceImpl @Inject constructor(
    private val advertisementService: AdvertisementService
) : AdvertisementRemoteDataSource {
    override suspend fun getAdvertisementDetail(advertisementId: Int): ResponseAdvertisementDetailDto = advertisementService.getAdvertisementDetail(advertisementId = advertisementId)

    override suspend fun getHomeAdvertisements(): ResponseAdvertisementsDto = advertisementService.getHomeAdvertisements()
}
