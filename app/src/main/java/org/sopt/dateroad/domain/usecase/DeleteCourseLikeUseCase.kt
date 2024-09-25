package org.sopt.dateroad.domain.usecase

import javax.inject.Inject
import javax.inject.Singleton
import org.sopt.dateroad.domain.repository.CourseRepository

@Singleton
class DeleteCourseLikeUseCase @Inject constructor(
    private val courseRepository: CourseRepository
) {
    suspend operator fun invoke(courseId: Int): Result<Unit> = courseRepository.deleteCourseLike(courseId = courseId)
}
