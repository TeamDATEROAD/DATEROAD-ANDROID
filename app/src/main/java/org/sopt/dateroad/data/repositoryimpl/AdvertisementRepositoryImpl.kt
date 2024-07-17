package org.sopt.dateroad.data.repositoryimpl

import org.sopt.dateroad.data.dataremote.datasource.AdvertisementRemoteDataSource
import org.sopt.dateroad.data.mapper.todomain.toDomain
import org.sopt.dateroad.domain.model.Advertisement
import org.sopt.dateroad.domain.model.AdvertisementDetail
import org.sopt.dateroad.domain.repository.AdvertisementRepository
import javax.inject.Inject

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

