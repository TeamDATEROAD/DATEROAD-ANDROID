package org.sopt.dateroad.domain.usecase

import javax.inject.Inject
import javax.inject.Singleton
import org.sopt.dateroad.domain.model.UsePoint
import org.sopt.dateroad.domain.repository.UserPointRepository

@Singleton
class PostUsePointUseCase @Inject constructor(
    private val userPointRepository: UserPointRepository
) {
    suspend operator fun invoke(courseId: Int, usePoint: UsePoint): Result<Unit> = runCatching {
        userPointRepository.postUsePoint(courseId = courseId, usePoint = usePoint)
    }
}
