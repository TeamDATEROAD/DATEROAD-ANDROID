package org.sopt.teamdateroad.domain.usecase

import javax.inject.Inject
import javax.inject.Singleton
import org.sopt.teamdateroad.domain.model.Course
import org.sopt.teamdateroad.domain.repository.CourseRepository
import org.sopt.teamdateroad.domain.type.SortByType

@Singleton
class GetSortedCoursesUseCase @Inject constructor(
    private val courseRepository: CourseRepository
) {
    suspend operator fun invoke(sortedBy: SortByType): Result<List<Course>> = courseRepository.getSortedCourses(sortedBy = sortedBy)
}
