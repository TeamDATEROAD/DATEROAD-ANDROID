package org.sopt.dateroad.data.repositoryimpl

import javax.inject.Inject
import org.sopt.dateroad.data.dataremote.datasource.UserPointRemoteDataSource
import org.sopt.dateroad.data.mapper.todata.toData
import org.sopt.dateroad.data.mapper.todomain.toDomain
import org.sopt.dateroad.domain.model.PointHistory
import org.sopt.dateroad.domain.model.UsePoint
import org.sopt.dateroad.domain.model.UserPoint
import org.sopt.dateroad.domain.repository.UserPointRepository

class UserPointRepositoryImpl @Inject constructor(
    private val userPointRemoteDataSource: UserPointRemoteDataSource
) : UserPointRepository {
    override suspend fun getUserPoint(): Result<UserPoint> = runCatching {
        userPointRemoteDataSource.getUserPoint().toDomain()
    }

    override suspend fun getPointHistory(): Result<PointHistory> = runCatching {
        userPointRemoteDataSource.getPointHistory().toDomain()
    }

    override suspend fun postUsePoint(courseId: Int, usePoint: UsePoint) {
        userPointRemoteDataSource.postUsePoint(courseId = courseId, requestUsePointDto = usePoint.toData())
    }
}
