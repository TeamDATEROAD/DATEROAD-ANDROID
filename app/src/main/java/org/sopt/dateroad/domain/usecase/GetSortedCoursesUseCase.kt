package org.sopt.dateroad.domain.usecase

import javax.inject.Inject
import javax.inject.Singleton
import org.sopt.dateroad.domain.model.Course
import org.sopt.dateroad.domain.repository.CourseRepository
import org.sopt.dateroad.domain.type.SortByType

@Singleton
class GetSortedCoursesUseCase @Inject constructor(
    private val courseRepository: CourseRepository
) {
    suspend operator fun invoke(sortedBy: SortByType): Result<List<Course>> = courseRepository.getSortedCourses(sortedBy = sortedBy)
}
