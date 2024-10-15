package org.sopt.teamdateroad.domain.usecase

import javax.inject.Inject
import javax.inject.Singleton
import org.sopt.teamdateroad.domain.model.Enroll
import org.sopt.teamdateroad.domain.model.EnrollCourseResult
import org.sopt.teamdateroad.domain.repository.CourseRepository

@Singleton
class PostCourseUseCase @Inject constructor(
    private val courseRepository: CourseRepository
) {
    suspend operator fun invoke(enroll: Enroll): Result<EnrollCourseResult> = courseRepository.postCourse(enroll = enroll)
}
