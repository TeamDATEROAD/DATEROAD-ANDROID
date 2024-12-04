package org.sopt.teamdateroad.data.repositoryimpl

import javax.inject.Inject
import org.sopt.teamdateroad.data.dataremote.datasource.AdvertisementRemoteDataSource
import org.sopt.teamdateroad.data.mapper.todomain.toDomain
import org.sopt.teamdateroad.domain.model.Advertisement
import org.sopt.teamdateroad.domain.model.AdvertisementDetail
import org.sopt.teamdateroad.domain.repository.AdvertisementRepository

class AdvertisementRepositoryImpl @Inject constructor(
    private val advertisementRemoteDataSource: AdvertisementRemoteDataSource
) : AdvertisementRepository {
    override suspend fun getAdvertisementDetail(advertisementId: Int): Result<AdvertisementDetail> = runCatching {
        advertisementRemoteDataSource.getAdvertisementDetail(advertisementId = advertisementId).toDomain()
    }

    override suspend fun getHomeAdvertisements(): Result<List<Advertisement>> = runCatching {
        advertisementRemoteDataSource.getHomeAdvertisements().toDomain()
    }
}
