package org.sopt.dateroad.data.dataremote.datasourceimpl

import javax.inject.Inject
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.sopt.dateroad.data.dataremote.datasource.AuthRemoteDataSource
import org.sopt.dateroad.data.dataremote.model.request.RequestDummyDto
import org.sopt.dateroad.data.dataremote.service.AuthService
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.TEXT_PLANE

class AuthRemoteDataSourceImpl @Inject constructor(
    private val authService: AuthService
) : AuthRemoteDataSource {

    override suspend fun getNicknameCheck(name: String) = authService.getNicknameCheck(name = name).code()

    override suspend fun postSignIn(requestDummyDto: RequestDummyDto) {
        authService.postSignIn(requestDummyDto)
    }

    override suspend fun postSignUp(requestDummyDto: RequestDummyDto) {
        authService.postSignUp(requestDummyDto)
    }

    override suspend fun deleteWithdraw(userId: Int, authCode: String) {
        val requestBody = authCode.toRequestBody(TEXT_PLANE.toMediaType())
        authService.deleteWithdraw(userId, requestBody)
    }

    override suspend fun deleteSignOut(userId: Int) {
        authService.deleteSignOut(userId)
    }
}
