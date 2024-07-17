package org.sopt.dateroad.domain.repository

import org.sopt.dateroad.data.dataremote.model.request.RequestDummyDto

interface AuthRepository {
    suspend fun deleteWithdraw(authCode: String?): Result<Unit>
    suspend fun getNicknameCheck(name: String): Result<Int>

    suspend fun postSignIn(requestDummyDto: RequestDummyDto)

    suspend fun postSignUp(requestDummyDto: RequestDummyDto)

    suspend fun deleteWithdraw(userId: Int, authCode: String)

    suspend fun deleteSignOut(userId: Int)
}
