package org.sopt.teamdateroad.domain.repository

import org.sopt.teamdateroad.domain.model.Course
import org.sopt.teamdateroad.domain.model.CourseDetail
import org.sopt.teamdateroad.domain.model.Enroll
import org.sopt.teamdateroad.domain.model.EnrollCourseResult
import org.sopt.teamdateroad.domain.type.MoneyTagType
import org.sopt.teamdateroad.domain.type.RegionType
import org.sopt.teamdateroad.domain.type.SortByType

interface CourseRepository {
    suspend fun deleteCourse(courseId: Int): Result<Unit>

    suspend fun deleteCourseLike(courseId: Int): Result<Unit>

    suspend fun getCourseDetail(courseId: Int): Result<CourseDetail>

    suspend fun getFilteredCourses(country: RegionType?, city: Any?, cost: MoneyTagType?): Result<List<Course>>

    suspend fun getSortedCourses(sortedBy: SortByType): Result<List<Course>>

    suspend fun postCourse(enroll: Enroll): Result<EnrollCourseResult>

    suspend fun postCourseLike(courseId: Int): Result<Unit>
}
