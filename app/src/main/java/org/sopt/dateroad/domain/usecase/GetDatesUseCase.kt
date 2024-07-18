package org.sopt.dateroad.domain.usecase

import javax.inject.Inject
import org.sopt.dateroad.domain.model.Date
import org.sopt.dateroad.domain.repository.DateRepository
import org.sopt.dateroad.domain.type.DateTimeType

class GetDatesUseCase @Inject constructor(
    private val dateRepository: DateRepository
) {
    suspend operator fun invoke(time: DateTimeType): Result<List<Date>> = runCatching {
        dateRepository.getDates(time = time)
    }
}
