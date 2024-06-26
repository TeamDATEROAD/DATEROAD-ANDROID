package org.sopt.dateroad.domain.repository

import org.sopt.dateroad.domain.model.DummyModel

interface DummyRepository {
    suspend fun getDummies(page: Int): Result<List<DummyModel>>
    suspend fun postDummyMultipart(image: String, content: String): Result<Unit>
}