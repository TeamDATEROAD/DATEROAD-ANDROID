package org.sopt.dateroad.data.dataremote.datasourceimpl

import android.content.ContentResolver
import javax.inject.Inject
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.sopt.dateroad.data.dataremote.datasource.AuthRemoteDataSource
import org.sopt.dateroad.data.dataremote.model.base.BaseResponse
import org.sopt.dateroad.data.dataremote.model.request.RequestDummyDto
import org.sopt.dateroad.data.dataremote.service.AuthService
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.TEXT_PLANE

class AuthRemoteDataSourceImpl @Inject constructor(
    private val contentResolver: ContentResolver,
    private val authService: AuthService
) : AuthRemoteDataSource {

    override suspend fun getNicknameCheck(name: String): Unit =
        authService.getNicknameCheck(name = name)

    override suspend fun postSignIn(requestDummyDto: RequestDummyDto): BaseResponse<Unit> =
        authService.postSignIn(requestDummyDto)

    override suspend fun postSignUp(requestDummyDto: RequestDummyDto): BaseResponse<Unit> =
        authService.postSignUp(requestDummyDto)

    override suspend fun deleteWithdraw(userId: Int, authCode: String): BaseResponse<Unit> {
        val requestBody = authCode.toRequestBody(TEXT_PLANE.toMediaType())
        return authService.deleteWithdraw(userId, requestBody)
    }

    override suspend fun deleteSignOut(userId: Int): BaseResponse<Unit> =
        authService.deleteSignOut(userId)
}
