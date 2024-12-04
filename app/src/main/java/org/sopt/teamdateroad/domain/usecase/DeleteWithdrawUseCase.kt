package org.sopt.teamdateroad.domain.usecase

import javax.inject.Inject
import javax.inject.Singleton
import org.sopt.teamdateroad.domain.repository.AuthRepository

@Singleton
class DeleteWithdrawUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(authCode: String?): Result<Unit> = authRepository.deleteWithdraw(authCode)
}
