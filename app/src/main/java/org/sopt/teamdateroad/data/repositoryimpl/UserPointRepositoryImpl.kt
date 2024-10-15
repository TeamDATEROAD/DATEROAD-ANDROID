package org.sopt.teamdateroad.data.repositoryimpl

import javax.inject.Inject
import org.sopt.teamdateroad.data.dataremote.datasource.UserPointRemoteDataSource
import org.sopt.teamdateroad.data.mapper.todata.toData
import org.sopt.teamdateroad.data.mapper.todomain.toDomain
import org.sopt.teamdateroad.domain.model.PointHistory
import org.sopt.teamdateroad.domain.model.PointUseResult
import org.sopt.teamdateroad.domain.model.UsePoint
import org.sopt.teamdateroad.domain.model.UserPoint
import org.sopt.teamdateroad.domain.repository.UserPointRepository

class UserPointRepositoryImpl @Inject constructor(
    private val userPointRemoteDataSource: UserPointRemoteDataSource
) : UserPointRepository {
    override suspend fun getUserPoint(): Result<UserPoint> = runCatching {
        userPointRemoteDataSource.getUserPoint().toDomain()
    }

    override suspend fun getPointHistory(): Result<PointHistory> = runCatching {
        userPointRemoteDataSource.getPointHistory().toDomain()
    }

    override suspend fun postUsePoint(courseId: Int, usePoint: UsePoint): Result<PointUseResult> = runCatching {
        userPointRemoteDataSource.postUsePoint(courseId = courseId, requestUsePointDto = usePoint.toData()).toDomain()
    }
}
