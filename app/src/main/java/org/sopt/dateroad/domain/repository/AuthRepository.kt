package org.sopt.dateroad.domain.repository

import org.sopt.dateroad.data.dataremote.model.request.RequestDummyDto
import org.sopt.dateroad.domain.model.Auth
import org.sopt.dateroad.domain.model.SignIn

interface AuthRepository {
    suspend fun deleteSignOut(userId: Int)

    suspend fun deleteWithdraw(authCode: String?): Result<Unit>

    suspend fun getNicknameCheck(name: String): Result<Int>

    suspend fun postSignIn(authorization: String, signIn: SignIn): Result<Auth>

    suspend fun postSignUp(requestDummyDto: RequestDummyDto)
}
