package org.sopt.dateroad.data.dataremote.datasource

import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.sopt.dateroad.data.dataremote.model.response.ResponseCourseDetailDto
import org.sopt.dateroad.data.dataremote.model.response.ResponseCoursesDto
import org.sopt.dateroad.data.dataremote.model.response.ResponseEnrollCourseDto

interface CourseRemoteDataSource {
    suspend fun deleteCourse(courseId: Int)

    suspend fun deleteCourseLike(courseId: Int)

    suspend fun getCourseDetail(courseId: Int): ResponseCourseDetailDto

    suspend fun getFilteredCourses(country: String?, city: String?, cost: Int?): ResponseCoursesDto

    suspend fun getSortedCourses(sortBy: String): ResponseCoursesDto

    suspend fun postCourse(images: List<MultipartBody.Part>, course: RequestBody, tags: RequestBody, places: RequestBody): ResponseEnrollCourseDto

    suspend fun postCourseLike(courseId: Int)
}
