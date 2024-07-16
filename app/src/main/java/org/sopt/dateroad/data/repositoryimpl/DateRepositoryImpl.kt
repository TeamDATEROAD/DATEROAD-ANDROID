package org.sopt.dateroad.data.repositoryimpl

import javax.inject.Inject
import org.sopt.dateroad.data.dataremote.datasource.DateRemoteDataSource
import org.sopt.dateroad.data.dataremote.model.response.ResponseDateDetailDto
import org.sopt.dateroad.data.dataremote.model.response.ResponseDatesDto
import org.sopt.dateroad.domain.repository.DateRepository

class DateRepositoryImpl @Inject constructor(
    private val dateRemoteDataSource: DateRemoteDataSource
) : DateRepository {
    override suspend fun deleteDate(dateId: Long) {
        dateRemoteDataSource.deleteDate(dateId)
    }

    override suspend fun getDateDetail(dateId: Long): ResponseDateDetailDto {
        return dateRemoteDataSource.getDateDetail(dateId)
    }

    override suspend fun getDates(time: String): ResponseDatesDto {
        return dateRemoteDataSource.getDates(time)
    }
}
