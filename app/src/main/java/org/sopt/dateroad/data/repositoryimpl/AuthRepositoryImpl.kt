package org.sopt.dateroad.data.repositoryimpl

import javax.inject.Inject
import org.sopt.dateroad.data.dataremote.datasource.AuthRemoteDataSource
import org.sopt.dateroad.data.dataremote.model.request.RequestWithdrawDto
import org.sopt.dateroad.domain.repository.AuthRepository

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource
) : AuthRepository {
    override suspend fun deleteWithdraw(userId: Int, authCode: String?): Result<Unit> = runCatching {
        val requestWithdrawDto = RequestWithdrawDto(authCode)
        authRemoteDataSource.deleteWithdraw(userId, requestWithdrawDto)
    }
}
