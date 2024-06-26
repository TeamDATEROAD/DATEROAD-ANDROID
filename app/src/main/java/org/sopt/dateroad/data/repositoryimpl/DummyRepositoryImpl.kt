package org.sopt.dateroad.data.repositoryimpl

import org.sopt.dateroad.data.dataremote.datasource.DummyRemoteDataSource
import org.sopt.dateroad.data.dataremote.model.response.ResponseDummiesDto
import org.sopt.dateroad.data.mapper.todata.toData
import org.sopt.dateroad.data.mapper.todomain.toDomain
import org.sopt.dateroad.domain.model.Dummy
import org.sopt.dateroad.domain.repository.DummyRepository
import javax.inject.Inject

class DummyRepositoryImpl @Inject constructor(
    private val dummyRemoteDataSource: DummyRemoteDataSource
) : DummyRepository {
    override suspend fun getDummies(page: Int): Result<List<Dummy>> = runCatching {
        dummyRemoteDataSource.getDummies(page = page).data.map { responseDummyDto: ResponseDummiesDto.ResponseDummyDto ->
            responseDummyDto.toDomain()
        }
    }

    override suspend fun postDummy(dummy: Dummy): Result<Unit> = runCatching {
        dummyRemoteDataSource.postDummy(requestDummyDto = dummy.toData())
    }

    override suspend fun postDummyMultipart(image: String, content: String): Result<Unit> =
        runCatching {
            dummyRemoteDataSource.postDummyMultipart(image = image, content = content)
        }
}