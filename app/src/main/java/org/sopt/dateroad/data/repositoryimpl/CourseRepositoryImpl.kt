package org.sopt.dateroad.data.repositoryimpl

import javax.inject.Inject
import org.sopt.dateroad.data.dataremote.datasource.CourseRemoteDataSource
import org.sopt.dateroad.data.mapper.todomain.toDomain
import org.sopt.dateroad.domain.model.Course
import org.sopt.dateroad.domain.model.CourseDetail
import org.sopt.dateroad.domain.repository.CourseRepository
import org.sopt.dateroad.domain.type.MoneyTagType
import org.sopt.dateroad.domain.type.SortByType

class CourseRepositoryImpl @Inject constructor(
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

    override suspend fun getFilteredCourses(country: String, city: String, cost: MoneyTagType): Result<List<Course>> = runCatching {
        courseRemoteDataSource.getFilteredCourses(country = country, city = city, cost = cost.costParameter).toDomain()
    }

    override suspend fun getSortedCourses(sortedBy: SortByType): Result<List<Course>> = runCatching {
        courseRemoteDataSource.getSortedCourses(sortBy = sortedBy.name).toDomain()
    }

    override suspend fun postCourseLike(courseId: Int): Result<Unit> = runCatching {
        courseRemoteDataSource.postCourseLike(courseId = courseId)
    }
}
