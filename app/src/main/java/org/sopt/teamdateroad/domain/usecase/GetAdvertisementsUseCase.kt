package org.sopt.teamdateroad.domain.usecase

import javax.inject.Inject
import javax.inject.Singleton
import org.sopt.teamdateroad.domain.model.Advertisement
import org.sopt.teamdateroad.domain.repository.AdvertisementRepository

@Singleton
class GetAdvertisementsUseCase @Inject constructor(
    private val advertisementRepository: AdvertisementRepository
) {
    suspend operator fun invoke(): Result<List<Advertisement>> = advertisementRepository.getHomeAdvertisements()
}
