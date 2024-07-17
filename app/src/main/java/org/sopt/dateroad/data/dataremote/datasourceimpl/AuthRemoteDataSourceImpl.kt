package org.sopt.dateroad.data.dataremote.datasourceimpl

import javax.inject.Inject
import org.sopt.dateroad.data.dataremote.datasource.AuthRemoteDataSource
import org.sopt.dateroad.data.dataremote.model.request.RequestWithdrawDto
import org.sopt.dateroad.data.dataremote.service.AuthService

class AuthRemoteDataSourceImpl @Inject constructor(
    private val authService: AuthService
) : AuthRemoteDataSource {
    override suspend fun deleteWithdraw(requestWithdrawDto: RequestWithdrawDto) =
        authService.deleteWithdraw(requestWithdrawDto = requestWithdrawDto)
}
