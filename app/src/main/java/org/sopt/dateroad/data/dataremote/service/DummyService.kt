package org.sopt.dateroad.data.dataremote.service

import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.sopt.dateroad.data.dataremote.model.base.BaseResponse
import org.sopt.dateroad.data.dataremote.model.request.RequestDummyDto
import org.sopt.dateroad.data.dataremote.model.response.ResponseDummiesDto
import org.sopt.dateroad.data.dataremote.util.Api.API
import org.sopt.dateroad.data.dataremote.util.Api.CONTENT
import org.sopt.dateroad.data.dataremote.util.Api.PAGE
import org.sopt.dateroad.data.dataremote.util.Api.USERS
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface DummyService {
    @GET("$API/$USERS")
    suspend fun getDummies(
        @Query("$PAGE") page: Int
    ): ResponseDummiesDto

    @POST("$API")
    suspend fun postDummy(
        @Body requestDummyDto: RequestDummyDto
    ): BaseResponse<Unit>

    @Multipart
    @POST("$API")
    suspend fun postDummyMultipart(
        @Part image: MultipartBody.Part,
        @Part("$CONTENT") content: RequestBody
    ): BaseResponse<Unit>
}