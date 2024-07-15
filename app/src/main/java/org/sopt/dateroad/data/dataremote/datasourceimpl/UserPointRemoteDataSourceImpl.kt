package org.sopt.dateroad.data.dataremote.datasourceimpl

import org.sopt.dateroad.data.dataremote.datasource.UserPointRemoteDataSource
import org.sopt.dateroad.data.dataremote.model.request.RequestUsePointDto
import org.sopt.dateroad.data.dataremote.model.response.ResponsePointHistoryDto
import org.sopt.dateroad.data.dataremote.model.response.ResponseUserPointDto
import org.sopt.dateroad.data.dataremote.service.UserPointService
import javax.inject.Inject

class UserPointRemoteDataSourceImpl @Inject constructor(
    private val userPointService: UserPointService
) : UserPointRemoteDataSource {
    override suspend fun getUserPoint(): ResponseUserPointDto = userPointService.getUserPoint()

    override suspend fun getPointHistory(): ResponsePointHistoryDto = userPointService.getPointHistory()

    override suspend fun postUsePoint(courseId: Int, requestUsePointDto: RequestUsePointDto) = userPointService.postUsePoint(courseId = courseId, requestUsePointDto = requestUsePointDto)
}