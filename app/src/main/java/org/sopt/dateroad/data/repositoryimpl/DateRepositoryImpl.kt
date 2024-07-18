package org.sopt.dateroad.data.repositoryimpl

import javax.inject.Inject
import org.sopt.dateroad.data.dataremote.datasource.DateRemoteDataSource
import org.sopt.dateroad.data.mapper.todata.toDateData
import org.sopt.dateroad.data.mapper.todomain.toDatesDomain
import org.sopt.dateroad.data.mapper.todomain.toDomain
import org.sopt.dateroad.data.mapper.todomain.toTimelineCardDomain
import org.sopt.dateroad.domain.model.Date
import org.sopt.dateroad.domain.model.DateDetail
import org.sopt.dateroad.domain.model.Enroll
import org.sopt.dateroad.domain.model.MainDate
import org.sopt.dateroad.domain.repository.DateRepository
import org.sopt.dateroad.domain.type.DateTimeType

class DateRepositoryImpl @Inject constructor(
    private val dateRemoteDataSource: DateRemoteDataSource
) : DateRepository {
    override suspend fun deleteDate(dateId: Int) {
        dateRemoteDataSource.deleteDate(dateId)
    }

    override suspend fun getDateDetail(dateId: Int): DateDetail = dateRemoteDataSource.getDateDetail(dateId).toDomain()

    override suspend fun getDates(time: DateTimeType): List<Date> = dateRemoteDataSource.getDates(time.name).dates.map {
        when (time) {
            DateTimeType.PAST -> it.toDatesDomain()
            DateTimeType.FUTURE -> it.toTimelineCardDomain()
        }
    }

    override suspend fun getNearestDate(): MainDate = dateRemoteDataSource.getNearestDate().toDomain()

    override suspend fun postDate(date: Enroll) = runCatching {
        dateRemoteDataSource.postDate(requestDateDto = date.toDateData())
    }
}
