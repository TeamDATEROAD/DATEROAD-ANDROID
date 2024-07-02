package org.sopt.dateroad.domain.repository

import org.sopt.dateroad.domain.model.Dummy

interface DummyRepository {
    suspend fun getDummies(page: Int): Result<List<Dummy>>
    suspend fun postDummy(dummy: Dummy): Result<Unit>
    suspend fun postDummyMultipart(image: String, content: String): Result<Unit>
}
