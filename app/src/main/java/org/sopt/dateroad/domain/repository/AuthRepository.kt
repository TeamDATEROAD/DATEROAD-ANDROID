package org.sopt.dateroad.domain.repository

interface AuthRepository {
    suspend fun deleteWithdraw(userId: Int, authCode: String?): Result<Unit>
}
