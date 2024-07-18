package org.sopt.dateroad.data.dataremote.service

import org.sopt.dateroad.data.dataremote.model.base.BaseResponse
import org.sopt.dateroad.data.dataremote.model.request.RequestDummyDto
import org.sopt.dateroad.data.dataremote.model.request.RequestWithdrawDto
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.API
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.CHECK
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.NAME
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.USERS
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.VERSION
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.WITHDRAW
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthService {
    @DELETE("api/v1/users/signout")
    suspend fun deleteSignOut(
        @Query("userId") userId: Int
    ): BaseResponse<Unit>

    @HTTP(method = "DELETE", hasBody = true, path = "$API/$VERSION/$USERS/$WITHDRAW")
    suspend fun deleteWithdraw(
        @Body requestWithdrawDto: RequestWithdrawDto
    )

    @GET("$API/$VERSION/$USERS/$CHECK")
    suspend fun getNicknameCheck(
        @Query(NAME) name: String
    ): Response<Unit>

    @POST("api/v1/users/signin")
    suspend fun postSignIn(
        @Body requestDummyDto: RequestDummyDto
    ): BaseResponse<Unit>

    @POST("api/v1/users/signup")
    suspend fun postSignUp(
        @Body requestDummyDto: RequestDummyDto
    ): BaseResponse<Unit>
}
