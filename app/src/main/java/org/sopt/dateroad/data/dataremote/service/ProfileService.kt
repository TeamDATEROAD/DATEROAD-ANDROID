package org.sopt.dateroad.data.dataremote.service

import org.sopt.dateroad.data.dataremote.model.response.ResponseProfileDto
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.API
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.USERS
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.VERSION
import retrofit2.http.GET

interface ProfileService {
    @GET("$API/$VERSION/$USERS")
    suspend fun getProfile(): ResponseProfileDto
}
