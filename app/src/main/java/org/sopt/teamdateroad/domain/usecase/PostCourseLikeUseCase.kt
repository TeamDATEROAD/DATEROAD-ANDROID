package org.sopt.teamdateroad.domain.usecase

import javax.inject.Inject
import javax.inject.Singleton
import org.sopt.teamdateroad.domain.repository.CourseRepository

@Singleton
class PostCourseLikeUseCase @Inject constructor(
    private val courseRepository: CourseRepository
) {
    suspend operator fun invoke(courseId: Int): Result<Unit> = courseRepository.postCourseLike(courseId = courseId)
}
