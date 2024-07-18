package org.sopt.dateroad.data.dataremote.datasourceimpl

import javax.inject.Inject
import org.sopt.dateroad.data.dataremote.datasource.DateRemoteDataSource
import org.sopt.dateroad.data.dataremote.model.response.ResponseDateDetailDto
import org.sopt.dateroad.data.dataremote.model.response.ResponseDatesDto
import org.sopt.dateroad.data.dataremote.model.response.ResponseNearestDateDto
import org.sopt.dateroad.data.dataremote.model.request.RequestDateDto
import org.sopt.dateroad.data.dataremote.service.DateService

class DateRemoteDataSourceImpl @Inject constructor(
    private val dateService: DateService
) : DateRemoteDataSource {
    override suspend fun deleteDate(dateId: Int) = dateService.deleteDate(dateId)

    override suspend fun getDateDetail(dateId: Int): ResponseDateDetailDto = dateService.getDateDetail(dateId)

    override suspend fun getDates(time: String): ResponseDatesDto = dateService.getDates(time)

    override suspend fun getNearestDate(): ResponseNearestDateDto = dateService.getNearestDate()

    override suspend fun postDate(requestDateDto: RequestDateDto) = dateService.postDate(requestDateDto = requestDateDto)
}
