package org.sopt.dateroad.data.dataremote.datasource

import org.sopt.dateroad.data.dataremote.model.request.RequestUsePointDto
import org.sopt.dateroad.data.dataremote.model.response.ResponsePointHistoryDto
import org.sopt.dateroad.data.dataremote.model.response.ResponseUserPointDto

interface UserPointRemoteDataSource {
    suspend fun getUserPoint(userId: Int): ResponseUserPointDto

    suspend fun getPointHistory(): ResponsePointHistoryDto

    suspend fun postUsePoint(courseId: Int, requestUsePointDto: RequestUsePointDto)
}
