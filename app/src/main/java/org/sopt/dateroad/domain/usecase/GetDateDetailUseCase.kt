package org.sopt.dateroad.domain.usecase

import javax.inject.Inject
import org.sopt.dateroad.data.dataremote.model.response.ResponseDateDetailDto
import org.sopt.dateroad.domain.repository.DateRepository

class GetDateDetailUseCase @Inject constructor(
    private val dateRepository: DateRepository
) {
    suspend operator fun invoke(dateId: Long): Result<ResponseDateDetailDto> = runCatching {
        dateRepository.getDateDetail(dateId)
    }
}
