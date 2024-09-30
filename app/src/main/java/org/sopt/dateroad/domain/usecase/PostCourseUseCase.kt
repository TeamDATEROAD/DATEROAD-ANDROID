package org.sopt.dateroad.domain.usecase

import javax.inject.Inject
import javax.inject.Singleton
import org.sopt.dateroad.domain.model.Enroll
import org.sopt.dateroad.domain.model.EnrollCourseResult
import org.sopt.dateroad.domain.repository.CourseRepository

@Singleton
class PostCourseUseCase @Inject constructor(
    private val courseRepository: CourseRepository
) {
    suspend operator fun invoke(enroll: Enroll): Result<EnrollCourseResult> = courseRepository.postCourse(enroll = enroll)
}
