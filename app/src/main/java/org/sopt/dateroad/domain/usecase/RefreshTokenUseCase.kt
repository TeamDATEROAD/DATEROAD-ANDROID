package org.sopt.dateroad.domain.usecase

import javax.inject.Inject
import javax.inject.Singleton
import org.sopt.dateroad.domain.model.Auth
import org.sopt.dateroad.domain.repository.AuthRepository

@Singleton
class RefreshTokenUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(refreshToken: String): Auth {
        return authRepository.reissueToken(refreshToken).getOrThrow()
    }
}
