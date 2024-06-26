package org.sopt.dateroad.data.dataremote.datasource

import org.sopt.dateroad.data.dataremote.model.response.ResponseDummiesDto

interface DummyRemoteDataSource {
    suspend fun getDummies(page: Int): ResponseDummiesDto
}