package org.sopt.dateroad.domain.repository

import org.sopt.dateroad.domain.model.PointHistory
import org.sopt.dateroad.domain.model.UsePoint
import org.sopt.dateroad.domain.model.UserPoint

interface UserPointRepository {
    suspend fun getUserPoint(): Result<UserPoint>

    suspend fun getPointHistory(): Result<PointHistory>

    suspend fun postUsePoint(courseId: Int, usePoint: UsePoint)

    suspend fun getUserProfileMain(userId: Int): Result<UserPoint>
}
