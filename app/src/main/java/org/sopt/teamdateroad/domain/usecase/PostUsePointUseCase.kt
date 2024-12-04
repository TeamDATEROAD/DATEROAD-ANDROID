package org.sopt.teamdateroad.domain.usecase

import javax.inject.Inject
import javax.inject.Singleton
import org.sopt.teamdateroad.domain.model.PointUseResult
import org.sopt.teamdateroad.domain.model.UsePoint
import org.sopt.teamdateroad.domain.repository.UserPointRepository

@Singleton
class PostUsePointUseCase @Inject constructor(
    private val userPointRepository: UserPointRepository
) {
    suspend operator fun invoke(courseId: Int, usePoint: UsePoint): Result<PointUseResult> =
        userPointRepository.postUsePoint(courseId = courseId, usePoint = usePoint)
}
