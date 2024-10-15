package org.sopt.teamdateroad.data.repositoryimpl

import javax.inject.Inject
import org.sopt.teamdateroad.data.dataremote.datasource.MyCourseRemoteDataSource
import org.sopt.teamdateroad.data.mapper.todomain.toDomain
import org.sopt.teamdateroad.domain.model.Course
import org.sopt.teamdateroad.domain.repository.MyCourseRepository

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
