package org.sopt.teamdateroad.domain.usecase

import javax.inject.Inject
import javax.inject.Singleton
import org.sopt.teamdateroad.domain.model.Auth
import org.sopt.teamdateroad.domain.model.SignIn
import org.sopt.teamdateroad.domain.repository.AuthRepository

@Singleton
class PostSignInUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(authorization: String, signIn: SignIn): Result<Auth> =
        authRepository.postSignIn(authorization = authorization, signIn = signIn)
}
