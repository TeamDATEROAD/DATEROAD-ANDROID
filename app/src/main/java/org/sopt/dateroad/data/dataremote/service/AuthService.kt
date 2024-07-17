package org.sopt.dateroad.data.dataremote.service

import okhttp3.RequestBody
import org.sopt.dateroad.data.dataremote.model.base.BaseResponse
import org.sopt.dateroad.data.dataremote.model.request.RequestDummyDto
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.API
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.CHECK
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.NAME
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.USERS
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.VERSION
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthService {
    @GET("$API/$VERSION/$USERS/$CHECK")
    suspend fun getNicknameCheck(
        @Query(NAME) name: String
    )

    @POST("api/v1/users/signin")
    suspend fun postSignIn(
        @Body requestDummyDto: RequestDummyDto
    ): BaseResponse<Unit>

    @POST("api/v1/users/signup")
    suspend fun postSignUp(
        @Body requestDummyDto: RequestDummyDto
    ): BaseResponse<Unit>

    @DELETE("api/v1/users/withdraw")
    suspend fun deleteWithdraw(
        @Query("userId") userId: Int,
        @Body authCode: RequestBody
    ): BaseResponse<Unit>

    @DELETE("api/v1/users/signout")
    suspend fun deleteSignOut(
        @Query("userId") userId: Int
    ): BaseResponse<Unit>
}
