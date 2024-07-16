package org.sopt.dateroad.data.repositoryimpl

import javax.inject.Inject
import org.sopt.dateroad.data.dataremote.datasource.MyCourseRemoteDataSource
import org.sopt.dateroad.data.mapper.todomain.toDomain
import org.sopt.dateroad.domain.model.Course
import org.sopt.dateroad.domain.repository.MyCourseRepository

class MyCourseRepositoryImpl @Inject constructor(
    private val myCourseRemoteDataSource: MyCourseRemoteDataSource
) : MyCourseRepository {
    override suspend fun getMyCourseEnroll(): Result<List<Course>> = runCatching {
        myCourseRemoteDataSource.getMyCourseEnroll().toDomain()
    }

    override suspend fun getMyCourseRead(): Result<List<Course>> = runCatching {
        myCourseRemoteDataSource.getMyCourseRead().toDomain()
    }
}
