package org.sopt.dateroad.domain.repository

import org.sopt.dateroad.domain.model.Course
import org.sopt.dateroad.domain.model.CourseDetail
import org.sopt.dateroad.domain.type.MoneyTagType
import org.sopt.dateroad.domain.type.RegionType
import org.sopt.dateroad.domain.type.SortByType

interface CourseRepository {
    suspend fun deleteCourse(courseId: Int): Result<Unit>

    suspend fun deleteCourseLike(courseId: Int): Result<Unit>

    suspend fun getCourseDetail(courseId: Int): Result<CourseDetail>

    suspend fun getFilteredCourses(country: RegionType?, city: Any?, cost: MoneyTagType?): Result<List<Course>>

    suspend fun getSortedCourses(sortedBy: SortByType): Result<List<Course>>

    suspend fun postCourseLike(courseId: Int): Result<Unit>
}
