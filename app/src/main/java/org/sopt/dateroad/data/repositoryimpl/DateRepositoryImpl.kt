package org.sopt.dateroad.data.repositoryimpl

import javax.inject.Inject
import org.sopt.dateroad.data.dataremote.datasource.DateRemoteDataSource
import org.sopt.dateroad.data.mapper.todata.toDateData
import org.sopt.dateroad.domain.model.Enroll
import org.sopt.dateroad.domain.repository.DateRepository

class DateRepositoryImpl @Inject constructor(
    private val dateRemoteDataSource: DateRemoteDataSource
) : DateRepository {
    override suspend fun postDate(date: Enroll) = runCatching {
        dateRemoteDataSource.postDate(requestDateDto = date.toDateData())
    }
}
