package org.sopt.dateroad.domain.usecase

import javax.inject.Inject
import org.sopt.dateroad.data.dataremote.model.response.ResponseDatesDto
import org.sopt.dateroad.domain.repository.DateRepository

class GetDatesUseCase @Inject constructor(
    private val dateRepository: DateRepository
) {
    suspend operator fun invoke(time: String): Result<ResponseDatesDto> = runCatching {
        dateRepository.getDates(time)
    }
}
