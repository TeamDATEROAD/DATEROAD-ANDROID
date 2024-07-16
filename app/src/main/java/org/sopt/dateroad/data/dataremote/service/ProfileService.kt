package org.sopt.dateroad.data.dataremote.service

import org.sopt.dateroad.data.dataremote.model.response.ResponseUserDto
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.API
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.USERS
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.VERSION
import retrofit2.http.GET
import retrofit2.http.Query

interface ProfileService {
    @GET("$API/$VERSION/$USERS")
    suspend fun getUsers(
        @Query("userID") userId: Int
    ): ResponseUserDto
}
