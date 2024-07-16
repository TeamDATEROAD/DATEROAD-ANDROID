package org.sopt.dateroad.domain.usecase

import javax.inject.Inject
import javax.inject.Singleton
import org.sopt.dateroad.domain.repository.AuthRepository

@Singleton
class DeleteWithdrawUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(userId: Int, authCode: String?): Result<Unit> {
        return authRepository.deleteWithdraw(userId, authCode)
    }
}
