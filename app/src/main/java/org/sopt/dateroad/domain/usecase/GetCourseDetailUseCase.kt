package org.sopt.dateroad.domain.usecase

import javax.inject.Inject
import javax.inject.Singleton
import org.sopt.dateroad.domain.repository.CourseRepository

@Singleton
class GetCourseDetailUseCase @Inject constructor(
    private val courseRepository: CourseRepository
) {
    suspend operator fun invoke(courseId: Int) = courseRepository.getCourseDetail(courseId = courseId)
}
