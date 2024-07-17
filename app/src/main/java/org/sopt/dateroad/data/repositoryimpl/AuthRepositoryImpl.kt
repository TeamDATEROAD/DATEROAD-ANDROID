package org.sopt.dateroad.data.repositoryimpl

import javax.inject.Inject
import org.sopt.dateroad.data.dataremote.datasource.AuthRemoteDataSource
import org.sopt.dateroad.data.dataremote.model.request.RequestDummyDto
import org.sopt.dateroad.data.dataremote.model.request.RequestWithdrawDto
import org.sopt.dateroad.data.mapper.todata.toData
import org.sopt.dateroad.data.mapper.todomain.toDomain
import org.sopt.dateroad.domain.model.Auth
import org.sopt.dateroad.domain.model.SignIn
import org.sopt.dateroad.domain.repository.AuthRepository

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource
) : AuthRepository {
    override suspend fun deleteSignOut(userId: Int) {
        authRemoteDataSource.deleteSignOut(userId)
    }

    override suspend fun deleteWithdraw(authCode: String?): Result<Unit> = runCatching {
        authRemoteDataSource.deleteWithdraw(requestWithdrawDto = RequestWithdrawDto(authCode))
    }

    override suspend fun getNicknameCheck(name: String): Result<Int> = runCatching {
        authRemoteDataSource.getNicknameCheck(name = name)
    }

    override suspend fun postSignIn(authorization: String, signIn: SignIn):Result<Auth> = runCatching{
        authRemoteDataSource.postSignIn(authorization = authorization, requestSignInDto = signIn.toData()).toDomain()
    }

    override suspend fun postSignUp(requestDummyDto: RequestDummyDto) {
        authRemoteDataSource.postSignUp(requestDummyDto)
    }
}
