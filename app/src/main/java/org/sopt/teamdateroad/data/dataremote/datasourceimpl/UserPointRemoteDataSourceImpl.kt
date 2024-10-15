package org.sopt.teamdateroad.data.dataremote.datasourceimpl

import javax.inject.Inject
import org.sopt.teamdateroad.data.dataremote.datasource.UserPointRemoteDataSource
import org.sopt.teamdateroad.data.dataremote.model.request.RequestUsePointDto
import org.sopt.teamdateroad.data.dataremote.model.response.ResponsePointHistoryDto
import org.sopt.teamdateroad.data.dataremote.model.response.ResponseUserPointDto
import org.sopt.teamdateroad.data.dataremote.model.response.ResponseUserUsePointDto
import org.sopt.teamdateroad.data.dataremote.service.UserPointService

class UserPointRemoteDataSourceImpl @Inject constructor(
    private val userPointService: UserPointService
) : UserPointRemoteDataSource {
    override suspend fun getUserPoint(): ResponseUserPointDto = userPointService.getUserPoint()

    override suspend fun getPointHistory(): ResponsePointHistoryDto = userPointService.getPointHistory()

    override suspend fun postUsePoint(courseId: Int, requestUsePointDto: RequestUsePointDto): ResponseUserUsePointDto = userPointService.postUsePoint(courseId = courseId, requestUsePointDto = requestUsePointDto)
}
