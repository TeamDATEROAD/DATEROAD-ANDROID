package org.sopt.dateroad.data.dataremote.datasource

import org.sopt.dateroad.data.dataremote.model.request.RequestUsePointDto
import org.sopt.dateroad.data.dataremote.model.response.ResponsePointHistoryDto
import org.sopt.dateroad.data.dataremote.model.response.ResponseUserPointDto
import org.sopt.dateroad.data.dataremote.model.response.ResponseUserProfileMainDto

interface UserPointRemoteDataSource {
    suspend fun getUserPoint(): ResponseUserPointDto

    suspend fun getPointHistory(): ResponsePointHistoryDto

    suspend fun postUsePoint(courseId: Int, requestUsePointDto: RequestUsePointDto)

    suspend fun getUserProfileMain(userId: Int): ResponseUserProfileMainDto
}
