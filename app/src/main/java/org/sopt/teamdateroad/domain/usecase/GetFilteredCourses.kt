package org.sopt.teamdateroad.domain.usecase

import javax.inject.Inject
import javax.inject.Singleton
import org.sopt.teamdateroad.domain.model.Course
import org.sopt.teamdateroad.domain.repository.CourseRepository
import org.sopt.teamdateroad.domain.type.MoneyTagType
import org.sopt.teamdateroad.domain.type.RegionType

@Singleton
class GetFilteredCourses @Inject constructor(
    private val courseRepository: CourseRepository
) {
    suspend operator fun invoke(country: RegionType?, city: Any?, cost: MoneyTagType?): Result<List<Course>> = courseRepository.getFilteredCourses(country = country, city = city, cost = cost)
}
