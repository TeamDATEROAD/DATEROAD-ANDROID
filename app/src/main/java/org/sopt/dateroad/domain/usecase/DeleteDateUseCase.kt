package org.sopt.dateroad.domain.usecase

import javax.inject.Inject
import javax.inject.Singleton
import org.sopt.dateroad.domain.repository.DateRepository

@Singleton
class DeleteDateUseCase @Inject constructor(
    private val dateRepository: DateRepository
) {
    suspend operator fun invoke(dateId: Long): Result<Unit> {
        return runCatching {
            dateRepository.deleteDate(dateId)
        }
    }
}
