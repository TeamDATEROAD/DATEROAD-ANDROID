package org.sopt.dateroad.data.dataremote.datasource

import org.sopt.dateroad.data.dataremote.model.base.BaseResponse
import org.sopt.dateroad.data.dataremote.model.request.RequestDummyDto
import org.sopt.dateroad.data.dataremote.model.response.ResponseDummiesDto

interface DummyRemoteDataSource {
    suspend fun getDummies(page: Int): ResponseDummiesDto
    suspend fun postDummy(requestDummyDto: RequestDummyDto): BaseResponse<Unit>
    suspend fun postDummyMultipart(
        image: String,
        content: String
    ): BaseResponse<Unit>
}