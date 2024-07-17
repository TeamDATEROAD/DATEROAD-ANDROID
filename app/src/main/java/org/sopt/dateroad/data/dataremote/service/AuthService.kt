package org.sopt.dateroad.data.dataremote.service

import org.sopt.dateroad.data.dataremote.model.base.BaseResponse
import org.sopt.dateroad.data.dataremote.model.request.RequestDummyDto
import org.sopt.dateroad.data.dataremote.model.request.RequestSignInDto
import org.sopt.dateroad.data.dataremote.model.request.RequestWithdrawDto
import org.sopt.dateroad.data.dataremote.model.response.ResponseAuthDto
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.API
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.AUTHORIZATION
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.CHECK
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.NAME
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.SIGNUP
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.SIGN_IN
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.USERS
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.VERSION
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.WITHDRAW
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.Header
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

    @POST("$API/$VERSION/$USERS/$SIGN_IN")
    suspend fun postSignIn(
        @Header(AUTHORIZATION) authorization: String,
        @Body requestSignInDto: RequestSignInDto
    ): ResponseAuthDto

    @POST("$API/$VERSION/$USERS/$SIGNUP")
    suspend fun postSignUp(
        @Body requestDummyDto: RequestDummyDto
    ): BaseResponse<Unit>
}
