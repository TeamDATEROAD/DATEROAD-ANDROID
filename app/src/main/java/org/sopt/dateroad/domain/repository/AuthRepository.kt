package org.sopt.dateroad.domain.repository

import org.sopt.dateroad.domain.model.Auth
import org.sopt.dateroad.domain.model.SignIn
import org.sopt.dateroad.domain.model.SignUp

interface AuthRepository {
    suspend fun deleteSignOut(): Result<Unit>

    suspend fun deleteWithdraw(authCode: String?): Result<Unit>

    suspend fun getNicknameCheck(name: String): Result<Int>

    suspend fun postSignIn(authorization: String, signIn: SignIn): Result<Auth>

    suspend fun postSignUp(signUp: SignUp): Result<Auth>

    suspend fun reissueToken(refreshToken: String): Result<Auth>
}
