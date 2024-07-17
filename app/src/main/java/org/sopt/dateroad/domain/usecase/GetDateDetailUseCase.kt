package org.sopt.dateroad.domain.usecase

import javax.inject.Inject
import org.sopt.dateroad.domain.model.DateDetail
import org.sopt.dateroad.domain.repository.DateRepository

class GetDateDetailUseCase @Inject constructor(
    private val dateRepository: DateRepository
) {
    suspend operator fun invoke(dateId: Int): Result<DateDetail> = runCatching {
        dateRepository.getDateDetail(dateId)
    }
}
