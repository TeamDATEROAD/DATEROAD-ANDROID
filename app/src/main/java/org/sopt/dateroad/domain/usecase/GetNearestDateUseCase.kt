package org.sopt.dateroad.domain.usecase

import javax.inject.Inject
import org.sopt.dateroad.data.dataremote.model.response.ResponseNearestDateDto
import org.sopt.dateroad.domain.repository.DateRepository

class GetNearestDateUseCase @Inject constructor(
    private val dateRepository: DateRepository
) {
    suspend operator fun invoke(): Result<ResponseNearestDateDto> = runCatching {
        dateRepository.getNearestDate()
    }
}
