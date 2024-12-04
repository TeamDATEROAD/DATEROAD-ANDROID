package org.sopt.teamdateroad.domain.usecase

import javax.inject.Inject
import javax.inject.Singleton
import org.sopt.teamdateroad.domain.model.PointHistory
import org.sopt.teamdateroad.domain.repository.UserPointRepository

@Singleton
class GetPointHistoryUseCase @Inject constructor(
    private val userPointRepository: UserPointRepository
) {
    suspend operator fun invoke(): Result<PointHistory> = userPointRepository.getPointHistory()
}
