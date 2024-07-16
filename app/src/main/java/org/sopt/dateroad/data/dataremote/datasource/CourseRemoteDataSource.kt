package org.sopt.dateroad.data.dataremote.datasource

import org.sopt.dateroad.data.dataremote.model.response.ResponseCourseDetailDto
import org.sopt.dateroad.data.dataremote.model.response.ResponseCoursesDto

interface CourseRemoteDataSource {
    suspend fun deleteCourse(courseId: Int)

    suspend fun deleteCourseLike(courseId: Int)

    suspend fun getCourseDetail(courseId: Int): ResponseCourseDetailDto

    suspend fun getFilteredCourses(country: String?, city: String?, cost: Int?): ResponseCoursesDto

    suspend fun getSortedCourses(sortBy: String): ResponseCoursesDto

    suspend fun postCourseLike(courseId: Int)
}
