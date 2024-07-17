package org.sopt.dateroad.domain.repository

interface AuthRepository {
    suspend fun deleteWithdraw(authCode: String?): Result<Unit>
}
