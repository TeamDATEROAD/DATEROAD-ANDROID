package org.sopt.teamdateroad.domain.usecase

import javax.inject.Inject
import org.sopt.teamdateroad.domain.model.UserPoint
import org.sopt.teamdateroad.domain.repository.UserPointRepository

class GetUserPointUseCase @Inject constructor(
    private val userPointRepository: UserPointRepository
) {
    suspend operator fun invoke(): Result<UserPoint> = userPointRepository.getUserPoint()
}
