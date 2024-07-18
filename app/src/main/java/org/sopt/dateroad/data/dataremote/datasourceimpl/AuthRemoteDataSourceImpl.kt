package org.sopt.dateroad.data.dataremote.datasourceimpl

import javax.inject.Inject
import org.sopt.dateroad.data.dataremote.datasource.AuthRemoteDataSource
import org.sopt.dateroad.data.dataremote.model.request.RequestDummyDto
import org.sopt.dateroad.data.dataremote.model.request.RequestWithdrawDto
import org.sopt.dateroad.data.dataremote.service.AuthService

class AuthRemoteDataSourceImpl @Inject constructor(
    private val authService: AuthService
) : AuthRemoteDataSource {
    override suspend fun deleteSignOut(userId: Int) {
        authService.deleteSignOut(userId)
    }

    override suspend fun deleteWithdraw(requestWithdrawDto: RequestWithdrawDto) =
        authService.deleteWithdraw(requestWithdrawDto = requestWithdrawDto)

    override suspend fun getNicknameCheck(name: String) = authService.getNicknameCheck(name = name).code()

    override suspend fun postSignIn(requestDummyDto: RequestDummyDto) {
        authService.postSignIn(requestDummyDto)
    }

    override suspend fun postSignUp(requestDummyDto: RequestDummyDto) {
        authService.postSignUp(requestDummyDto)
    }
}