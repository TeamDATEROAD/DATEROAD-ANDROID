package org.sopt.dateroad.data.dataremote.datasource

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.sopt.dateroad.data.dataremote.model.request.RequestSignInDto
import org.sopt.dateroad.data.dataremote.model.request.RequestWithdrawDto
import org.sopt.dateroad.data.dataremote.model.response.ResponseAuthDto

interface AuthRemoteDataSource {
    suspend fun deleteSignOut()

    suspend fun deleteWithdraw(requestWithdrawDto: RequestWithdrawDto)

    suspend fun getNicknameCheck(name: String): Int

    suspend fun postSignIn(authorization: String, requestSignInDto: RequestSignInDto): ResponseAuthDto

    suspend fun postSignUp(image: MultipartBody.Part?, userSignUpData: RequestBody, tags: RequestBody): ResponseAuthDto
}
