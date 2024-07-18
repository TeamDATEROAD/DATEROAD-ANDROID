package org.sopt.dateroad.domain.usecase

import javax.inject.Inject
import org.sopt.dateroad.domain.model.MainDate
import org.sopt.dateroad.domain.repository.DateRepository

class GetNearestDateUseCase @Inject constructor(
    private val dateRepository: DateRepository
) {
    suspend operator fun invoke(): Result<MainDate> = runCatching {
        dateRepository.getNearestDate()
    }
}
