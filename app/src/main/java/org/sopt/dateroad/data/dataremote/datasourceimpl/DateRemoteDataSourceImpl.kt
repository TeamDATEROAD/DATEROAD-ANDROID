package org.sopt.dateroad.data.dataremote.datasourceimpl

import javax.inject.Inject
import org.sopt.dateroad.data.dataremote.datasource.DateRemoteDataSource
import org.sopt.dateroad.data.dataremote.model.response.ResponseDateDetailDto
import org.sopt.dateroad.data.dataremote.model.response.ResponseDatesDto
import org.sopt.dateroad.data.dataremote.service.DateService

class DateRemoteDataSourceImpl @Inject constructor(
    private val dateService: DateService
) : DateRemoteDataSource {
    override suspend fun deleteDate(dateId: Long) {
        dateService.deleteDate(dateId)
    }

    override suspend fun getDateDetail(dateId: Long): ResponseDateDetailDto {
        return dateService.getDateDetail(dateId)
    }

    override suspend fun getDates(time: String): ResponseDatesDto {
        return dateService.getDates(time)
    }
}
