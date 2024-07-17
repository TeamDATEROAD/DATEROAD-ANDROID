package org.sopt.dateroad.data.dataremote.datasourceimpl

import javax.inject.Inject
import org.sopt.dateroad.data.dataremote.datasource.DateRemoteDataSource
import org.sopt.dateroad.data.dataremote.model.request.RequestDateDto
import org.sopt.dateroad.data.dataremote.service.DateService

class DateRemoteDataSourceImpl @Inject constructor(
    private val dateService: DateService
) : DateRemoteDataSource {
    override suspend fun postDate(requestDateDto: RequestDateDto) = dateService.postDate(requestDateDto = requestDateDto)
}
