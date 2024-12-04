package org.sopt.teamdateroad.data.repositoryimpl

import android.content.ContentResolver
import android.net.Uri
import javax.inject.Inject
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.sopt.teamdateroad.data.dataremote.datasource.CourseRemoteDataSource
import org.sopt.teamdateroad.data.dataremote.util.ContentUriRequestBody
import org.sopt.teamdateroad.data.mapper.todata.toCourseData
import org.sopt.teamdateroad.data.mapper.todata.toData
import org.sopt.teamdateroad.data.mapper.todomain.toDomain
import org.sopt.teamdateroad.domain.model.Course
import org.sopt.teamdateroad.domain.model.CourseDetail
import org.sopt.teamdateroad.domain.model.Enroll
import org.sopt.teamdateroad.domain.model.EnrollCourseResult
import org.sopt.teamdateroad.domain.repository.CourseRepository
import org.sopt.teamdateroad.domain.type.GyeonggiAreaType
import org.sopt.teamdateroad.domain.type.IncheonAreaType
import org.sopt.teamdateroad.domain.type.MoneyTagType
import org.sopt.teamdateroad.domain.type.RegionType
import org.sopt.teamdateroad.domain.type.SeoulAreaType
import org.sopt.teamdateroad.domain.type.SortByType

class CourseRepositoryImpl @Inject constructor(
    private val contentResolver: ContentResolver,
    private val courseRemoteDataSource: CourseRemoteDataSource
) : CourseRepository {
    override suspend fun deleteCourse(courseId: Int): Result<Unit> = runCatching {
        courseRemoteDataSource.deleteCourse(courseId = courseId)
    }

    override suspend fun deleteCourseLike(courseId: Int): Result<Unit> = runCatching {
        courseRemoteDataSource.deleteCourseLike(courseId = courseId)
    }

    override suspend fun getCourseDetail(courseId: Int): Result<CourseDetail> = runCatching {
        courseRemoteDataSource.getCourseDetail(courseId = courseId).toDomain()
    }

    override suspend fun getFilteredCourses(country: RegionType?, city: Any?, cost: MoneyTagType?): Result<List<Course>> = runCatching {
        courseRemoteDataSource.getFilteredCourses(
            country = country?.title,
            city = city?.let {
                when (it) {
                    is SeoulAreaType -> it.title
                    is GyeonggiAreaType -> it.title
                    is IncheonAreaType -> it.title
                    else -> null
                }
            },
            cost = cost?.costParameter
        ).toDomain()
    }

    override suspend fun getSortedCourses(sortedBy: SortByType): Result<List<Course>> = runCatching {
        courseRemoteDataSource.getSortedCourses(sortBy = sortedBy.name).toDomain()
    }

    override suspend fun postCourse(enroll: Enroll): Result<EnrollCourseResult> = runCatching {
        courseRemoteDataSource.postCourse(
            images = enroll.images.map { image -> ContentUriRequestBody(contentResolver = contentResolver, uri = Uri.parse(image)).toFormData() },
            course = Json.encodeToString(enroll.toCourseData()).toRequestBody(APPLICATION_JSON.toMediaType()),
            places = Json.encodeToString(enroll.places.mapIndexed { index, place -> place.toData(sequence = index + 1) }).toRequestBody(APPLICATION_JSON.toMediaType()),
            tags = Json.encodeToString(enroll.tags.map { tag -> tag.toData() }).toRequestBody(APPLICATION_JSON.toMediaType())
        ).toDomain()
    }

    override suspend fun postCourseLike(courseId: Int): Result<Unit> = runCatching {
        courseRemoteDataSource.postCourseLike(courseId = courseId)
    }

    companion object {
        const val APPLICATION_JSON = "application/json"
    }
}
