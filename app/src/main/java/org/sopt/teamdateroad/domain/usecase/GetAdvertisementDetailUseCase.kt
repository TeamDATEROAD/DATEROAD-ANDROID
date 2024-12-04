package org.sopt.teamdateroad.domain.usecase

import javax.inject.Inject
import javax.inject.Singleton
import org.sopt.teamdateroad.domain.model.AdvertisementDetail
import org.sopt.teamdateroad.domain.repository.AdvertisementRepository

@Singleton
class GetAdvertisementDetailUseCase @Inject constructor(
    private val advertisementRepository: AdvertisementRepository
) {
    suspend operator fun invoke(advertisementId: Int): Result<AdvertisementDetail> = advertisementRepository.getAdvertisementDetail(advertisementId = advertisementId)
}
