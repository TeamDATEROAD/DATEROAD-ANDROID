package org.sopt.dateroad.data.dataremote.datasource

import org.sopt.dateroad.data.dataremote.model.request.RequestDummyDto
import org.sopt.dateroad.data.dataremote.model.request.RequestWithdrawDto

interface AuthRemoteDataSource {
    suspend fun deleteSignOut(userId: Int)

    suspend fun deleteWithdraw(requestWithdrawDto: RequestWithdrawDto)

    suspend fun getNicknameCheck(name: String): Int

    suspend fun postSignIn(requestDummyDto: RequestDummyDto)

    suspend fun postSignUp(requestDummyDto: RequestDummyDto)
}
