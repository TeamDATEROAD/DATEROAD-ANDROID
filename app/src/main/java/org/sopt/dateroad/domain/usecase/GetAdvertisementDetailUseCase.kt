package org.sopt.dateroad.domain.usecase

import javax.inject.Inject
import javax.inject.Singleton
import org.sopt.dateroad.domain.model.AdvertisementDetail
import org.sopt.dateroad.domain.repository.AdvertisementRepository

@Singleton
class GetAdvertisementDetailUseCase @Inject constructor(
    private val advertisementRepository: AdvertisementRepository
) {
    suspend operator fun invoke(advertisementId: Int): Result<AdvertisementDetail> = advertisementRepository.getAdvertisementDetail(advertisementId = advertisementId)
}
