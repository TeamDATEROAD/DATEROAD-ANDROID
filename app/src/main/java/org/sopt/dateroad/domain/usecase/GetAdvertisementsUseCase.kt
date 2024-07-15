package org.sopt.dateroad.domain.usecase

import javax.inject.Inject
import javax.inject.Singleton
import org.sopt.dateroad.domain.model.Advertisement
import org.sopt.dateroad.domain.repository.AdvertisementRepository

@Singleton
class GetAdvertisementsUseCase @Inject constructor(
    private val advertisementRepository: AdvertisementRepository
) {
    suspend operator fun invoke(): Result<List<Advertisement>> = runCatching {
        advertisementRepository.getHomeAdvertisements().getOrThrow()
    }
}
