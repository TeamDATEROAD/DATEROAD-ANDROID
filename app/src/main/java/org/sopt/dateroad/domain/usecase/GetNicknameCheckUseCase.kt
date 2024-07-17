package org.sopt.dateroad.domain.usecase

import javax.inject.Inject
import javax.inject.Singleton
import org.sopt.dateroad.domain.repository.AuthRepository

@Singleton
class GetNicknameCheckUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(name: String): Result<Unit> {
        return runCatching {
            authRepository.getNicknameCheck(name)
        }
    }
}
