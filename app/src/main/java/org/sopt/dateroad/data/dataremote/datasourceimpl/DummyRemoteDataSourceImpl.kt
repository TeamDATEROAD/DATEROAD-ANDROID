package org.sopt.dateroad.data.dataremote.datasourceimpl

import org.sopt.dateroad.data.dataremote.datasource.DummyRemoteDataSource
import org.sopt.dateroad.data.dataremote.model.response.ResponseDummiesDto
import org.sopt.dateroad.data.dataremote.service.DummyService
import javax.inject.Inject

class DummyRemoteDataSourceImpl @Inject constructor(
    private val dummyService: DummyService
) : DummyRemoteDataSource {
    override suspend fun getDummies(page: Int): ResponseDummiesDto =
        dummyService.getDummies(page = page)
}