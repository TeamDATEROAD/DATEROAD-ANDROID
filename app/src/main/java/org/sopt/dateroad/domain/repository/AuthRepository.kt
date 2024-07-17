package org.sopt.dateroad.domain.repository

import org.sopt.dateroad.data.dataremote.model.request.RequestDummyDto

interface AuthRepository {
    suspend fun deleteSignOut(userId: Int)

    suspend fun deleteWithdraw(authCode: String?): Result<Unit>

    suspend fun getNicknameCheck(name: String): Result<Int>

    suspend fun postSignIn(requestDummyDto: RequestDummyDto)

    suspend fun postSignUp(requestDummyDto: RequestDummyDto)
}
