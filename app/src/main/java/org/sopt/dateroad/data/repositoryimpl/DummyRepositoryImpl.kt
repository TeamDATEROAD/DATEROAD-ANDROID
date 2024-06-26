package org.sopt.dateroad.data.repositoryimpl

import org.sopt.dateroad.data.dataremote.datasource.DummyRemoteDataSource
import org.sopt.dateroad.data.dataremote.model.response.ResponseDummiesDto
import org.sopt.dateroad.data.mapper.todomain.toDomain
import org.sopt.dateroad.domain.model.DummyModel
import org.sopt.dateroad.domain.repository.DummyRepository
import javax.inject.Inject

class DummyRepositoryImpl @Inject constructor(
    private val dummyRemoteDataSource: DummyRemoteDataSource
) : DummyRepository {
    override suspend fun getDummies(page: Int): Result<List<DummyModel>> = runCatching {
        dummyRemoteDataSource.getDummies(page = page).data.map { responseDummyDto: ResponseDummiesDto.ResponseDummyDto ->
            responseDummyDto.toDomain()
        }
    }
}