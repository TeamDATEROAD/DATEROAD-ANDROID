package org.sopt.dateroad.data.dataremote.service

import org.sopt.dateroad.data.dataremote.model.response.ResponseDummiesDto
import org.sopt.dateroad.data.dataremote.util.API.API
import org.sopt.dateroad.data.dataremote.util.API.PAGE
import org.sopt.dateroad.data.dataremote.util.API.USERS
import retrofit2.http.GET
import retrofit2.http.Query

interface DummyService {
    @GET("$API/$USERS")
    suspend fun getDummies(
        @Query("$PAGE") page: Int
    ): ResponseDummiesDto
}