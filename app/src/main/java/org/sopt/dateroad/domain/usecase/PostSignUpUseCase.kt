package org.sopt.dateroad.domain.usecase

import javax.inject.Inject
import javax.inject.Singleton
import org.sopt.dateroad.domain.model.Auth
import org.sopt.dateroad.domain.model.SignUp
import org.sopt.dateroad.domain.repository.AuthRepository

@Singleton
class PostSignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(signUp: SignUp): Result<Auth> =
        authRepository.postSignUp(signUp = signUp)
}
