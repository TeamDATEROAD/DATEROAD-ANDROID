package org.sopt.teamdateroad.domain.repository

import org.sopt.teamdateroad.domain.model.PointHistory
import org.sopt.teamdateroad.domain.model.PointUseResult
import org.sopt.teamdateroad.domain.model.UsePoint
import org.sopt.teamdateroad.domain.model.UserPoint

interface UserPointRepository {
    suspend fun getUserPoint(): Result<UserPoint>

    suspend fun getPointHistory(): Result<PointHistory>

    suspend fun postUsePoint(courseId: Int, usePoint: UsePoint): Result<PointUseResult>
}
