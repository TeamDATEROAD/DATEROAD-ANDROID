package org.sopt.dateroad.data.dataremote.datasource

import org.sopt.dateroad.data.dataremote.model.request.RequestDummyDto
import org.sopt.dateroad.data.dataremote.model.request.RequestSignInDto
import org.sopt.dateroad.data.dataremote.model.request.RequestWithdrawDto
import org.sopt.dateroad.data.dataremote.model.response.ResponseAuthDto

interface AuthRemoteDataSource {
    suspend fun deleteSignOut(userId: Int)

    suspend fun deleteWithdraw(requestWithdrawDto: RequestWithdrawDto)

    suspend fun getNicknameCheck(name: String): Int

    suspend fun postSignIn(authorization: String, requestSignInDto: RequestSignInDto):ResponseAuthDto

    suspend fun postSignUp(requestDummyDto: RequestDummyDto)
}
