package org.sopt.dateroad.data.dataremote.datasourceimpl

import android.content.ContentResolver
import android.net.Uri
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.sopt.dateroad.data.dataremote.datasource.DummyRemoteDataSource
import org.sopt.dateroad.data.dataremote.model.base.BaseResponse
import org.sopt.dateroad.data.dataremote.model.request.RequestDummyDto
import org.sopt.dateroad.data.dataremote.model.response.ResponseDummiesDto
import org.sopt.dateroad.data.dataremote.service.DummyService
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.TEXT_PLANE
import org.sopt.dateroad.data.dataremote.util.ContentUriRequestBody
import javax.inject.Inject

class DummyRemoteDataSourceImpl @Inject constructor(
    private val contentResolver: ContentResolver,
    private val dummyService: DummyService
) : DummyRemoteDataSource {
    override suspend fun getDummies(page: Int): ResponseDummiesDto =
        dummyService.getDummies(page = page)

    override suspend fun postDummy(requestDummyDto: RequestDummyDto): BaseResponse<Unit> = dummyService.postDummy(requestDummyDto = requestDummyDto)

    override suspend fun postDummyMultipart(
        image: String,
        content: String
    ): BaseResponse<Unit> = dummyService.postDummyMultipart(
        image = ContentUriRequestBody(
            contentResolver = contentResolver,
            uri = Uri.parse(image)
        ).toFormData(),
        content = content.toRequestBody(TEXT_PLANE.toMediaType())
    )
}
