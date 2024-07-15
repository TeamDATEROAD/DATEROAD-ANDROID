package org.sopt.dateroad.domain.usecase

import javax.inject.Inject
import javax.inject.Singleton
import org.sopt.dateroad.domain.model.Course
import org.sopt.dateroad.domain.repository.CourseRepository
import org.sopt.dateroad.domain.type.MoneyTagType

@Singleton
class GetFilteredCourses @Inject constructor(
    private val courseRepository: CourseRepository
) {
    suspend operator fun invoke(country: String, city: String, cost: MoneyTagType): Result<List<Course>> = courseRepository.getFilteredCourses(country = country, city = city, cost = cost)
}
