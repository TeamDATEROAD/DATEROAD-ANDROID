package org.sopt.dateroad.data.dataremote.datasourceimpl

import javax.inject.Inject
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.sopt.dateroad.data.dataremote.datasource.CourseRemoteDataSource
import org.sopt.dateroad.data.dataremote.model.response.ResponseCourseDetailDto
import org.sopt.dateroad.data.dataremote.model.response.ResponseCoursesDto
import org.sopt.dateroad.data.dataremote.service.CourseService

class CourseRemoteDataSourceImpl @Inject constructor(
    private val courseService: CourseService
) : CourseRemoteDataSource {
    override suspend fun deleteCourse(courseId: Int) = courseService.deleteCourse(courseId = courseId)

    override suspend fun deleteCourseLike(courseId: Int) = courseService.deleteCourseLike(courseId = courseId)

    override suspend fun getCourseDetail(courseId: Int): ResponseCourseDetailDto = courseService.getCourseDetail(courseId = courseId)

    override suspend fun getFilteredCourses(country: String?, city: String?, cost: Int?): ResponseCoursesDto = courseService.getFilteredCourses(country = country, city = city, cost = cost)

    override suspend fun getSortedCourses(sortBy: String): ResponseCoursesDto = courseService.getSortedCourses(sortBy = sortBy)

    override suspend fun postCourse(images: List<MultipartBody.Part>, course: RequestBody, tags: RequestBody, places: RequestBody) = courseService.postCourse(images = images, course = course, tags = tags, places = places)

    override suspend fun postCourseLike(courseId: Int) = courseService.postCourseLike(courseId = courseId)
}
