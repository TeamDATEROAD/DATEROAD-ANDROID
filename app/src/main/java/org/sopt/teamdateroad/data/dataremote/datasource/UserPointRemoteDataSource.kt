package org.sopt.teamdateroad.data.dataremote.datasource

import org.sopt.teamdateroad.data.dataremote.model.request.RequestUsePointDto
import org.sopt.teamdateroad.data.dataremote.model.response.ResponsePointHistoryDto
import org.sopt.teamdateroad.data.dataremote.model.response.ResponseUserPointDto
import org.sopt.teamdateroad.data.dataremote.model.response.ResponseUserUsePointDto

interface UserPointRemoteDataSource {
    suspend fun getUserPoint(): ResponseUserPointDto

    suspend fun getPointHistory(): ResponsePointHistoryDto

    suspend fun postUsePoint(courseId: Int, requestUsePointDto: RequestUsePointDto): ResponseUserUsePointDto
}
