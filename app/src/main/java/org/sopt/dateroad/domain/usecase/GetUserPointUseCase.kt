package org.sopt.dateroad.domain.usecase

import javax.inject.Inject
import org.sopt.dateroad.domain.model.UserPoint
import org.sopt.dateroad.domain.repository.UserPointRepository

class GetUserPointUseCase @Inject constructor(
    private val userPointRepository: UserPointRepository
) {
    suspend operator fun invoke(): Result<UserPoint> = userPointRepository.getUserPoint()
}
