package org.sopt.teamdateroad.domain.repository

import org.sopt.teamdateroad.domain.model.Auth
import org.sopt.teamdateroad.domain.model.EditProfile
import org.sopt.teamdateroad.domain.model.SignIn
import org.sopt.teamdateroad.domain.model.SignUp

interface AuthRepository {
    suspend fun deleteSignOut(): Result<Unit>

    suspend fun deleteWithdraw(authCode: String?): Result<Unit>

    suspend fun getNicknameCheck(name: String): Result<Int>

    suspend fun postSignIn(authorization: String, signIn: SignIn): Result<Auth>

    suspend fun postSignUp(signUp: SignUp): Result<Auth>

    suspend fun patchEditProfile(editProfile: EditProfile): Result<Unit>
}
