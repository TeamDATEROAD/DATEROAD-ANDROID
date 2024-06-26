package org.sopt.dateroad.data.dataremote.service

import org.sopt.dateroad.data.dataremote.model.response.ResponseDummiesDto
import org.sopt.dateroad.data.dataremote.util.Api.API
import org.sopt.dateroad.data.dataremote.util.Api.PAGE
import org.sopt.dateroad.data.dataremote.util.Api.USERS
import retrofit2.http.GET
import retrofit2.http.Query

interface DummyService {
    @GET("$API/$USERS")
    suspend fun getDummies(
        @Query("$PAGE") page: Int
    ): ResponseDummiesDto
}