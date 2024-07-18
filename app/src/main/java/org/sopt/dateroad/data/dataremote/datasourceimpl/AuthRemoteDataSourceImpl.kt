package org.sopt.dateroad.data.dataremote.datasourceimpl

import javax.inject.Inject
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.sopt.dateroad.data.dataremote.datasource.AuthRemoteDataSource
import org.sopt.dateroad.data.dataremote.model.request.RequestSignInDto
import org.sopt.dateroad.data.dataremote.model.request.RequestWithdrawDto
import org.sopt.dateroad.data.dataremote.model.response.ResponseAuthDto
import org.sopt.dateroad.data.dataremote.service.AuthService

class AuthRemoteDataSourceImpl @Inject constructor(
    private val authService: AuthService
) : AuthRemoteDataSource {
    override suspend fun deleteSignOut() {
        authService.deleteSignOut()
    }

    override suspend fun deleteWithdraw(requestWithdrawDto: RequestWithdrawDto) =
        authService.deleteWithdraw(requestWithdrawDto = requestWithdrawDto)

    override suspend fun getNicknameCheck(name: String) = authService.getNicknameCheck(name = name).code()

    override suspend fun postSignIn(authorization: String, requestSignInDto: RequestSignInDto): ResponseAuthDto =
        authService.postSignIn(requestSignInDto = requestSignInDto)

    override suspend fun postSignUp(image: MultipartBody.Part?, userSignUpData: RequestBody, tags: RequestBody): ResponseAuthDto =
        authService.postSignUp(image = image, userSignUpData = userSignUpData, tags = tags)
}
