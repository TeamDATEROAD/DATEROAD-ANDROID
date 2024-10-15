package org.sopt.teamdateroad.data.dataremote.service

import org.sopt.teamdateroad.data.dataremote.model.response.ResponseProfileDto
import org.sopt.teamdateroad.data.dataremote.util.ApiConstraints.API
import org.sopt.teamdateroad.data.dataremote.util.ApiConstraints.USERS
import org.sopt.teamdateroad.data.dataremote.util.ApiConstraints.VERSION
import retrofit2.http.GET

interface ProfileService {
    @GET("$API/$VERSION/$USERS")
    suspend fun getProfile(): ResponseProfileDto
}
