package org.sopt.teamdateroad.data.dataremote.datasourceimpl

import javax.inject.Inject
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.sopt.teamdateroad.data.dataremote.datasource.AuthRemoteDataSource
import org.sopt.teamdateroad.data.dataremote.model.request.RequestSignInDto
import org.sopt.teamdateroad.data.dataremote.model.request.RequestWithdrawDto
import org.sopt.teamdateroad.data.dataremote.model.response.ResponseAuthDto
import org.sopt.teamdateroad.data.dataremote.service.AuthService

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

    override suspend fun patchEditProfile(name: RequestBody, tags: RequestBody, image: MultipartBody.Part?, isDefaultImage: RequestBody) =
        authService.patchProfile(name = name, tags = tags, image = image, isDefaultImage = isDefaultImage)
}
